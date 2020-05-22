package com.blog.framework.service.impl.sys;

import com.blog.framework.common.KeyValueBean;
import com.blog.framework.common.enums.BaseStatusEnum;
import com.blog.framework.common.enums.MenuTypeEnum;
import com.blog.framework.common.exception.ServiceException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.dao.sys.SysMenuDao;
import com.blog.framework.dao.sys.SysRoleMenuDao;
import com.blog.framework.dao.sys.SysUserRoleDao;
import com.blog.framework.dto.sys.menu.SysMenuAddDto;
import com.blog.framework.dto.sys.menu.SysMenuEditDto;
import com.blog.framework.dto.sys.menu.SysMenuQueryDto;
import com.blog.framework.model.sys.SysMenuModel;
import com.blog.framework.model.sys.SysRoleMenuModel;
import com.blog.framework.model.sys.SysUserRoleModel;
import com.blog.framework.service.sys.SysMenuService;
import com.blog.framework.vo.sys.menu.SysMenuVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SysMenuServiceImpl
 *
 * @author liuzw
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<SysMenuVo> getList(SysMenuQueryDto queryReq) {
        List<SysMenuModel> list = sysMenuDao.getList(queryReq);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<SysMenuVo> menuList = CopyDataUtil.copyList(list, SysMenuVo.class);
        return formatMenuList(menuList);
    }

    @Override
    public SysMenuVo detail(Long id) {
        SysMenuModel detail = sysMenuDao.detail(id);
        if (detail != null) {
            SysMenuVo menuResp = CopyDataUtil.copyObject(detail, SysMenuVo.class);
            menuResp.setMenuStatusName(getStatus(menuResp.getMenuStatus()));
            menuResp.setMenuTypeName(getType(menuResp.getMenuType()));
            return menuResp;
        }
        return null;
    }

    @Override
    public List<KeyValueBean<String, Long>> getMenuByType(Integer type) {
        SysMenuQueryDto queryReq = new SysMenuQueryDto();
        queryReq.setMenuType(type);
        List<SysMenuModel> list = sysMenuDao.getList(queryReq);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(v -> {
            KeyValueBean<String, Long> keyValue = new KeyValueBean<>();
            keyValue.setKey(v.getMenuName());
            keyValue.setValue(v.getId());
            return keyValue;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SysMenuAddDto addReq) {
        //参数校验
        checkParam(addReq);
        //校验菜单是否存在
        checkMenuNameExist(addReq, null);
        SysMenuModel insertObj = CopyDataUtil.copyObject(addReq, SysMenuModel.class);
        insertObj.setId(null);
        return sysMenuDao.add(insertObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(SysMenuEditDto editReq) {
        //参数校验
        checkParam(editReq);
        //校验角色名是否存在
        checkMenuNameExist(editReq, editReq.getId());
        SysMenuModel updateObj = CopyDataUtil.copyObject(editReq, SysMenuModel.class);
        return sysMenuDao.edit(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Long id) {
        //判断菜单是否有下级
        Boolean hasChild = sysMenuDao.checkMenuHasChild(id);
        if (hasChild) {
            throw new ServiceException("菜单包含下级菜单不能删除");
        }
        return sysMenuDao.delete(id);
    }

    @Override
    public List<SysMenuVo> getMenuByUserId(Long userId) {
        //获取用户对应角色
        List<SysUserRoleModel> userRoleList = sysUserRoleDao.getUserRoleByUserId(Lists.newArrayList(userId));
        if (CollectionUtils.isEmpty(userRoleList)) {
            return Collections.emptyList();
        }
        //获取角色id
        List<Long> roleIds = userRoleList.stream()
                .map(SysUserRoleModel::getRoleId)
                .distinct()
                .collect(Collectors.toList());

        //获取角色对应菜单
        List<SysRoleMenuModel> roleMenuList = sysRoleMenuDao.getRoleMenuByRoleId(roleIds);
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return Collections.emptyList();
        }
        //获取菜单id
        List<Long> menuIds = roleMenuList.stream()
                .map(SysRoleMenuModel::getMenuId)
                .distinct()
                .collect(Collectors.toList());
        //获取到菜单信息
        List<SysMenuModel> list = sysMenuDao.getMenuListByIds(menuIds);
        return CopyDataUtil.copyList(list, SysMenuVo.class);
    }

    @Override
    public List<SysMenuVo> getMenuByRoleId(Long roleId) {
        //获取角色对应菜单
        List<SysRoleMenuModel> roleMenuList = sysRoleMenuDao.getRoleMenuByRoleId(Lists.newArrayList(roleId));
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return Collections.emptyList();
        }
        //获取菜单id
        List<Long> menuIds = roleMenuList.stream()
                .map(SysRoleMenuModel::getMenuId)
                .distinct()
                .collect(Collectors.toList());
        //获取到菜单信息
        List<SysMenuModel> list = sysMenuDao.getMenuListByIds(menuIds);
        List<SysMenuVo> respList = CopyDataUtil.copyList(list, SysMenuVo.class);
        return formatMenuList(respList);
    }

    @Override
    public List<SysMenuVo> selectAllByAdmin() {
        List<SysMenuModel> list = sysMenuDao.getList(new SysMenuQueryDto());
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return CopyDataUtil.copyList(list, SysMenuVo.class);
    }

    @Override
    public List<SysMenuVo> formatMenuList(List<SysMenuVo> rootMenu) {
        // 先找到所有的一级菜单
        List<SysMenuVo> menuList = rootMenu.stream()
                .filter(menu -> menu.getParentId() == 0)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(menuList)) {
            menuList = rootMenu.stream().filter(menu -> MenuTypeEnum.MENU.getCode().equals(menu.getMenuType()))
                    .collect(Collectors.toList());
        }
        //排序
        menuList.sort(Comparator.comparing(SysMenuVo::getMenuOrder));
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (SysMenuVo menu : menuList) {
            menu.setChildren(getChild(menu.getId(), rootMenu));
        }
        return menuList;
    }


    /**
     * 参数校验
     *
     * @param req 需要校验的数据
     */
    private void checkParam(SysMenuAddDto req) {
        // 当为按钮时
        if (MenuTypeEnum.BUTTON.getCode().equals(req.getMenuType())) {
            if (StringUtils.isBlank(req.getMenuPerms()) || StringUtils.isBlank(req.getMenuCode())) {
                throw new ServiceException("菜单权限或code不能为空");
            }
        }
    }


    /**
     * 判断菜单名称/code是否存在
     *
     * @param req 校验数据
     * @param id  主键id（修改时对自身不做校验）
     */
    private void checkMenuNameExist(SysMenuAddDto req, Long id) {
        SysMenuModel model = sysMenuDao.selectOne(SysMenuModel.builder()
                .menuName(req.getMenuName())
                .parentId(req.getParentId())
                .build());

        if (model != null && !model.getId().equals(id)) {
            throw new ServiceException("菜单名称已存在");
        }
    }

    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return List<SysMenuVo>
     */
    private List<SysMenuVo> getChild(Long id, List<SysMenuVo> rootMenu) {
        // 子菜单
        List<SysMenuVo> childList = new ArrayList<>();
        for (SysMenuVo menu : rootMenu) {
            menu.setMenuTypeName(getType(menu.getMenuType()));
            menu.setMenuStatusName(getStatus(menu.getMenuStatus()));
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
        }

        // 递归退出条件
        if (CollectionUtils.isEmpty(childList)) {
            return Collections.emptyList();
        }
        //排序
        childList.sort(Comparator.comparing(SysMenuVo::getMenuOrder));

        for (SysMenuVo menu : childList) {
            menu.setMenuTypeName(getType(menu.getMenuType()));
            menu.setMenuStatusName(getStatus(menu.getMenuStatus()));
            menu.setChildren(getChild(menu.getId(), rootMenu));
        }

        return childList;
    }


    private String getType(Integer type) {
        return MenuTypeEnum.getMenuTypeEnum(type).getMsg();
    }

    private String getStatus(Integer status) {
        return BaseStatusEnum.getStatusEnum(status).getMsg();
    }
}