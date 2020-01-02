
package com.blog.framework.web.controller;


import com.blog.framework.common.PageBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.service.BlogService;
import com.blog.framework.vo.blog.BlogDetailVO;
import com.blog.framework.vo.blog.BlogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author liuzw
 **/
@RestController
@RequestMapping("/blog")
@Api(tags = "博客列表")
public class BlogController {

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
    public ResultData<PageBean<BlogVO>> list(@RequestBody BlogQueryDto dto) {
        return ResultData.createSelectResult(blogService.list(dto));
    }

    /**
     * 获取喜欢的博客列表数据
     *
     * @return ResultData
     */
    @ApiOperation(value = "获取喜欢的博客列表数据", notes = "获取喜欢的博客列表数据")
    @PostMapping(value = "/like-blog-list")
    public ResultData<PageBean<BlogVO>> likeBlogList() {
        return ResultData.createSelectResult(blogService.likeBlogList());
    }


    /**
     * 获取收藏的博客列表数据
     *
     * @return ResultData
     */
    @ApiOperation(value = "获取收藏的博客列表数据", notes = "获取收藏的博客列表数据")
    @PostMapping(value = "/collect-blog-List")
    public ResultData<PageBean<BlogVO>> collectBlogList() {
        return ResultData.createSelectResult(blogService.collectBlogList());
    }

    /**
     * 博客详情
     *
     * @param id 博客id
     * @return ResultData<BlogBean>
     */
    @ApiOperation(value = "博客详情", notes = "博客详情")
    @GetMapping(value = "/detail")
    public ResultData<BlogDetailVO> detail(@RequestParam("bid") Long id) {
        return ResultData.createSelectResult(blogService.detail(id));
    }

}
