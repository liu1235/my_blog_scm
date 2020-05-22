package com.blog.framework.web.controller.sys;


import com.blog.framework.common.Id;
import com.blog.framework.common.KeyValueBean;
import com.blog.framework.common.PageBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.sys.role.SysRoleAddDto;
import com.blog.framework.dto.sys.role.SysRoleEditDto;
import com.blog.framework.dto.sys.role.SysRoleQueryDto;
import com.blog.framework.service.sys.SysRoleService;
import com.blog.framework.vo.sys.role.SysRoleVo;
import com.blog.framework.web.annotation.Permissions;
import com.blog.framework.web.annotation.SysLog;
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
 * 角色管理
 *
 * @author liuzw
 **/
@RestController
@RequestMapping("/sys_role")
@Api(tags = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 获取所有角色列表数据
     *
     * @param queryReq 查询参数
     * @return ResultData<PageBean < SysRoleVo>>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_role:view")
    @ApiOperation(value = "获取所有角色列表数据", notes = "获取所有角色列表数据")
    @PostMapping(value = "/list")
    public ResultData<PageBean<SysRoleVo>> getList(@RequestBody SysRoleQueryDto queryReq) {
        return ResultData.createSelectResult(sysRoleService.getList(queryReq));
    }

    /**
     * 获取所有未被禁用的角色数据
     *
     * @return ResultData<List < KeyValueBean < String, Long>>>
     */
    @SysLog
    @ApiOperation(value = "获取所有未被禁用的角色数据", notes = "获取所有未被禁用的角色数据")
    @PostMapping(value = "/getRoleSelect")
    public ResultData<List<KeyValueBean<String, Long>>> getRoleSelect() {
        return ResultData.createSelectResult(sysRoleService.getRoleSelect());
    }

    /**
     * 根据角色获取对应菜单id
     *
     * @param idBean id
     * @return ResultData<List < Long>>
     */
    @SysLog
    @ApiOperation(value = "根据角色获取对应菜单id", notes = "根据角色获取对应菜单id")
    @PostMapping(value = "/getMenuIdByRoleId")
    public ResultData<List<Long>> getMenuIdByRoleId(@RequestBody Id<Long> idBean) {
        return ResultData.createSelectResult(sysRoleService.getMenuIdByRoleId(idBean.getId()));
    }

    /**
     * 根据id获取角色详情
     *
     * @param idBean 主键id
     * @return ResultData<SysRoleBean>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_role:view")
    @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
    @PostMapping(value = "/detail")
    public ResultData<SysRoleVo> detail(@Validated @RequestBody Id<Long> idBean) {
        return ResultData.createSelectResult(sysRoleService.detail(idBean.getId()));
    }


    /**
     * 添加角色
     *
     * @param addReq 添加角色数据
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_role:add")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping(value = "/add")
    public ResultData<String> add(@Validated @RequestBody SysRoleAddDto addReq) {
        return ResultData.createInsertResult(sysRoleService.add(addReq));
    }

    /**
     * 更新角色
     *
     * @param editReq 修改角色数据
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_role:edit")
    @ApiOperation(value = "更新角色", notes = "更新角色")
    @PostMapping(value = "/edit")
    public ResultData<String> edit(@Validated @RequestBody SysRoleEditDto editReq) {
        return ResultData.createUpdateResult(sysRoleService.edit(editReq));
    }


    /**
     * 删除角色
     *
     * @param idBean 主键id
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_role:delete")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @PostMapping(value = "/delete")
    public ResultData<String> delete(@Validated @RequestBody Id<Long> idBean) {
        return ResultData.createDeleteResult(sysRoleService.delete(idBean.getId()));
    }

}
