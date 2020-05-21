package com.blog.framework.mapper.sys;


import com.blog.framework.model.sys.SysRoleMenuModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * interface SysRoleMenuMapper
 *
 * @author liuzw
 */
public interface SysRoleMenuMapper extends Mapper<SysRoleMenuModel>, MySqlMapper<SysRoleMenuModel> {

    /**
     * 批量插入角色菜单
     *
     * @param list 插入数据
     * @return int
     */
    @Insert({
            "<script>",
            " insert into t_sys_role_menu  ( role_id,menu_id) ",
            " values ",
            " <foreach collection='list' item='item' separator=','>",
            " (#{item.roleId}, #{item.menuId})",
            " </foreach>",
            "</script>"
    })
    int batchInsert(@Param("list") List<SysRoleMenuModel> list);

}