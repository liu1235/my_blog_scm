package com.liuzw.blog.controller;


import com.liuzw.blog.common.Page;
import com.liuzw.blog.common.ResultData;
import com.liuzw.blog.dto.CommentDto;
import com.liuzw.blog.dto.CommentQueryDto;
import com.liuzw.blog.service.CommentService;
import com.liuzw.blog.vo.CommentVo;
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
    @PostMapping(value = "/getList")
    public ResultData<Page<CommentVo>> getList(@Validated @RequestBody CommentQueryDto dto) {
        return ResultData.createSelectResult(commentService.getList(dto));
    }

    /**
     * 添加评论
     *
     * @param dto 新增数据
     * @return ResultData<String>
     */
    @ApiOperation(value = "增加数据", notes = "增加数据")
    @PostMapping(value = "/add")
    public ResultData<String> insert(@Validated @RequestBody CommentDto dto) {
        return ResultData.createInsertResult(commentService.insert(dto));
    }


}
