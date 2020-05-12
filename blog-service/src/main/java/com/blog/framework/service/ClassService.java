package com.blog.framework.service;

import com.blog.framework.dto.classs.ClassAddDto;
import com.blog.framework.dto.classs.ClassQueryDto;
import com.blog.framework.dto.classs.ClassUpdateDto;
import com.blog.framework.vo.classs.ClassListVo;
import com.blog.framework.vo.classs.ClassVo;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-14
 **/
public interface ClassService {

    /**
     * 获取分类列表数据
     *
     * @return PageBean<ClassListVo>
     */
    List<ClassListVo> list(ClassQueryDto queryDto);

    /**
     * 获取分类详情
     *
     * @return PageBean<ClassListVo>
     */
    ClassListVo detail(Long id);

    /**
     * 获取一级分类数据
     *
     * @return ResultData
     */
    List<ClassVo> first();

    /**
     * 根据一级分类获取二级分类
     *
     * @param classId 一级分类id
     * @return ClassVo
     */
    ClassVo second(Long classId);

    /**
     * 新增分类
     *
     * @param addDto 新增数据
     * @return Boolean
     */
    Boolean add(ClassAddDto addDto);

    /**
     * 编辑分类
     *
     * @param updateDto 编辑分类
     * @return Boolean
     */
    Boolean edit(ClassUpdateDto updateDto);

    /**
     * 删除分类
     *
     * @param id 分类id
     * @return Boolean
     */
    Boolean delete(Long id);

    /**
     * 获取分类下拉菜单数据
     *
     * @return List<ClassVo>
     */
    List<ClassVo> selectList();

}
