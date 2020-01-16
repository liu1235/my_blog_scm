package com.blog.framework.service;


import com.blog.framework.common.PageBean;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.dto.comment.ReplyDto;
import com.blog.framework.vo.comment.CommentVo;

import java.util.List;

/**
 * interface Comment
 *
 * @author liuzw
 */
public interface CommentService {

    /**
     * 获取评论内容
     *
     * @param dto 查询条件
     * @return CommentVo
     */
    CommentVo list(CommentQueryDto dto);

    /**
     * 新增评论
     *
     * @param dto 新增数据
     * @return Boolean
     */
    Boolean add(CommentDto dto);

    /**
     * 新增回复
     *
     * @param dto 新增数据
     * @return Boolean
     */
    Boolean addReply(ReplyDto dto);


}