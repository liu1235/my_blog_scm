package com.blog.framework.service.impl;


import com.blog.framework.common.PageBean;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.dao.CommentDao;
import com.blog.framework.dao.ReplyDao;
import com.blog.framework.dao.UserDao;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.dto.comment.ReplyDto;
import com.blog.framework.model.CommentModel;
import com.blog.framework.model.ReplyModel;
import com.blog.framework.model.UserModel;
import com.blog.framework.service.CommentService;
import com.blog.framework.service.TokenService;
import com.blog.framework.vo.comment.CommentVo;
import com.blog.framework.vo.comment.ReplyVo;
import com.blog.framework.vo.user.UserLoginVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CommentServiceImpl
 *
 * @author liuzw
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    @Override
    public CommentVo list(CommentQueryDto dto) {
        Page<CommentModel> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        //获取对博客的评论
        List<CommentModel> commentList = commentDao.list(dto);
        if (CollectionUtils.isEmpty(commentList)) {
            return CommentVo.builder().count(0L)
                    .detail(new PageBean<>())
                    .build();
        }
        List<CommentVo.Detail> list = CopyDataUtil.copyList(commentList, CommentVo.Detail.class);
        //获取评论id
        List<Long> commentIds = commentList.stream()
                .map(CommentModel::getId)
                .distinct()
                .collect(Collectors.toList());
        //获取用户信息
        List<Long> userIds = commentList.stream()
                .map(CommentModel::getUserId)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, UserModel> map = getUserInfo(userIds);

        List<ReplyModel> replyList = replyDao.getReplyByCommentId(commentIds);
        //处理回复内容 拼装成树形
        Map<Long, List<ReplyVo>> replyMap = handleReply(replyList, map);
        //评论回复总条数
        long count = page.getTotal() + replyList.size();

        for (CommentVo.Detail detail : list) {
            if (detail.getUserId() == -1) {
                detail.setUserName("游客");
            } else {
                UserModel userModel = map.get(detail.getUserId());
                detail.setUserName(userModel.getUserName());
                detail.setHeadPhoto(userModel.getHeadPhoto());
                if (StringUtils.isNotBlank(userModel.getTags())) {
                    detail.setLabels(Arrays.asList(userModel.getTags().split(",")));
                }
            }
            detail.setChild(replyMap.get(detail.getId()));
            detail.setReplyId(-1L);
        }

        return CommentVo.builder()
                .count(count)
                .detail(PageBean.createPageBean(page.getPageNum(), page.getPageSize(), page.getTotal(), list))
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean add(CommentDto dto) {
        // 获取当前登录人id
        UserLoginVo userInfo = tokenService.getUserInfo();
        if (userInfo != null && userInfo.getUserId() != null) {
            dto.setUserId(userInfo.getUserId());
        } else {
            dto.setUserId(-1L);
        }
        return commentDao.add(dto);
    }

    @Override
    public Boolean addReply(ReplyDto dto) {
        ReplyModel replyModel = CopyDataUtil.copyObject(dto, ReplyModel.class);
        UserLoginVo userInfo = tokenService.getUserInfo();
        if (userInfo == null || userInfo.getUserId() == null) {
            throw new LoginException();
        }
        replyModel.setFromUserId(userInfo.getUserId());
        return replyDao.add(replyModel);
    }


    /**
     * 处理回复内容
     *
     * @param map 用户信息
     * @return Map<Long, List < ReplyVo>>
     */
    private Map<Long, List<ReplyVo>> handleReply(List<ReplyModel> replyList, Map<Long, UserModel> map) {

        if (CollectionUtils.isEmpty(replyList)) {
            return Collections.emptyMap();
        }

        List<ReplyVo> list = CopyDataUtil.copyList(replyList, ReplyVo.class);

        for (ReplyVo vo : list) {
            UserModel userModel = map.get(vo.getFromUserId());
            if (userModel != null) {
                vo.setFromUserName(userModel.getUserName());
                vo.setFromHeadPhoto(userModel.getHeadPhoto());
            }
            UserModel userModel1 = map.get(vo.getToUserId());
            if (userModel1 != null) {
                vo.setToUserName(userModel1.getUserName());
            }
        }

        List<ReplyVo> rootList = list.stream()
                //根据回复类型获取最上级评论 回复类型(1:对评论的回复，2: 对回复的回复)
                .filter(vo -> vo.getReplyType() == 1)
                .sorted(Comparator.comparing(ReplyVo::getCreateTime))
                .collect(Collectors.toList());

        // 为对评论的回复设置 对回复的回复，getChild是递归调用的
        for (ReplyVo vo : rootList) {
            vo.setChild(getChild(vo.getId(), list));
        }

        return rootList.stream().collect(Collectors.groupingBy(ReplyVo::getCommentId));

    }


    /**
     * 递归设置回复内容
     *
     * @param id       当前回复id
     * @param rootList 要查找的列表
     * @return List<ReplyVo>
     */
    private List<ReplyVo> getChild(Long id, List<ReplyVo> rootList) {
        List<ReplyVo> childList = new ArrayList<>();
        for (ReplyVo vo : rootList) {
            if (vo.getReplyId().equals(id)) {
                childList.add(vo);
            }
        }
        // 递归退出条件
        if (childList.isEmpty()) {
            return Collections.emptyList();
        }
        //排序
        childList.sort(Comparator.comparing(ReplyVo::getCreateTime));
        for (ReplyVo vo : childList) {
            vo.setChild(getChild(vo.getId(), rootList));
        }
        return childList;
    }


    /**
     * 获取所有用户信息
     *
     * @return Map<userId, UserModel>
     */
    private Map<Long, UserModel> getUserInfo(List<Long> userIds) {
        //获取所有用户信息
        List<UserModel> list = userDao.selectByIds(userIds);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.toMap(UserModel::getId, u -> u));
    }
}