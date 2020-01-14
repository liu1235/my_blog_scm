package com.blog.framework.web.controller;

import com.blog.framework.common.Id;
import com.blog.framework.common.ResultData;
import com.blog.framework.service.ClassService;
import com.blog.framework.vo.ClassVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类管理
 *
 * @author liuzw
 * @date 2020-01-14
 **/
@Api(tags = "分类管理")
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;


    /**
     * 获取一级分类数据
     *
     * @return ResultData
     */
    @ApiOperation(value = "获取一级分类数据", notes = "获取一级分类数据")
    @PostMapping(value = "/first")
    public ResultData<List<ClassVo>> first() {
        return ResultData.createSelectResult(classService.first());
    }

    /**
     * 根据一级分类获取二级分类
     *
     * @param idDto 博客id
     * @return ResultData<BlogBean>
     */
    @ApiOperation(value = "根据一级分类获取二级分类", notes = "根据一级分类获取二级分类")
    @PostMapping(value = "/second")
    public ResultData<ClassVo> second(@RequestBody Id<Long> idDto) {
        return ResultData.createSelectResult(classService.second(idDto.getId()));
    }
}
