package com.blog.framework.web.controller.sys;


import com.blog.framework.common.Id;
import com.blog.framework.common.KeyValueBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.sys.menu.SysMenuAddDto;
import com.blog.framework.dto.sys.menu.SysMenuEditDto;
import com.blog.framework.dto.sys.menu.SysMenuQueryDto;
import com.blog.framework.service.sys.SysMenuService;
import com.blog.framework.vo.sys.menu.SysMenuVo;
import com.blog.framework.web.annotation.Permissions;
import com.blog.framework.web.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 菜单管理
 *
 * @author liuzw
 **/
@RestController
@RequestMapping("/sys_menu")
@Api(tags = "菜单管理")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 获取所有菜单列表数据
     *
     * @param queryReq 查询参数
     * @return ResultData<PageBean<SysMenuVo>>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_menu:view")
    @ApiOperation(value = "获取所有菜单列表数据", notes = "获取所有菜单列表数据")
    @PostMapping(value = "/list")
    public ResultData<List<SysMenuVo>> getList(@RequestBody SysMenuQueryDto queryReq) {
        return ResultData.createSelectResult(sysMenuService.getList(queryReq));
    }

    /**
     * 根据id获取菜单详情
     *
     * @param idBean 主键id
     * @return ResultData<SysMenuBean>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_menu:view")
    @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
    @PostMapping(value = "/detail")
    public ResultData<SysMenuVo> detail(@Validated @RequestBody Id<Long> idBean) {
        return ResultData.createSelectResult(sysMenuService.detail(idBean.getId()));
    }

    /**
     * 根据菜单类型获取菜单数据
     *
     * @param type 菜单类型
     * @return ResultData<List<KeyValueBean<String, Long>>>
     */
    @SysLog
    @ApiOperation(value = "获取所有菜单列表数据", notes = "获取所有菜单列表数据")
    @PostMapping(value = "/getMenuByType/{type}")
    public ResultData<List<KeyValueBean<String, Long>>> getMenuByType(@PathVariable("type") Integer type) {
        return ResultData.createSelectResult(sysMenuService.getMenuByType(type));
    }


    /**
     * 添加菜单
     *
     * @param addReq 添加菜单数据
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_menu:add")
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @PostMapping(value = "/add")
    public ResultData<String> add(@Validated @RequestBody SysMenuAddDto addReq) {
        return ResultData.createInsertResult(sysMenuService.add(addReq));
    }

    /**
     * 更新菜单
     *
     * @param editReq 修改菜单数据
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_menu:edit")
    @ApiOperation(value = "更新菜单", notes = "更新菜单")
    @PostMapping(value = "/edit")
    public ResultData<String> edit(@Validated @RequestBody SysMenuEditDto editReq) {
        return ResultData.createUpdateResult(sysMenuService.edit(editReq));
    }

    /**
     * 删除菜单
     *
     * @param idBean 主键id
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_menu:delete")
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @PostMapping(value = "/delete")
    public ResultData<String> delete(@Validated @RequestBody Id<Long> idBean) {
        return ResultData.createDeleteResult(sysMenuService.delete(idBean.getId()));
    }

}
