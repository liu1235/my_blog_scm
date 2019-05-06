/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.controller;


import com.liuzw.blog.bean.LikeBean;
import com.liuzw.blog.bean.LikeQueryBean;
import com.liuzw.blog.service.LikeService;
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
@RequestMapping("/like")
@Api(description = "管理")
public class LikeController extends BaseController {

    @Autowired
    private LikeService likeService;


    /**
     *  获取所有数据
     *
     * @param bean  LikeBean
     * @return     ResultData<PageInfo<LikeBean>>
     */
     @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
     @PostMapping(value = "/list")
     public ResultData<PageInfo<LikeBean>> getList(@RequestBody LikeQueryBean bean) {
         return  ResultData.createSelectSuccessResult(convertPageInfo(likeService.getList(bean), LikeBean.class));
     }

    /**
     *  根据id获取数据
     *
     * @param id    主键id
     * @return     ResultData<LikeBean>
     */
     @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
     @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "数据Id", paramType = "query", dataType = "Long")})
     @PostMapping(value = "/query")
     public ResultData<LikeBean> query(@RequestBody IdBean idDto) {
        return ResultData.createSelectSuccessResult(CopyDataUtil.copyObject(likeService.getById(idBean.getId()), LikeBean.class));
     }


    /**
     *  添加
     *
     * @param bean   LikeBean
     * @return      ResultData<LikeBean>
     */
     @ApiOperation(value = "增加数据", notes = "增加数据")
     @PostMapping(value = "/add")
     public ResultData<LikeBean> insert(@RequestBody LikeBean bean) {
        return ResultData.createInsertResult(likeService.insert(bean));
     }

    /**
     *  更新
     *
     * @param bean  LikeBean
     * @return     ResultData<LikeBean>
     */
     @ApiOperation(value = "更新数据", notes = "更新数据")
     @PostMapping(value = "/edit")
     public ResultData<LikeBean> update(@RequestBody LikeBean bean) {
        return ResultData.createUpdateResult(likeService.update(bean));
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
         return ResultData.createDeleteResult(likeService.delete(idBean.getId()));
      }

}
