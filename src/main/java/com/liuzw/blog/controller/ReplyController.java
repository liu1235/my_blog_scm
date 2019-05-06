/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.controller;


import com.liuzw.blog.bean.ReplyBean;
import com.liuzw.blog.bean.ReplyQueryBean;
import com.liuzw.blog.service.ReplyService;
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
@RequestMapping("/reply")
@Api(description = "管理")
public class ReplyController extends BaseController {

    @Autowired
    private ReplyService replyService;


    /**
     *  获取所有数据
     *
     * @param bean  ReplyBean
     * @return     ResultData<PageInfo<ReplyBean>>
     */
     @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
     @PostMapping(value = "/list")
     public ResultData<PageInfo<ReplyBean>> getList(@RequestBody ReplyQueryBean bean) {
         return  ResultData.createSelectSuccessResult(convertPageInfo(replyService.getList(bean), ReplyBean.class));
     }

    /**
     *  根据id获取数据
     *
     * @param id    主键id
     * @return     ResultData<ReplyBean>
     */
     @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
     @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "数据Id", paramType = "query", dataType = "Long")})
     @PostMapping(value = "/query")
     public ResultData<ReplyBean> query(@RequestBody IdBean idDto) {
        return ResultData.createSelectSuccessResult(CopyDataUtil.copyObject(replyService.getById(idBean.getId()), ReplyBean.class));
     }


    /**
     *  添加
     *
     * @param bean   ReplyBean
     * @return      ResultData<ReplyBean>
     */
     @ApiOperation(value = "增加数据", notes = "增加数据")
     @PostMapping(value = "/add")
     public ResultData<ReplyBean> insert(@RequestBody ReplyBean bean) {
        return ResultData.createInsertResult(replyService.insert(bean));
     }

    /**
     *  更新
     *
     * @param bean  ReplyBean
     * @return     ResultData<ReplyBean>
     */
     @ApiOperation(value = "更新数据", notes = "更新数据")
     @PostMapping(value = "/edit")
     public ResultData<ReplyBean> update(@RequestBody ReplyBean bean) {
        return ResultData.createUpdateResult(replyService.update(bean));
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
         return ResultData.createDeleteResult(replyService.delete(idBean.getId()));
      }

}
