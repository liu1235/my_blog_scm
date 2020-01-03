package com.blog.framework.service;


import com.blog.framework.common.PageBean;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.vo.CommentVo;

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
     * @return PageBean<CommentVo>
     */
    PageBean<CommentVo> list(CommentQueryDto dto);

    /**
     * 获取最新的十条评论
     *
     * @return List<CommentVo>
     */
    List<CommentVo> topCommentList();

    /**
     * 新增评论
     *
     * @param dto 数据
     * @return Boolean
     */
    Boolean add(CommentDto dto);

}