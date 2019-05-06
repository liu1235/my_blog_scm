package com.liuzw.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Api(tags = "测试")
@RequestMapping
@RestController
public class TestController {

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public String test() {
        return "测试........";
    }
}
