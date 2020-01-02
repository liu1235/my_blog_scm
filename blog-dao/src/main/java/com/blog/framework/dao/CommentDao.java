package com.blog.framework.dao;

import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.model.CommentModel;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-02
 **/
public interface CommentDao {


    /**
     * 获取评论内容
     *
     * @param dto 查询条件
     * @return List<CommentVo>
     */
    List<CommentModel> list(CommentQueryDto dto);

    /**
     * 新增评论
     *
     * @param dto 数据
     * @return Boolean
     */
    Boolean add(CommentDto dto);
}
