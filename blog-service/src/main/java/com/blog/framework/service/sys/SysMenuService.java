package com.blog.framework.service.sys;


import com.blog.framework.common.KeyValueBean;
import com.blog.framework.dto.sys.menu.SysMenuAddDto;
import com.blog.framework.dto.sys.menu.SysMenuEditDto;
import com.blog.framework.dto.sys.menu.SysMenuQueryDto;
import com.blog.framework.vo.sys.menu.SysMenuVo;

import java.util.List;


/**
 * interface SysMenu
 *
 * @author liuzw
 */
public interface SysMenuService {


    /**
     * 获取所有菜单列表数据
     *
     * @param queryReq 查询参数
     * @return List<SysMenuVo>
     */
    List<SysMenuVo> getList(SysMenuQueryDto queryReq);

    /**
     * 根据id获取菜单详情
     *
     * @param id 主键id
     * @return SysMenuVo
     */
    SysMenuVo detail(Long id);

    /**
     * 根据菜单类型获取菜单数据
     *
     * @param type 菜单类型
     * @return List<SysMenuVo>
     */
    List<KeyValueBean<String, Long>> getMenuByType(Integer type);

    /**
     * 增加菜单
     *
     * @param addReq 新增菜单数据
     * @return Boolean
     */
    Boolean add(SysMenuAddDto addReq);

    /**
     * 修改菜单
     *
     * @param editReq 修改菜单数据
     * @return Boolean
     */
    Boolean edit(SysMenuEditDto editReq);

    /**
     * 删除菜单
     *
     * @param id 主键
     * @return Boolean
     */
    Boolean delete(Long id);

    /**
     * 根据用户id获取菜单（未拼成树形数据）
     *
     * @param userId 用户id
     * @return List<SysMenuVo>
     */
    List<SysMenuVo> getMenuByUserId(Long userId);

    /**
     * 根据角色id获取菜单
     *
     * @param roleId 角色id
     * @return List<SysMenuVo>
     */
    List<SysMenuVo> getMenuByRoleId(Long roleId);


    /**
     * 超管获取所有菜单数据
     *
     * @return List<SysMenuVo>
     */
    List<SysMenuVo> selectAllByAdmin();

    /**
     * 封装树形菜单
     *
     * @param rootMenu  菜单数据
     * @return List<SysMenuVo>
     */
    List<SysMenuVo> formatMenuList(List<SysMenuVo> rootMenu);

}