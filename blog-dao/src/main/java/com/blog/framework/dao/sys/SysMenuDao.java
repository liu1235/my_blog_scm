package com.blog.framework.dao.sys;

import com.blog.framework.model.sys.SysMenuModel;
import com.blog.framework.dto.sys.menu.SysMenuQueryDto;

import java.util.List;

/**
 * basic-framework
 *
 * @author liuzw
 * @date 2019-08-09
 **/
public interface SysMenuDao {

    /**
     * 获取所有菜单列表数据
     *
     * @param queryReq 查询参数
     * @return list<SysRole>
     */
    List<SysMenuModel> getList(SysMenuQueryDto queryReq);

    /**
     * 根据id获取菜单详情
     *
     * @param id 主键id
     * @return SysRoleVo
     */
    SysMenuModel detail(Long id);

    /**
     * 根据相应条件获取菜单信息
     *
     * @param model 查询数据
     * @return SysMenuModel
     */
    SysMenuModel selectOne(SysMenuModel model);

    /**
     * 增加菜单
     *
     * @param model 新增菜单数据
     * @return Boolean
     */
    Boolean add(SysMenuModel model);

    /**
     * 修改菜单
     *
     * @param model 修改菜单数据
     * @return Boolean
     */
    Boolean edit(SysMenuModel model);

    /**
     * 删除菜单
     *
     * @param id 主键
     * @return Boolean
     */
    Boolean delete(Long id);

    /**
     * 根据菜单id集合获取菜单信息
     *
     * @param ids 菜单id
     * @return List<SysMenuModel>
     */
    List<SysMenuModel> getMenuListByIds(List<Long> ids);

    /**
     * 校验菜单是否包含下级
     *
     * @param id 主键
     * @return Boolean
     */
    Boolean checkMenuHasChild(Long id);

}
