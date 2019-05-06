
package com.liuzw.blog.controller;


import com.github.pagehelper.PageInfo;
import com.liuzw.blog.common.Id;
import com.liuzw.blog.common.ResultData;
import com.liuzw.blog.service.BlogService;
import com.liuzw.blog.utils.CopyDataUtil;
import com.liuzw.blog.vo.BlogDetailVO;
import com.liuzw.blog.vo.BlogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liuzw
 **/
@RestController
@RequestMapping("/blog")
@Api(description = "博客列表")
public class BlogController {

    @Autowired
    private BlogService blogService;


    /**
     * 获取所有博客数据
     *
     * @param bean BlogBean
     * @return ResultData<PageInfo       <       BlogBean>>
     */
    @ApiOperation(value = "获取所有博客数据", notes = "获取所有博客数据")
    @PostMapping(value = "/list")
    public ResultData<PageInfo<BlogVO>> getList(@RequestBody BlogQueryBean bean) {
        return ResultData.createSelectResult(blogService.getList(bean));
    }

    /**
     * 根据id获取数据
     *
     * @param idDto 主键id
     * @return ResultData<BlogBean>
     */
    @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
    @PostMapping(value = "/query")
    public ResultData<BlogDetailVO> query(@RequestBody Id<Long> idDto) {
        return ResultData.createSelectResult();
    }

}
