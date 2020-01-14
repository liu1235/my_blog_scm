package com.blog.framework.dao;

import com.blog.framework.model.ClassModel;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-14
 **/
public interface ClassDao {


    /**
     * 根据父分类id获取数据
     *
     * @param parentId 父分类id
     * @return List<ClassModel>
     */
    List<ClassModel> select(Long parentId);


    /**
     * 根据主键查询
     *
     * @param id 主键id
     * @return ClassModel
     */
    ClassModel selectById(Long id);


}
