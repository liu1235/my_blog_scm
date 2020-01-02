package com.blog.framework.dao.impl;

import com.blog.framework.dao.UserDao;
import com.blog.framework.mapper.UserMapper;
import com.blog.framework.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * my_blog_scm
 *
 * @author liuzw
 * @date 2020-01-02
 **/

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserModel selectByEmail(String email) {
        return userMapper.selectOne(UserModel.builder().email(email).build());
    }

    @Override
    public UserModel selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean add(UserModel userModel) {
        return userMapper.insertSelective(userModel) > 0;
    }

    @Override
    public Boolean updateById(UserModel userModel) {
        return userMapper.updateByPrimaryKeySelective(userModel) > 0;
    }
}
