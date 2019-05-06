package com.liuzw.blog.mapper;


import com.liuzw.blog.model.BlogModel;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * interface BlogMapper
 *
 * @author liuzw
 */
public interface BlogMapper extends Mapper<BlogModel>, MySqlMapper<BlogModel> {
		

}