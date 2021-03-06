package com.blog.framework.web.controller;

import com.blog.framework.common.Id;
import com.blog.framework.common.PageBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.classs.ClassAddDto;
import com.blog.framework.dto.classs.ClassQueryDto;
import com.blog.framework.dto.classs.ClassUpdateDto;
import com.blog.framework.service.ClassService;
import com.blog.framework.vo.classs.ClassListVo;
import com.blog.framework.vo.classs.ClassVo;
import com.blog.framework.web.annotation.Permissions;
import com.blog.framework.web.annotation.SysLog;
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
     * 获取分类列表数据
     *
     * @return ResultData
     */
    @SysLog
    @Permissions(permissions = "blog:class:view")
    @ApiOperation(value = "获取分类列表数据")
    @PostMapping(value = "/list")
    public ResultData<List<ClassListVo>> list(@RequestBody ClassQueryDto queryDto) {
        return ResultData.createSelectResult(classService.list(queryDto));
    }

    /**
     * 获取分类详情
     *
     * @return ResultData
     */
    @SysLog
    @Permissions(permissions = "blog:class:view")
    @ApiOperation(value = "获取分类详情")
    @PostMapping(value = "/detail")
    public ResultData<ClassListVo> detail(@Validated @RequestBody Id<Long> idDto) {
        return ResultData.createSelectResult(classService.detail(idDto.getId()));
    }

    /**
     * 获取一级分类数据
     *
     * @return ResultData
     */
    @SysLog
    @ApiOperation(value = "获取一级分类数据")
    @PostMapping(value = "/first")
    public ResultData<List<ClassVo>> first() {
        return ResultData.createSelectResult(classService.first());
    }

    /**
     * 根据一级分类获取二级分类
     *
     * @param idDto 分类id
     * @return ResultData<BlogBean>
     */
    @SysLog
    @ApiOperation(value = "根据一级分类获取二级分类")
    @PostMapping(value = "/second")
    public ResultData<ClassVo> second(@Validated @RequestBody Id<Long> idDto) {
        return ResultData.createSelectResult(classService.second(idDto.getId()));
    }


    /**
     * 新增分类
     *
     * @param addDto 新增数据
     * @return ResultData<BlogBean>
     */
    @SysLog
    @Permissions(permissions = "blog:class:add")
    @ApiOperation(value = "新增分类")
    @PostMapping(value = "/add")
    public ResultData<Boolean> add(@Validated @RequestBody ClassAddDto addDto) {
        return ResultData.createInsertResult(classService.add(addDto));
    }

    /**
     * 编辑分类
     *
     * @param updateDto 编辑数据
     * @return ResultData<BlogBean>
     */
    @SysLog
    @Permissions(permissions = "blog:class:edit")
    @ApiOperation(value = "编辑分类")
    @PostMapping(value = "/edit")
    public ResultData<Boolean> edit(@Validated @RequestBody ClassUpdateDto updateDto) {
        return ResultData.createUpdateResult(classService.edit(updateDto));
    }


    /**
     * 启用
     *
     * @param idDto 分类id
     * @return ResultData<BlogBean>
     */
    @SysLog
    @Permissions(permissions = "blog:class:edit")
    @ApiOperation(value = "修改分类状态")
    @PostMapping(value = "/enable")
    public ResultData<Boolean> enable(@Validated @RequestBody Id<Long> idDto) {
        return ResultData.createUpdateResult(classService.enable(idDto.getId()));
    }

    /**
     * 禁用
     *
     * @param idDto 分类id
     * @return ResultData<BlogBean>
     */
    @SysLog
    @Permissions(permissions = "blog:class:edit")
    @ApiOperation(value = "修改分类状态")
    @PostMapping(value = "/disable")
    public ResultData<Boolean> disable(@Validated @RequestBody Id<Long> idDto) {
        return ResultData.createUpdateResult(classService.disable(idDto.getId()));
    }

    /**
     * 删除分类
     *
     * @param idDto 分类id
     * @return ResultData<BlogBean>
     */
    @SysLog
    @Permissions(permissions = "blog:class:delete")
    @ApiOperation(value = "删除分类")
    @PostMapping(value = "/delete")
    public ResultData<Boolean> delete(@Validated @RequestBody Id<Long> idDto) {
        return ResultData.createDeleteResult(classService.delete(idDto.getId()));
    }


    /**
     * 获取分类下拉菜单数据
     *
     * @return ResultData<List<ClassVo>>
     */
    @SysLog
    @ApiOperation(value = "获取一级分类数据")
    @PostMapping(value = "/selectList")
    public ResultData<List<ClassVo>> selectList() {
        return ResultData.createSelectResult(classService.selectList());
    }
}
