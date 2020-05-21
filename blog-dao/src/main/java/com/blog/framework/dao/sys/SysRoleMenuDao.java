package com.blog.framework.dao.sys;


import com.blog.framework.model.sys.SysRoleMenuModel;

import java.util.List;

/**
 * interface SysRoleMenuMapper
 *
 * @author liuzw
 */
public interface SysRoleMenuDao {

    /**
     * 根据角色id获取对应菜单信息
     *
     * @param roleIds 角色id
     * @return List<SysRoleMenuModel>
     */
    List<SysRoleMenuModel> getRoleMenuByRoleId(List<Long> roleIds);

    /**
     * 批量插入角色对应菜单信息
     *
     * @param list 新增数据
     * @return Boolean
     */
    Boolean insertRoleMenu(List<SysRoleMenuModel> list);

    /**
     * 根据角色id删除对应信息
     *
     * @param roleId 角色id
     * @return Boolean
     */
    Boolean deleteRoleMenuByRoleId(Long roleId);
}