package com.liuzw.blog.controller;

import com.liuzw.blog.common.ResultData;
import com.liuzw.blog.dto.UserActivationDto;
import com.liuzw.blog.dto.UserLoginDto;
import com.liuzw.blog.service.LoginService;
import com.liuzw.blog.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录,注册,激活
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Api(tags = "登录,激活")
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * 登录
     *
     * @param dto 登录数据
     * @return ResultData<String>
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultData<UserLoginVo> login(@Validated @RequestBody UserLoginDto dto) {
        return ResultData.createSelectResult(loginService.login(dto));
    }


    /**
     * 发送激活邮件
     *
     * @param dto 邮箱
     * @return ResultData<String>
     */
    @PostMapping("/sendMail")
    @ApiOperation(value = "发送激活邮件")
    public ResultData<String> sendMail(@Validated @RequestBody UserActivationDto dto) {
        loginService.sendMail(dto);
        return ResultData.createSuccessResult();
    }
}
