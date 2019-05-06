package com.liuzw.blog.service.impl;

import com.liuzw.blog.dto.MailDto;
import com.liuzw.blog.dto.UserActivationDto;
import com.liuzw.blog.dto.UserRegisterDto;
import com.liuzw.blog.enums.ResultDataEnum;
import com.liuzw.blog.enums.UserStatusEnum;
import com.liuzw.blog.exception.ServiceException;
import com.liuzw.blog.mapper.UserMapper;
import com.liuzw.blog.model.UserModel;
import com.liuzw.blog.service.MailService;
import com.liuzw.blog.service.UserService;
import com.liuzw.blog.utils.EncryptMd5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    @Value("${activation-address}")
    private String activationAddress;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(UserRegisterDto dto) {
        //根据邮箱获取用户信息
        UserModel userModel = userMapper.selectOne(UserModel.builder().email(dto.getEmail()).build());
        //判断用户是否存在
        if (userModel != null) {
            throw new ServiceException(ResultDataEnum.ACCOUNT_EXISTS.getCode(),
                    ResultDataEnum.ACCOUNT_EXISTS.getMsg());
        }

        String activationCode = UUID.randomUUID().toString().replace("-", "");


        UserModel build = UserModel.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(EncryptMd5Util.getMD5(dto.getPassword()))
                .activationCode(activationCode)
                .build();

        boolean flag = userMapper.insertSelective(build) > 0;

        //发送邮件
        MailDto mailDto = MailDto.builder()
                .userName(dto.getUserName())
                .url(activationAddress.replace("{1}", activationCode + "_" + build.getId()))
                .build();
        mailService.sendTemplateMail(dto.getEmail(), mailDto);
        //注册
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean activation(UserActivationDto dto) {
        String[] split = getParams(dto.getParam());
        //激活码
        String activationCode = split[0];

        Long id = Long.valueOf(split[1]);

        //根据邮箱获取用户信息
        UserModel userModel = userMapper.selectByPrimaryKey(id);
        if (userModel == null) {
            throw new ServiceException(ResultDataEnum.MAIL_ERROR.getCode(),
                    ResultDataEnum.MAIL_ERROR.getMsg());
        }

        //进行激活码验证
        if (!userModel.getActivationCode().equals(activationCode)) {
            throw new ServiceException(ResultDataEnum.ILLEGAL_ACTIVATION.getCode(),
                    ResultDataEnum.ILLEGAL_ACTIVATION.getMsg());
        }

        //创建时间
        Date createTime = userModel.getUpdateTime();
        //当前时间
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime localDateTime = today.minusDays(1);
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        Date date = Date.from(zdt.toInstant());
        //判断时间是否过期24小时
        if (createTime.before(date)) {
            throw new ServiceException(ResultDataEnum.ACCOUNT_ACTIVATION_TIMEOUT.getCode(),
                    ResultDataEnum.ACCOUNT_ACTIVATION_TIMEOUT.getMsg());
        }

        UserModel build = UserModel.builder()
                .id(id)
                .status(UserStatusEnum.ACTIVATED.getCode())
                .updateTime(new Date())
                .build();
        return userMapper.updateByPrimaryKeySelective(build) > 0;
    }

    @Override
    public void sendMail(UserActivationDto dto) {
        String[] split = getParams(dto.getParam());

        Long id = Long.valueOf(split[1]);

        //根据邮箱获取用户信息
        UserModel userModel = userMapper.selectByPrimaryKey(id);
        if (userModel == null) {
            return;
        }
        String activationCode = UUID.randomUUID().toString().replace("-", "");

        //发送邮件
        MailDto mailDto = MailDto.builder()
                .userName(userModel.getUserName())
                .url(activationAddress.replace("{1}", activationCode + "_" + id))
                .build();
        mailService.sendTemplateMail(userModel.getEmail(), mailDto);

        // 更新激活码
        UserModel build = UserModel.builder()
                .id(id)
                .activationCode(activationCode)
                .updateTime(new Date())
                .build();
        userMapper.updateByPrimaryKeySelective(build);

    }


    private String[] getParams(String param) {
        String[] split = param.split("_");
        if (split.length < 2) {
            throw new ServiceException(ResultDataEnum.ILLEGAL_ACTIVATION.getCode(),
                    ResultDataEnum.ILLEGAL_ACTIVATION.getMsg());
        }
        return split;
    }

}
