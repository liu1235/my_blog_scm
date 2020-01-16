package com.blog.framework.dao.impl;

import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.mapper.BlogMapper;
import com.blog.framework.mapper.ClassMapper;
import com.blog.framework.model.BlogModel;
import com.blog.framework.model.ClassModel;
import com.blog.framework.service.BlogDao;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ClassMapper classMapper;


    @Override
    public List<BlogVO> list(BlogQueryDto dto) {
        List<Long> classIds = new ArrayList<>();
        if (dto.getClassId() != null) {
            classIds.add(dto.getClassId());
            //获取该分类是否有下级分类
            List<ClassModel> list = classMapper.select(ClassModel.builder().parentId(dto.getClassId()).build());
            if (CollectionUtils.isNotEmpty(list)) {
                classIds.addAll(list.stream().map(ClassModel::getId).collect(Collectors.toList()));
            }
        }
        return blogMapper.list(dto, classIds);
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

    @Override
    public List<BlogModel> getByIds(List<Long> blogIds) {
        Example example = new Example(BlogModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", blogIds);
        return blogMapper.selectByExample(example);
    }
}
