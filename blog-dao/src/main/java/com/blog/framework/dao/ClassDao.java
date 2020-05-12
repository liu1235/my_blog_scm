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
     * 根据父分类id获取数据
     *
     * @param parentIds 父分类id
     * @return List<ClassModel>
     */
    List<ClassModel> select(List<Long> parentIds);

    /**
     * 根据父分类id获取数据
     *
     * @param classModel 查询条件
     * @return List<ClassModel>
     */
    List<ClassModel> select(ClassModel classModel);

    /**
     * 根据主键查询
     *
     * @param id 主键id
     * @return ClassModel
     */
    ClassModel selectById(Long id);

    /**
     * 新增分类
     *
     * @param addModel 新增数据
     * @return Boolean
     */
    Boolean add(ClassModel addModel);

    /**
     * 编辑分类
     *
     * @param updateModel 编辑分类
     * @return Boolean
     */
    Boolean edit(ClassModel updateModel);

    /**
     * 删除分类
     *
     * @param id 分类id
     * @return Boolean
     */
    Boolean deleteById(Long id);


}
