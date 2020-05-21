package com.blog.framework.mapper.sys;


import com.blog.framework.model.sys.SysUserRoleModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * interface SysUserRoleMapper
 *
 * @author liuzw
 */
public interface SysUserRoleMapper extends Mapper<SysUserRoleModel>, MySqlMapper<SysUserRoleModel> {

    /**
     * 批量插入角色菜单
     *
     * @param list 插入数据
     * @return int
     */
    @Insert({
            "<script>",
            " insert into t_sys_user_role  (user_id, role_id) ",
            " values ",
            " <foreach collection='list' item='item' separator=','>",
            " (#{item.userId}, #{item.roleId})",
            " </foreach>",
            "</script>"
    })
    int batchInsert(@Param("list") List<SysUserRoleModel> list);
}