package com.blog.framework.dao.impl;

import com.blog.framework.model.BlogModel;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.mapper.BlogMapper;
import com.blog.framework.service.BlogDao;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
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
public class BlogDaoImpl implements BlogDao {

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public List<BlogVO> list(BlogQueryDto dto) {
        return blogMapper.list(dto);
    }

    @Override
    public List<BlogTopVO> topBlogList() {
        return blogMapper.topBlogList();
    }

    @Override
    public BlogModel detail(Long id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(BlogModel model) {
        return blogMapper.updateByPrimaryKeySelective(model) > 0;
    }
}
