package com.blog.framework.web.controller;

import com.blog.framework.common.ResultData;
import com.blog.framework.dto.user.UserActivationDto;
import com.blog.framework.dto.user.UserDto;
import com.blog.framework.dto.user.UserRegisterDto;
import com.blog.framework.service.UserService;
import com.blog.framework.vo.user.FriendsLinkVo;
import com.blog.framework.vo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    /**
     * 个人详情
     *
     * @return ResultData<BlogBean>
     */
    @ApiOperation(value = "个人详情", notes = "个人详情")
    @PostMapping(value = "/detail")
    public ResultData<UserVo> detail() {
        return ResultData.createSelectResult(userService.detail());
    }

    /**
     * 修改用户信息
     *
     * @return ResultData<BlogBean>
     */
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping(value = "/update")
    public ResultData<String> update(@Validated @RequestBody UserDto userDto) {
        return ResultData.createUpdateResult(userService.update(userDto));
    }


    /**
     * 获取友链
     *
     * @return ResultData<FriendsLinkVo>
     */
    @ApiOperation(value = "获取友链", notes = "获取友链")
    @PostMapping(value = "/friends-link")
    public ResultData<List<FriendsLinkVo>> friendsLink() {
        return ResultData.createSelectResult(userService.friendsLink());
    }


}
