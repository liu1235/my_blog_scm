package com.blog.framework.dao.impl;

import com.blog.framework.dao.CommentDao;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.mapper.CommentMapper;
import com.blog.framework.model.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-02
 **/
@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentModel> list(CommentQueryDto dto) {
        return commentMapper.select(CommentModel.builder()
                .blogId(dto.getBlogId())
                .commentType(dto.getType())
                .build());
    }

    @Override
    public Boolean add(CommentDto dto) {
        CommentModel model = CommentModel.builder()
                .commentType(dto.getCommentType())
                .content(dto.getContent())
                .userId(dto.getUserId())
                .blogId(dto.getBlogId())
                .build();
        return commentMapper.insertSelective(model) > 0;
    }
}
