/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.controller;


import com.liuzw.blog.bean.CommentBean;
import com.liuzw.blog.bean.CommentQueryBean;
import com.liuzw.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * @liuzw
 **/
@RestController
@RequestMapping("/comment")
@Api(description = "管理")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;


    /**
     *  获取所有数据
     *
     * @param bean  CommentBean
     * @return     ResultData<PageInfo<CommentBean>>
     */
     @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
     @PostMapping(value = "/list")
     public ResultData<PageInfo<CommentBean>> getList(@RequestBody CommentQueryBean bean) {
         return  ResultData.createSelectSuccessResult(convertPageInfo(commentService.getList(bean), CommentBean.class));
     }

    /**
     *  根据id获取数据
     *
     * @param id    主键id
     * @return     ResultData<CommentBean>
     */
     @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
     @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "数据Id", paramType = "query", dataType = "Long")})
     @PostMapping(value = "/query")
     public ResultData<CommentBean> query(@RequestBody IdBean idDto) {
        return ResultData.createSelectSuccessResult(CopyDataUtil.copyObject(commentService.getById(idBean.getId()), CommentBean.class));
     }


    /**
     *  添加
     *
     * @param bean   CommentBean
     * @return      ResultData<CommentBean>
     */
     @ApiOperation(value = "增加数据", notes = "增加数据")
     @PostMapping(value = "/add")
     public ResultData<CommentBean> insert(@RequestBody CommentBean bean) {
        return ResultData.createInsertResult(commentService.insert(bean));
     }

    /**
     *  更新
     *
     * @param bean  CommentBean
     * @return     ResultData<CommentBean>
     */
     @ApiOperation(value = "更新数据", notes = "更新数据")
     @PostMapping(value = "/edit")
     public ResultData<CommentBean> update(@RequestBody CommentBean bean) {
        return ResultData.createUpdateResult(commentService.update(bean));
     }


     /**
      *  删除
      *
      * @param  idDto  主键id
      * @return        ResultData<String>
      */
      @ApiOperation(value = "删除数据", notes = "删除数据")
      @PostMapping(value = "/remove")
      public ResultData<Void> delete(@RequestBody IdBean idBean) {
         return ResultData.createDeleteResult(commentService.delete(idBean.getId()));
      }

}
