package com.blog.framework.dao.impl;

import com.blog.framework.dao.ReplyDao;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.ReplyDto;
import com.blog.framework.mapper.ReplyMapper;
import com.blog.framework.model.CommentModel;
import com.blog.framework.model.ReplyModel;
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
public class ReplyDaoImpl implements ReplyDao {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<ReplyModel> getReplyByCommentId(List<Long> commentIds) {
        Example example = new Example(ReplyModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("commentId", commentIds);
        return replyMapper.selectByExample(example);
    }

    @Override
    public Boolean add(ReplyModel model) {
        return replyMapper.insertSelective(model) > 0;
    }
}
