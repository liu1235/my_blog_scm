package com.blog.framework.web.controller;


import com.blog.framework.common.PageBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.service.CommentService;
import com.blog.framework.vo.CommentVo;
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
@RequestMapping("/comment")
@Api(tags = "评论")
public class CommentController {

    @Autowired
    private CommentService commentService;



    /**
     * 获取评论内容
     *
     * @param dto 查询条件
     * @return ResultData<Page<CommentVo>>
     */
    @ApiOperation(value = "获取评论内容")
    @PostMapping(value = "/list")
    public ResultData<PageBean<CommentVo>> list(@Validated @RequestBody CommentQueryDto dto) {
        return ResultData.createSelectResult(commentService.list(dto));
    }

    /**
     * 获取最新的十条评论
     *
     * @return ResultData<CommentVo>
     */
    @ApiOperation(value = "获取评论内容")
    @PostMapping(value = "/top-comment-list")
    public ResultData<List<CommentVo>> topCommentList() {
        return ResultData.createSelectResult(commentService.topCommentList());
    }

    /**
     * 添加评论
     *
     * @param dto 新增数据
     * @return ResultData<String>
     */
    @ApiOperation(value = "增加数据", notes = "增加数据")
    @PostMapping(value = "/add")
    public ResultData<String> add(@Validated @RequestBody CommentDto dto) {
        return ResultData.createInsertResult(commentService.add(dto));
    }


}
