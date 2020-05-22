

package com.blog.framework.service.sys;


import com.blog.framework.common.KeyValueBean;
import com.blog.framework.common.PageBean;
import com.blog.framework.dto.sys.role.SysRoleAddDto;
import com.blog.framework.dto.sys.role.SysRoleEditDto;
import com.blog.framework.dto.sys.role.SysRoleQueryDto;
import com.blog.framework.vo.sys.role.SysRoleVo;

import java.util.List;


/**
 * interface SysRole
 *
 * @author liuzw
 */
public interface SysRoleService {


    /**
     * 获取所有角色列表数据
     *
     * @param queryReq 查询参数
     * @return PageBean<SysRole>
     */
    PageBean<SysRoleVo> getList(SysRoleQueryDto queryReq);


    /**
     * 获取所有未被禁用的角色数据
     *
     * @return List<KeyValueBean < String, Long>>
     */
    List<KeyValueBean<String, Long>> getRoleSelect();

    /**
     * 根据角色获取对应菜单id
     *
     * @param roleId 角色id
     * @return List<Long>
     */
    List<Long> getMenuIdByRoleId(Long roleId);

    /**
     * 根据id获取角色详情
     *
     * @param id 主键id
     * @return SysRoleVo
     */
    SysRoleVo detail(Long id);

    /**
     * 增加角色
     *
     * @param addReq 新增角色数据
     * @return Boolean
     */
    Boolean add(SysRoleAddDto addReq);

    /**
     * 修改角色
     *
     * @param editReq 修改角色数据
     * @return Boolean
     */
    Boolean edit(SysRoleEditDto editReq);

    /**
     * 删除角色
     *
     * @param id 主键
     * @return Boolean
     */
    Boolean delete(Long id);

}