package com.blog.framework.service;

import com.blog.framework.dto.sys.user.AdminUserLoginDto;
import com.blog.framework.dto.user.UserLoginDto;
import com.blog.framework.vo.sys.user.SysUserLoginVo;
import com.blog.framework.vo.user.UserLoginVo;

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
     * 登出
     *
     * @return Boolean
     */
    Boolean logout();


    /**
     * 管理员登录
     *
     * @param dto
     * @return
     */
    SysUserLoginVo loginAdmin(AdminUserLoginDto dto);

}
