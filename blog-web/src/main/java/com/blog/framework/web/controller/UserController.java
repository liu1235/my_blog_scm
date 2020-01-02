package com.blog.framework.web.controller;

import com.blog.framework.common.ResultData;
import com.blog.framework.dto.user.UserActivationDto;
import com.blog.framework.dto.user.UserRegisterDto;
import com.blog.framework.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param dto 注册数据
     * @return ResultData<String>
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public ResultData<String> register(@Validated @RequestBody UserRegisterDto dto) {
        return ResultData.createInsertResult(userService.register(dto));
    }

    /**
     * 激活
     *
     * @param dto 激活
     * @return ResultData<String>
     */
    @PostMapping("/activation")
    @ApiOperation(value = "激活")
    public ResultData<String> activation(@Validated @RequestBody UserActivationDto dto) {
        return ResultData.createUpdateResult(userService.activation(dto));
    }


    /**
     * 发送激活邮件
     *
     * @param dto 数据
     * @return ResultData<String>
     */
    @PostMapping("/sendMail")
    @ApiOperation(value = "发送激活邮件")
    public ResultData<String> sendMail(@Validated @RequestBody UserActivationDto dto) {
        userService.sendMail(dto);
        return ResultData.createSuccessResult();
    }

}
