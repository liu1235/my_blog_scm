package com.blog.framework.dao.impl;

import com.blog.framework.dao.CommentDao;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.mapper.CommentMapper;
import com.blog.framework.model.CommentModel;
import com.blog.framework.vo.CommentCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(CommentModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (dto.getBlogId() != null) {
            criteria.andEqualTo("blogId", dto.getBlogId());
        }
        criteria.andEqualTo("commentType", dto.getType());
        example.setOrderByClause(" create_time desc");
        return commentMapper.selectByExample(example);
    }

    @Override
    public List<CommentModel> topCommentList() {
        return commentMapper.topCommentList();
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

    @Override
    public List<CommentCountVo> getCommentCountByBlogId(List<Long> blogIds) {
        return commentMapper.getCommentCountByBlogId(blogIds);
    }
}
