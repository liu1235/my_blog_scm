package com.blog.framework.dao.sys;


import com.blog.framework.model.sys.SysUserRoleModel;

import java.util.List;

/**
 * interface SysUserRoleMapper
 *
 * @author liuzw
 */
public interface SysUserRoleDao {

    /**
     * 根据用户id获取对应角色信息
     *
     * @param userIds 用户id
     * @return List<SysUserRoleModel>
     */
    List<SysUserRoleModel> getUserRoleByUserId(List<Long> userIds);

    /**
     * 批量插入用户对应角色信息
     *
     * @param list 新增数据
     * @return Boolean
     */
    Boolean insertUserRole(List<SysUserRoleModel> list);

    /**
     * 根据用户id删除对应信息
     *
     * @param userId 用户id
     * @return Boolean
     */
    Boolean deleteUserRoleByUserId(Long userId);
}