package com.blog.framework.dao.impl;

import com.blog.framework.dao.ClassDao;
import com.blog.framework.mapper.ClassMapper;
import com.blog.framework.model.ClassModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-14
 **/
@Repository
public class ClassDaoImpl implements ClassDao {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<ClassModel> select(Long parentId) {
        return classMapper.select(ClassModel.builder().parentId(parentId).build());
    }

    @Override
    public ClassModel selectById(Long id) {
        return classMapper.selectByPrimaryKey(id);
    }

}
