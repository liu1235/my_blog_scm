package com.blog.framework.service;

import com.blog.framework.vo.ClassVo;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-14
 **/
public interface ClassService {


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

}
