package com.blog.framework.service.impl;

import com.blog.framework.common.enums.ResultDataEnum;
import com.blog.framework.common.enums.UserStatusEnum;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.common.exception.ServiceException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.common.utils.EncryptMd5Util;
import com.blog.framework.dao.UserDao;
import com.blog.framework.dto.MailDto;
import com.blog.framework.dto.user.UserActivationDto;
import com.blog.framework.dto.user.UserDto;
import com.blog.framework.dto.user.UserRegisterDto;
import com.blog.framework.model.UserModel;
import com.blog.framework.service.MailService;
import com.blog.framework.service.TokenService;
import com.blog.framework.service.UserService;
import com.blog.framework.vo.user.UserLoginVo;
import com.blog.framework.vo.user.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
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
    private UserDao userDao;

    @Autowired
    private MailService mailService;

    @Value("${activation-address}")
    private String activationAddress;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserVo detail() {
        UserLoginVo userInfo = tokenService.getUserInfo();
        if (userInfo == null) {
            throw new LoginException();
        }
        UserModel userModel = userDao.selectByPrimaryKey(userInfo.getUserId());
        UserVo userVo = CopyDataUtil.copyObject(userModel, UserVo.class);
        if (StringUtils.isNotBlank(userModel.getTags())) {
            userVo.setTags(Arrays.asList(userModel.getTags().split(",")));
        }
        return userVo;
    }

    @Override
    public Boolean update(UserDto userDto) {
        UserLoginVo userInfo = tokenService.getUserInfo();
        if (userInfo == null) {
            throw new LoginException();
        }
        UserModel userModel = CopyDataUtil.copyObject(userDto, UserModel.class);
        userModel.setId(userInfo.getUserId());
        if (CollectionUtils.isNotEmpty(userDto.getTags())) {
            userModel.setTags(StringUtils.join(userDto.getTags(), ","));
        }
        return userDao.updateUserInfo(userModel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(UserRegisterDto dto) {
        //根据邮箱获取用户信息
        UserModel userModel = userDao.selectByEmail(dto.getEmail());
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
        //注册
        boolean flag = userDao.add(build);

        if (flag) {
            //发送邮件
            MailDto mailDto = MailDto.builder()
                    .userName(dto.getUserName())
                    .url(activationAddress.replace("{1}", activationCode + "_" + build.getId()))
                    .build();
            mailService.sendTemplateMail(dto.getEmail(), mailDto);
        }

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
        UserModel userModel = userDao.selectByPrimaryKey(id);
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
        Date createTime = userModel.getCreateTime();
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
        return userDao.updateById(build);
    }

    @Override
    public void sendMail(UserActivationDto dto) {
        String[] split = getParams(dto.getParam());
        Long id = Long.valueOf(split[1]);
        //根据邮箱获取用户信息
        UserModel userModel = userDao.selectByPrimaryKey(id);
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
        userDao.updateById(build);
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
