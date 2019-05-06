package com.liuzw.blog.service;

import com.liuzw.blog.dto.UserActivationDto;
import com.liuzw.blog.dto.UserRegisterDto;

/**
 * 用户管理
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public interface UserService {


    /**
     * 注册
     *
     * @param dto 注册数据
     * @return Boolean
     */
    Boolean register(UserRegisterDto dto);

    /**
     * 激活
     *
     * @param dto 激活参数
     * @return Boolean
     */
    Boolean activation(UserActivationDto dto);

    /**
     * 发送激活邮件
     *
     * @param dto 数据
     */
    void  sendMail(UserActivationDto dto);
}
