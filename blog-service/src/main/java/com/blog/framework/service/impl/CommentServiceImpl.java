package com.liuzw.blog.service.impl;


import com.github.pagehelper.PageHelper;
import com.liuzw.blog.common.Page;
import com.liuzw.blog.dto.CommentDto;
import com.liuzw.blog.dto.CommentQueryDto;
import com.liuzw.blog.mapper.CommentMapper;
import com.liuzw.blog.mapper.ReplyMapper;
import com.liuzw.blog.mapper.UserMapper;
import com.liuzw.blog.model.CommentModel;
import com.liuzw.blog.model.ReplyModel;
import com.liuzw.blog.model.UserModel;
import com.liuzw.blog.service.CommentService;
import com.liuzw.blog.utils.CopyDataUtil;
import com.liuzw.blog.vo.CommentVo;
import com.liuzw.blog.vo.ReplyVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collections;
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
    private CommentMapper commentMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<CommentVo> getList(CommentQueryDto dto) {
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        //获取对博客的评论
        List<CommentModel> commentList = commentMapper.select(CommentModel.builder()
                .blogId(dto.getBlogId())
                .commentType(dto.getType())
                .build());

        if (CollectionUtils.isEmpty(commentList)) {
            return new Page<>();
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

        return Page.createPageBean(page.getPageNum(),page.getPageSize(), page.getTotal(), list);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean insert(CommentDto dto) {
        CommentModel model = CommentModel.builder()
                .commentType(dto.getCommentType())
                .content(dto.getContent())
                .userId(dto.getUserId())
                .blogId(dto.getBlogId())
                .build();
        return commentMapper.insertSelective(model) > 0;
    }


    /**
     * 处理回复内容
     *
     * @param commentIds 评论id
     * @param map        用户信息
     * @return Map<Long ,   List < ReplyVo>>
     */
    private Map<Long, List<ReplyVo>> handleReply(List<Long> commentIds, Map<Long, UserModel> map) {
        Example example = new Example(ReplyModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("commentId", commentIds);
        List<ReplyModel> replyList = replyMapper.selectByExample(example);

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
     * @param id        当前回复id
     * @param rootList  要查找的列表
     * @return          List<ReplyVo>
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
        for (ReplyVo vo : childList) {
            vo.setChild(getChild(vo.getId(), rootList));
        }
        return childList;
    }


    /**
     * 获取所有用户信息
     *
     * @return Map<userId , UserModel>
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