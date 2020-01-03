package com.blog.framework.dao;

import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.model.CommentModel;
import com.blog.framework.vo.CommentCountVo;

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
     * 获取最新的十条评论
     *
     * @return List<CommentModel>
     */
    List<CommentModel> topCommentList();

    /**
     * 新增评论
     *
     * @param dto 数据
     * @return Boolean
     */
    Boolean add(CommentDto dto);

    /**
     * 统计各个博客的评论数
     *
     * @param blogIds 博客id集合
     * @return List<CommentCountVo>
     */
    List<CommentCountVo> getCommentCountByBlogId(List<Long> blogIds);
}
