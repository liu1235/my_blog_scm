package com.blog.framework.mapper;

import com.blog.framework.model.ClassModel;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 分类
 *
 * @author liuzw
 **/
public interface ClassMapper extends Mapper<ClassModel>, MySqlMapper<ClassModel> {
}
