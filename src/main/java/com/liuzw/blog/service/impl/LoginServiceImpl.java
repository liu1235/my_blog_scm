package com.liuzw.blog.service.impl;

import com.liuzw.blog.dto.UserActivationDto;
import com.liuzw.blog.dto.UserLoginDto;
import com.liuzw.blog.enums.ResultDataEnum;
import com.liuzw.blog.enums.UserStatusEnum;
import com.liuzw.blog.exception.ServiceException;
import com.liuzw.blog.mapper.UserMapper;
import com.liuzw.blog.model.UserModel;
import com.liuzw.blog.service.LoginService;
import com.liuzw.blog.service.UserService;
import com.liuzw.blog.utils.EncryptMd5Util;
import com.liuzw.blog.vo.UserLoginVo;
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
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    @Override
    public UserLoginVo login(UserLoginDto dto) {
        //根据邮箱获取用户信息
        UserModel userModel = userMapper.selectOne(UserModel.builder().email(dto.getEmail()).build());
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
        return UserLoginVo.builder()
                .email(userModel.getEmail())
                .userName(userModel.getUserName())
                .token(token)
                .userId(userModel.getId())
                .build();
    }

    @Override
    public void sendMail(UserActivationDto dto) {
        //根据邮箱获取用户信息
        UserModel userModel = userMapper.selectOne(UserModel.builder().email(dto.getParam()).build());
        if (userModel != null) {
            dto.setParam(userModel.getId() + "_" + userModel.getId());
            userService.sendMail(dto);
        }
    }


}
