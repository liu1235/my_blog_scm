
package com.blog.framework.web.controller;


import com.blog.framework.common.Id;
import com.blog.framework.common.PageBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.dto.blog.manage.BlogAddDto;
import com.blog.framework.dto.blog.manage.BlogUpdateDto;
import com.blog.framework.service.BlogService;
import com.blog.framework.vo.blog.manage.BlogListVO;
import com.blog.framework.vo.blog.manage.BlogManageDetailVO;
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
 * @author liuzw
 **/
@RestController
@RequestMapping("/manage/blog")
@Api(tags = "博客列表")
public class BlogManageController {

    @Autowired
    private BlogService blogService;


    /**
     * 获取所有博客数据
     *
     * @param dto 查询参数
     * @return ResultData
     */
    @ApiOperation(value = "获取所有博客数据", notes = "获取所有博客数据")
    @PostMapping(value = "/list")
    public ResultData<PageBean<BlogListVO>> list(@RequestBody BlogQueryDto dto) {
        return ResultData.createSelectResult(blogService.list(dto));
    }

    /**
     * 博客详情
     *
     * @param idDto 博客id
     * @return ResultData<BlogBean>
     */
    @ApiOperation(value = "博客详情", notes = "博客详情")
    @PostMapping(value = "/detail")
    public ResultData<BlogManageDetailVO> detail(@Validated @RequestBody Id<Long> idDto) {
        return ResultData.createSelectResult(blogService.detailBlog(idDto.getId()));
    }

    /**
     * 新增博客
     *
     * @param addDto 新增数据
     * @return ResultData<Boolean>
     */
    @ApiOperation(value = "新增博客")
    @PostMapping(value = "/add")
    public ResultData<Boolean> add(@Validated @RequestBody BlogAddDto addDto) {
        return ResultData.createInsertResult(blogService.add(addDto));
    }

    /**
     * 编辑博客
     *
     * @param updateDto 编辑数据
     * @return ResultData<Boolean>
     */
    @ApiOperation(value = "编辑博客")
    @PostMapping(value = "/edit")
    public ResultData<Boolean> edit(@Validated @RequestBody BlogUpdateDto updateDto) {
        return ResultData.createUpdateResult(blogService.edit(updateDto));
    }

    /**
     * 发布博客
     *
     * @param listId 博客id
     * @return ResultData<Boolean>
     */
    @ApiOperation(value = "发布博客")
    @PostMapping(value = "/published")
    public ResultData<Boolean> published(@Validated @RequestBody Id<List<Long>> listId) {
        return ResultData.createUpdateResult(blogService.published(listId.getId()));
    }

    /**
     * 取消发布的博客
     *
     * @param listId 博客id
     * @return ResultData<Boolean>
     */
    @ApiOperation(value = "取消发布的博客")
    @PostMapping(value = "/unpublished")
    public ResultData<Boolean> unpublished(@Validated @RequestBody Id<List<Long>> listId) {
        return ResultData.createUpdateResult(blogService.unpublished(listId.getId()));
    }

    /**
     * 删除博客
     *
     * @param idDto 博客id
     * @return ResultData<Boolean>
     */
    @ApiOperation(value = "删除博客")
    @PostMapping(value = "/delete")
    public ResultData<Boolean> delete(@Validated @RequestBody Id<Long> idDto) {
        return ResultData.createDeleteResult(blogService.delete(idDto.getId()));
    }
}
