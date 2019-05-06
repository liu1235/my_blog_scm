package com.liuzw.blog.mapper;


import com.liuzw.blog.model.CommentModel;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * interface CommentMapper
 *
 * @author liuzw
 */
public interface CommentMapper extends Mapper<CommentModel>, MySqlMapper<CommentModel> {
		

}