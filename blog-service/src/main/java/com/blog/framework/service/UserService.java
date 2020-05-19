package com.blog.framework.service;

import com.blog.framework.common.PageBean;
import com.blog.framework.dto.user.UserActivationDto;
import com.blog.framework.dto.user.UserAddDto;
import com.blog.framework.dto.user.UserQueryDto;
import com.blog.framework.dto.user.UserRegisterDto;
import com.blog.framework.vo.user.FriendsLinkVo;
import com.blog.framework.vo.user.UserListVo;
import com.blog.framework.vo.user.UserVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户管理
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public interface UserService {

    /**
     * 获取用户列表数据
     *
     * @param dto 查询条件
     * @return PageBean<UserVo>
     */
    PageBean<UserListVo> list(@RequestBody UserQueryDto dto);

    /**
     * 查看个人详情
     *
     * @param id 用户id
     * @return UserVo
     */
    UserVo detail(Long id);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return Boolean
     */
    Boolean delete(Long id);

    /**
     * 个人详情
     *
     * @return UserVo
     */
    UserVo detail();

    /**
     * 修改个人信息
     *
     * @param userAddDto 修改内容
     * @return Boolean
     */
    Boolean update(UserAddDto userAddDto);


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
