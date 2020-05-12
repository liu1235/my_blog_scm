
package com.blog.framework.web.controller;


import com.blog.framework.bo.BlogLikeOrCollectBo;
import com.blog.framework.bo.BlogQueryBo;
import com.blog.framework.common.Id;
import com.blog.framework.common.PageBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.service.BlogService;
import com.blog.framework.vo.blog.BlogArchiveVO;
import com.blog.framework.vo.blog.BlogDetailVO;
import com.blog.framework.vo.blog.BlogTopCommentVo;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
        BlogQueryBo bo = CopyDataUtil.copyObject(dto, BlogQueryBo.class);
        return ResultData.createSelectResult(blogService.list(bo));
    }

    /**
     * 获取最热的十条博客
     *
     * @return ResultData
     */
    @ApiOperation(value = "获取最热的十条博客", notes = "获取最热的十条博客")
    @PostMapping(value = "/top-blog-list")
    public ResultData<List<BlogTopVO>> topBlogList() {
        return ResultData.createSelectResult(blogService.topBlogList());
    }

    /**
     * 博客归档
     *
     * @return ResultData
     */
    @ApiOperation(value = "博客归档", notes = "博客归档")
    @PostMapping(value = "/archive")
    public ResultData<List<BlogArchiveVO>> archive() {
        return ResultData.createSelectResult(blogService.archive());
    }


    /**
     * 获取最新的十条评论的博客
     *
     * @return ResultData<List<BlogTopCommentVo>>
     */
    @ApiOperation(value = "获取评论内容")
    @PostMapping(value = "/top-blog-comment-list")
    public ResultData<List<BlogTopCommentVo>> topBlogCommentList() {
        return ResultData.createSelectResult(blogService.topBlogCommentList());
    }


    /**
     * 获取喜欢的博客列表数据
     *
     * @return ResultData
     */
    @ApiOperation(value = "获取喜欢的博客列表数据", notes = "获取喜欢的博客列表数据")
    @PostMapping(value = "/like-blog-list")
    public ResultData<PageBean<BlogVO>> likeBlogList(@RequestBody BlogQueryDto dto) {
        BlogLikeOrCollectBo bo = CopyDataUtil.copyObject(dto, BlogLikeOrCollectBo.class);
        return ResultData.createSelectResult(blogService.likeBlogList(bo));
    }


    /**
     * 获取收藏的博客列表数据
     *
     * @return ResultData
     */
    @ApiOperation(value = "获取收藏的博客列表数据", notes = "获取收藏的博客列表数据")
    @PostMapping(value = "/collect-blog-list")
    public ResultData<PageBean<BlogVO>> collectBlogList(@RequestBody BlogQueryDto dto) {
        BlogLikeOrCollectBo bo = CopyDataUtil.copyObject(dto, BlogLikeOrCollectBo.class);
        return ResultData.createSelectResult(blogService.collectBlogList(bo));
    }

    /**
     * 博客详情
     *
     * @param idDto 博客id
     * @return ResultData<BlogBean>
     */
    @ApiOperation(value = "博客详情", notes = "博客详情")
    @PostMapping(value = "/detail")
    public ResultData<BlogDetailVO> detail(@RequestBody Id<Long> idDto) {
        return ResultData.createSelectResult(blogService.detail(idDto.getId()));
    }

    /**
     * 更新阅读次数
     *
     * @param idDto 博客id
     * @return ResultData<BlogBean>
     */
    @ApiOperation(value = "博客详情", notes = "博客详情")
    @PostMapping(value = "/updateReadCount")
    public ResultData<Boolean> updateReadCount(@RequestBody Id<Long> idDto) {
        return ResultData.createUpdateResult(blogService.updateReadCount(idDto.getId()));
    }

}
