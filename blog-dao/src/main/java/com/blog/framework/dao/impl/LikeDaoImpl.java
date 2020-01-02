package com.blog.framework.dao.impl;

import com.blog.framework.dao.LikeDao;
import com.blog.framework.mapper.LikeMapper;
import com.blog.framework.model.LikeModel;
import com.blog.framework.vo.LikeCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * my_blog_scm
 *
 * @author liuzw
 * @date 2020-01-02
 **/
@Repository
public class LikeDaoImpl implements LikeDao {

    @Autowired
    private LikeMapper likeMapper;


    @Override
    public List<LikeModel> select(LikeModel model) {
        return likeMapper.select(model);
    }

    @Override
    public LikeModel getLikeByUserIdAndBlogId(Long blogId, Long userId) {
        return likeMapper.selectOne(LikeModel.builder().blogId(blogId).userId(userId).build());
    }

    @Override
    public List<LikeCountVO> getCountByBlogId(List<Long> blogIds) {
        return likeMapper.getCountByBlogId(blogIds);
    }

    @Override
    public Boolean update(LikeModel model) {
        Example example = new Example(LikeModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blogId", model.getBlogId());
        criteria.andEqualTo("userId", model.getUserId());
        return likeMapper.updateByExampleSelective(model, example) > 0;
    }
}
