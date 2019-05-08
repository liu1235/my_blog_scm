package com.liuzw.blog.controller;


import com.liuzw.blog.common.ResultData;
import com.liuzw.blog.dto.LikeDto;
import com.liuzw.blog.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author liuzw
 **/
@RestController
@RequestMapping("/like")
@Api(tags = "喜欢和收藏")
public class LikeController {

    @Autowired
    private LikeService likeService;


    /**
     *  收藏
     *
     * @param dto  收藏
     * @return ResultData<LikeBean>
     */
    @ApiOperation(value = "收藏")
    @PostMapping(value = "/collect")
    public ResultData<String> collect(@RequestBody LikeDto dto) {
        return ResultData.createInsertResult(likeService.collect(dto));
    }


}
