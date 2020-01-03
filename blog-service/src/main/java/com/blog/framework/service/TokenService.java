package com.blog.framework.service;


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
    void saveUserInfo(String token, UserLoginVo userInfo);

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
     * 获取当前token信息
     *
     * @return UserBean
     */
    UserLoginVo getUserInfo();

}
