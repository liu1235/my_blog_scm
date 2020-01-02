package com.blog.framework.service.impl;


import com.blog.framework.common.PageBean;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.dao.CommentDao;
import com.blog.framework.dao.ReplyDao;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.mapper.CommentMapper;
import com.blog.framework.mapper.ReplyMapper;
import com.blog.framework.mapper.UserMapper;
import com.blog.framework.model.CommentModel;
import com.blog.framework.model.ReplyModel;
import com.blog.framework.model.UserModel;
import com.blog.framework.service.CommentService;
import com.blog.framework.vo.CommentVo;
import com.blog.framework.vo.ReplyVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
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
    private UserMapper userMapper;

    @Override
    public PageBean<CommentVo> list(CommentQueryDto dto) {
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        //获取对博客的评论
        List<CommentModel> commentList = commentDao.list(dto);
        if (CollectionUtils.isEmpty(commentList)) {
            return new PageBean<>();
        }
        List<CommentVo> list = CopyDataUtil.copyList(commentList, CommentVo.class);
        //获取评论id
        List<Long> commentIds = commentList.stream().map(CommentModel::getId).distinct().collect(Collectors.toList());
        //获取用户信息
        Map<Long, UserModel> map = getUserInfo();

        Map<Long, List<ReplyVo>> replyMap = handleReply(commentIds, map);

        for (CommentVo vo : list) {
            UserModel userModel = map.get(vo.getUserId());
            vo.setUserName(userModel.getUserName());
            vo.setHeadPhoto(userModel.getHeadPhoto());
            vo.setChild(replyMap.get(vo.getId()));
        }

        return PageBean.createPageBean(page.getPageNum(), page.getPageSize(), page.getTotal(), list);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean add(CommentDto dto) {
       return commentDao.add(dto);
    }


    /**
     * 处理回复内容
     *
     * @param commentIds 评论id
     * @param map        用户信息
     * @return Map<Long, List < ReplyVo>>
     */
    private Map<Long, List<ReplyVo>> handleReply(List<Long> commentIds, Map<Long, UserModel> map) {
        List<ReplyModel> replyList = replyDao.getReplyByCommentId(commentIds);

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
    private Map<Long, UserModel> getUserInfo() {
        //获取所有用户信息
        List<UserModel> list = userMapper.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.toMap(UserModel::getId, u -> u));
    }
}