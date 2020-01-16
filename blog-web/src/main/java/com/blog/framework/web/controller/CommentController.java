package com.blog.framework.web.controller;


import com.blog.framework.common.PageBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.comment.CommentDto;
import com.blog.framework.dto.comment.CommentQueryDto;
import com.blog.framework.dto.comment.ReplyDto;
import com.blog.framework.service.CommentService;
import com.blog.framework.vo.comment.CommentVo;
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
    public ResultData<CommentVo> list(@Validated @RequestBody CommentQueryDto dto) {
        return ResultData.createSelectResult(commentService.list(dto));
    }


    /**
     * 添加评论
     *
     * @param dto 添加评论
     * @return ResultData<String>
     */
    @ApiOperation(value = "添加评论", notes = "添加评论")
    @PostMapping(value = "/add")
    public ResultData<String> add(@Validated @RequestBody CommentDto dto) {
        return ResultData.createInsertResult(commentService.add(dto));
    }

    /**
     * 添加回复
     *
     * @param dto 新增数据
     * @return ResultData<String>
     */
    @ApiOperation(value = "添加回复", notes = "添加回复")
    @PostMapping(value = "/add-reply")
    public ResultData<String> addReply(@Validated @RequestBody ReplyDto dto) {
        return ResultData.createInsertResult(commentService.addReply(dto));
    }

}
