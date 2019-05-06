package com.liuzw.blog.service;

import com.liuzw.blog.dto.UserActivationDto;
import com.liuzw.blog.dto.UserLoginDto;
import com.liuzw.blog.dto.UserRegisterDto;
import com.liuzw.blog.vo.UserLoginVo;

/**
 * 登录,注册,激活
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public interface LoginService {


    /**
     * 登录
     *
     * @param dto 登录数据
     * @return UserLoginVo
     */
    UserLoginVo login(UserLoginDto dto);


    /**
     * 发送激活邮件
     *
     * @param dto 邮箱
     */
    void sendMail(UserActivationDto dto);

}
