package com.blog.framework.service;


import com.blog.framework.vo.sys.user.SysUserLoginVo;
import com.blog.framework.vo.user.UserLoginVo;

/**
 * @author liuzw
 **/

public interface TokenService {

    /**
     * 保存token信息
     *
     * @param token    token
     * @param userInfo 用户信息实体类
     */
    void saveUserInfo(String token, Object userInfo);

    /**
     * 删除token信息
     *
     * @return Boolean
     */
    Boolean deleteUserInfo();

    /**
     * 检测token是否存在
     *
     * @param tokenStr token
     * @return Boolean
     */
    Boolean checkTokenExists(String tokenStr);

    /**
     * 检测token是否存在
     *
     * @return Boolean
     */
    Boolean checkTokenExists();

    /**
     * 获取当前用户token信息
     *
     * @return UserLoginVo
     */
    UserLoginVo getUserInfo();

    /**
     * 获取当前系统用户token信息
     *
     * @return SysUserLoginVo
     */
    SysUserLoginVo getSysUserInfo();
}
