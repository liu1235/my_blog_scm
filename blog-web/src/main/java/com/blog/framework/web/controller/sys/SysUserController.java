package com.blog.framework.web.controller.sys;

import com.blog.framework.common.Id;
import com.blog.framework.common.PageBean;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.sys.user.SysUserAddDto;
import com.blog.framework.dto.sys.user.SysUserEditDto;
import com.blog.framework.dto.sys.user.SysUserEditPwdDto;
import com.blog.framework.dto.sys.user.SysUserQueryDto;
import com.blog.framework.service.sys.SysUserService;
import com.blog.framework.vo.sys.menu.SysMenuVo;
import com.blog.framework.vo.sys.user.SysUserVo;
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
 * 系统用户管理
 *
 * @author liuzw
 **/
@RestController
@RequestMapping("/sys_user")
@Api(tags = "系统用户管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取所有用户列表数据
     *
     * @param queryReq 查询参数
     * @return ResultData<PageBean < SysMenuVo>>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_user:view")
    @ApiOperation(value = "获取所有用户列表数据", notes = "获取所有用户列表数据")
    @PostMapping(value = "/list")
    public ResultData<PageBean<SysUserVo>> getList(@RequestBody SysUserQueryDto queryReq) {
        return ResultData.createSelectResult(sysUserService.getList(queryReq));
    }

    /**
     * 根据id获取用户详情
     *
     * @param idBean 主键id
     * @return ResultData<SysMenuVo>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_user:view")
    @ApiOperation(value = "根据id获取用户详情", notes = "根据id获取用户详情")
    @PostMapping(value = "/detail")
    public ResultData<SysUserVo> detail(@Validated @RequestBody Id<Long> idBean) {
        return ResultData.createSelectResult(sysUserService.detail(idBean.getId()));
    }

    /**
     * 根据用户获取对应角色id
     *
     * @param idBean id
     * @return ResultData<List < Long>>
     */
    @SysLog
    @ApiOperation(value = "根据用户获取对应角色id", notes = "根据用户获取对应角色id")
    @PostMapping(value = "/getRoleByUserId")
    public ResultData<List<Long>> getRoleByUserId(@RequestBody Id<Long> idBean) {
        return ResultData.createSelectResult(sysUserService.getRoleByUserId(idBean.getId()));
    }

    /**
     * 添加用户
     *
     * @param addReq 新增用户数据
     * @return ResultData<SysUserBean>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_user:add")
    @ApiOperation(value = "增加数据", notes = "增加数据")
    @PostMapping(value = "/add")
    public ResultData<String> add(@Validated @RequestBody SysUserAddDto addReq) {
        return ResultData.createInsertResult(sysUserService.add(addReq));
    }

    /**
     * 编辑用户
     *
     * @param bean SysUserBean
     * @return ResultData<SysUserBean>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_user:edit")
    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @PostMapping(value = "/edit")
    public ResultData<String> edit(@Validated @RequestBody SysUserEditDto bean) {
        return ResultData.createUpdateResult(sysUserService.edit(bean));
    }

    /**
     * 重置密码
     *
     * @param idBean 主键id
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_user:pwd")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping(value = "/resetPwd")
    public ResultData<String> resetPwd(@Validated @RequestBody Id<Long> idBean) {
        return ResultData.createDeleteResult(sysUserService.resetPwd(idBean.getId()));
    }

    /**
     * 修改密码
     *
     * @param pwdReq 密码
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_user:pwd")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping(value = "/editPwd")
    public ResultData<String> editPwd(@Validated @RequestBody SysUserEditPwdDto pwdReq) {
        return ResultData.createDeleteResult(sysUserService.editPwd(pwdReq));
    }

    /**
     * 删除用户
     *
     * @param idBean 主键id
     * @return ResultData<String>
     */
    @SysLog
    @Permissions(permissions = "blog:sys_user:delete")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping(value = "/delete")
    public ResultData<String> delete(@Validated @RequestBody Id<Long> idBean) {
        return ResultData.createDeleteResult(sysUserService.delete(idBean.getId()));
    }

}
