package com.blog.framework.web.controller;

import com.blog.framework.common.ResultData;
import com.blog.framework.common.enums.ResultDataEnum;
import com.blog.framework.dto.like.LikeDto;
import com.blog.framework.service.LikeService;
import com.blog.framework.web.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liuzw
 **/
@RestController
@RequestMapping("/like-collect")
@Api(tags = "喜欢和收藏")
public class LikeController {

    @Autowired
    private LikeService likeService;


    /**
     * 喜欢
     *
     * @param dto 博客id
     * @return ResultData<LikeBean>
     */
    @SysLog
    @ApiOperation(value = "喜欢")
    @PostMapping(value = "/like")
    public ResultData<String> like(@Validated @RequestBody LikeDto dto) {
        likeService.like(dto);
        return ResultData.createSelectResult(ResultDataEnum.SUCCESS.getMsg());
    }

    /**
     * 收藏
     *
     * @param dto 收藏
     * @return ResultData<LikeBean>
     */
    @SysLog
    @ApiOperation(value = "收藏")
    @PostMapping(value = "/collect")
    public ResultData<String> collect(@Validated @RequestBody LikeDto dto) {
        likeService.collect(dto);
        return ResultData.createSelectResult(ResultDataEnum.SUCCESS.getMsg());
    }


    /**
     * 喜欢我的数据展示
     *
     * @return ResultData<Integer>
     */
    @SysLog
    @ApiOperation(value = "喜欢我的数据展示")
    @PostMapping(value = "/like-me")
    public ResultData<Integer> likeMeData() {
        return ResultData.createSelectResult(likeService.likeMeData());
    }

    /**
     * 新增喜欢我的数据统计
     *
     * @return ResultData<Integer>
     */
    @SysLog
    @ApiOperation(value = "新增喜欢我的数据统计")
    @PostMapping(value = "/add-like-me")
    public ResultData<Void> addLikeMeData() {
        likeService.addLikeMeData();
        return ResultData.createSelectResult(null);
    }

}