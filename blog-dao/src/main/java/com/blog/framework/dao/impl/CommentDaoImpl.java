package com.blog.framework.dao.impl;

import com.blog.framework.dao.CommentDao;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.mapper.CommentMapper;
import com.blog.framework.mapper.ReplyMapper;
import com.blog.framework.model.CommentModel;
import com.blog.framework.model.ReplyModel;
import com.blog.framework.vo.comment.CommentCountVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private ReplyMapper replyMapper;

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
        if (CollectionUtils.isEmpty(blogIds)) {
            return Collections.emptyList();
        }
        //统计评论数量
        Example example = new Example(CommentModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("blogId", blogIds);
        List<CommentModel> commentList = commentMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(commentList)) {
            return Collections.emptyList();
        }
        //评论id
        List<Long> commentIds = commentList.stream().map(CommentModel::getId).collect(Collectors.toList());

        Example replyExample = new Example(ReplyModel.class);
        Example.Criteria replyCriteria = replyExample.createCriteria();
        replyCriteria.andIn("commentId", commentIds);
        List<ReplyModel> replyList = replyMapper.selectByExample(replyExample);
        //评论
        Map<Long, List<CommentModel>> commentMap = commentList.stream().collect(Collectors.groupingBy(CommentModel::getBlogId));
        //回复
        Map<Long, List<ReplyModel>> replyMap = new HashMap<>(commentIds.size());

        if (CollectionUtils.isNotEmpty(replyList)) {
            replyMap.putAll(replyList.stream().collect(Collectors.groupingBy(ReplyModel::getCommentId)));
        }
        //组装数据
        List<CommentCountVo> list = new ArrayList<>(blogIds.size());
        for (Map.Entry<Long, List<CommentModel>> entry : commentMap.entrySet()) {
            Long blogId = entry.getKey();
            List<CommentModel> value = entry.getValue();
            int count = value.size();
            for (CommentModel model : value) {
                List<ReplyModel> replyModels = replyMap.get(model.getId());
                if (CollectionUtils.isNotEmpty(replyModels)) {
                    count += replyModels.size();
                }
            }
            list.add(CommentCountVo.builder()
                    .blogId(blogId)
                    .commentCount(count)
                    .build());
        }

        return list;
    }
}
