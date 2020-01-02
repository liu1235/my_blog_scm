package com.blog.framework.service.impl;

import com.blog.framework.common.enums.ResultDataEnum;
import com.blog.framework.common.enums.UserStatusEnum;
import com.blog.framework.common.exception.ServiceException;
import com.blog.framework.common.utils.EncryptMd5Util;
import com.blog.framework.dao.UserDao;
import com.blog.framework.dto.user.UserLoginDto;
import com.blog.framework.model.UserModel;
import com.blog.framework.service.LoginService;
import com.blog.framework.vo.user.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserLoginVo login(UserLoginDto dto) {
        //根据邮箱获取用户信息
        UserModel userModel = userDao.selectByEmail(dto.getEmail());
        if (userModel == null) {
            throw new ServiceException(ResultDataEnum.USERNAME_OR_PASSWORD_IS_WRONG.getCode(),
                    ResultDataEnum.USERNAME_OR_PASSWORD_IS_WRONG.getMsg());
        }
        //密码错误
        String md5 = EncryptMd5Util.getMD5(dto.getPassword());
        if (!userModel.getPassword().equals(md5)) {
            throw new ServiceException(ResultDataEnum.USERNAME_OR_PASSWORD_IS_WRONG.getCode(),
                    ResultDataEnum.USERNAME_OR_PASSWORD_IS_WRONG.getMsg());
        }

        //未激活
        if (UserStatusEnum.INACTIVATED.getCode().equals(userModel.getStatus())) {
            throw new ServiceException(ResultDataEnum.EMAIL_NOT_ACTIVATED.getCode(),
                    ResultDataEnum.EMAIL_NOT_ACTIVATED.getMsg());
        }
        String token = UUID.randomUUID().toString();
        token = token.replace("-", "").toLowerCase();
        //todo 存放用户信息到redis？
        return UserLoginVo.builder()
                .email(userModel.getEmail())
                .userName(userModel.getUserName())
                .token(token)
                .userId(userModel.getId())
                .build();
    }

    @Override
    public void logout() {
        //todo 删除redis 用户数据
    }


}
