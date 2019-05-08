package com.liuzw.blog.service;


import com.liuzw.blog.common.Page;
import com.liuzw.blog.dto.CommentDto;
import com.liuzw.blog.dto.CommentQueryDto;
import com.liuzw.blog.vo.CommentVo;

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
     * @return list<CommentVo>
     */
    Page<CommentVo> getList(CommentQueryDto dto);

    /**
     * 新增评论
     *
     * @param dto 数据
     * @return Boolean
     */
    Boolean insert(CommentDto dto);

}