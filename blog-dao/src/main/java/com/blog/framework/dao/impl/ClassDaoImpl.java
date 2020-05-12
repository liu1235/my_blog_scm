package com.blog.framework.dao.impl;

import com.blog.framework.dao.ClassDao;
import com.blog.framework.mapper.ClassMapper;
import com.blog.framework.model.ClassModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

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
    public List<ClassModel> select(List<Long> parentIds) {
        Example example = new Example(ClassModel.class);
        example.createCriteria().andIn("parentId", parentIds);
        return classMapper.selectByExample(example);
    }

    @Override
    public List<ClassModel> select(ClassModel classModel) {
        return classMapper.select(classModel);
    }

    @Override
    public ClassModel selectById(Long id) {
        return classMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean add(ClassModel addModel) {
        return classMapper.insertSelective(addModel) > 0;
    }

    @Override
    public Boolean edit(ClassModel updateModel) {
        return classMapper.updateByPrimaryKeySelective(updateModel) > 0;
    }

    @Override
    public Boolean deleteById(Long id) {
        return classMapper.deleteByPrimaryKey(id) > 0;
    }

}
