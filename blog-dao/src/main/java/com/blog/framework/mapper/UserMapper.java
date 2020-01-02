package com.blog.framework.mapper;

import com.blog.framework.model.UserModel;
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
