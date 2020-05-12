package com.blog.framework.service;

import com.blog.framework.dto.user.UserActivationDto;
import com.blog.framework.dto.user.UserDto;
import com.blog.framework.dto.user.UserRegisterDto;
import com.blog.framework.vo.user.FriendsLinkVo;
import com.blog.framework.vo.user.UserVo;

import java.util.List;

/**
 * 用户管理
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public interface UserService {

    /**
     * 个人详情
     *
     * @return UserVo
     */
    UserVo detail();

    /**
     * 修改个人信息
     *
     * @param userDto 修改内容
     * @return Boolean
     */
    Boolean update(UserDto userDto);


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
    void sendMail(UserActivationDto dto);


    /**
     * 获取友链
     *
     * @return FriendsLinkVo
     */
    List<FriendsLinkVo> friendsLink();
}
