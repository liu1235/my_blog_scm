package com.blog.framework.dao.sys;

import com.blog.framework.model.sys.SysRoleModel;
import com.blog.framework.dto.sys.role.SysRoleQueryDto;

import java.util.List;

/**
 * interface SysRole
 *
 * @author liuzw
 */
public interface SysRoleDao {


    /**
     * 获取所有角色列表数据
     *
     * @param queryReq 查询参数
     * @return list<SysRole>
     */
    List<SysRoleModel> getList(SysRoleQueryDto queryReq);

    /**
     * 根据id获取角色详情
     *
     * @param id 主键id
     * @return SysRoleVo
     */
    SysRoleModel detail(Long id);

    /**
     * 根据相应条件获取角色信息
     *
     * @param model 查询数据
     * @return SysRoleModel
     */
    SysRoleModel selectOne(SysRoleModel model);

    /**
     * 增加角色
     *
     * @param model 新增角色数据
     * @return Boolean
     */
    Boolean add(SysRoleModel model);

    /**
     * 修改角色
     *
     * @param model 修改角色数据
     * @return Boolean
     */
    Boolean edit(SysRoleModel model);

    /**
     * 删除角色
     *
     * @param id 主键
     * @return Boolean
     */
    Boolean delete(Long id);

}