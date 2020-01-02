package com.blog.framework.dao;

import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.model.ReplyModel;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-02
 **/
public interface ReplyDao {


    /**
     * 获取评论内容
     *
     * @param commentIds 评论id
     * @return List<CommentVo>
     */
    List<ReplyModel> getReplyByCommentId(List<Long> commentIds);

    /**
     * 新增回复
     *
     * @param dto 数据
     * @return Boolean
     */
    Boolean add(CommentDto dto);
}
