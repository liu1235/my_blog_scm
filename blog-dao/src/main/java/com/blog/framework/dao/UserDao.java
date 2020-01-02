package com.blog.framework.dao;

import com.blog.framework.model.UserModel;

/**
 * my_blog_scm
 *
 * @author liuzw
 * @date 2020-01-02
 **/
public interface UserDao {

    /**
     * 根据邮箱获取用户
     *
     * @param email 邮箱
     * @return UserModel
     */
    UserModel selectByEmail(String email);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return UserModel
     */
    UserModel selectByPrimaryKey(Long id);

    /**
     * 注册用户
     *
     * @param userModel 用户数据
     * @return Boolean
     */
    Boolean add(UserModel userModel);


    /**
     * 注册用户
     *
     * @param userModel 用户数据
     * @return Boolean
     */
    Boolean updateById(UserModel userModel);
}
