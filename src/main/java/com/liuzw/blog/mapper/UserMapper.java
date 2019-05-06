package com.liuzw.blog.mapper;

import com.liuzw.blog.model.UserModel;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public interface UserMapper extends Mapper<UserModel>, MySqlMapper<UserModel> {
}
