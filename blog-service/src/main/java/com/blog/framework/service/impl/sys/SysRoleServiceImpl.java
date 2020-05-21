package com.blog.framework.service.impl.sys;

import com.blog.framework.common.KeyValueBean;
import com.blog.framework.common.PageBean;
import com.blog.framework.common.enums.BaseStatusEnum;
import com.blog.framework.common.exception.ServiceException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.dao.sys.SysRoleDao;
import com.blog.framework.dao.sys.SysRoleMenuDao;
import com.blog.framework.dto.sys.role.SysRoleAddDto;
import com.blog.framework.dto.sys.role.SysRoleEditDto;
import com.blog.framework.dto.sys.role.SysRoleQueryDto;
import com.blog.framework.model.sys.SysRoleMenuModel;
import com.blog.framework.model.sys.SysRoleModel;
import com.blog.framework.service.sys.SysRoleService;
import com.blog.framework.vo.sys.role.SysRoleVo;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SysRoleServiceImpl
 *
 * @author liuzw
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;


    @Override
    public PageBean<SysRoleVo> getList(SysRoleQueryDto queryReq) {
        PageHelper.startPage(queryReq.getPageNum(), queryReq.getPageSize());
        List<SysRoleModel> list = sysRoleDao.getList(queryReq);
        return PageBean.createPageBean(list, SysRoleVo.class);
    }

    @Override
    public List<KeyValueBean<String, Long>> getRoleSelect() {
        SysRoleQueryDto queryReq = new SysRoleQueryDto();
        queryReq.setRoleStatus(BaseStatusEnum.ENABLE.getCode());
        List<SysRoleModel> list = sysRoleDao.getList(queryReq);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(v -> {
            KeyValueBean<String, Long> keyValue = new KeyValueBean<>();
            keyValue.setKey(v.getRoleName());
            keyValue.setValue(v.getId());
            return keyValue;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Long> getMenuIdByRoleId(Long roleId) {
        List<SysRoleMenuModel> list = sysRoleMenuDao.getRoleMenuByRoleId(Lists.newArrayList(roleId));
        return list.stream().map(SysRoleMenuModel::getMenuId).distinct().collect(Collectors.toList());
    }

    @Override
    public SysRoleVo detail(Long id) {
        SysRoleModel detail = sysRoleDao.detail(id);
        if (detail != null) {
            return CopyDataUtil.copyObject(detail, SysRoleVo.class);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SysRoleAddDto addReq) {
        //校验角色名是否存在
        checkRoleNameExist(addReq.getRoleName(), null);
        SysRoleModel insertObj = CopyDataUtil.copyObject(addReq, SysRoleModel.class);
        insertObj.setId(null);
        Boolean flag = sysRoleDao.add(insertObj);
        if (flag && CollectionUtils.isNotEmpty(addReq.getMenuIds())) {
            List<SysRoleMenuModel> userRoleModels = addReq.getMenuIds().stream()
                    .map(v -> SysRoleMenuModel.builder()
                            .menuId(v)
                            .roleId(insertObj.getId())
                            .build())
                    .collect(Collectors.toList());
            sysRoleMenuDao.insertRoleMenu(userRoleModels);
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(SysRoleEditDto editReq) {
        //校验角色名是否存在
        checkRoleNameExist(editReq.getRoleName(), editReq.getId());
        SysRoleModel updateObj = CopyDataUtil.copyObject(editReq, SysRoleModel.class);
        Boolean flag = sysRoleDao.edit(updateObj);
        if (flag) {
            sysRoleMenuDao.deleteRoleMenuByRoleId(editReq.getId());
            if (CollectionUtils.isNotEmpty(editReq.getMenuIds())) {
                List<SysRoleMenuModel> userRoleModels = editReq.getMenuIds().stream()
                        .map(v -> SysRoleMenuModel.builder()
                                .menuId(v)
                                .roleId(editReq.getId())
                                .build())
                        .collect(Collectors.toList());
                sysRoleMenuDao.insertRoleMenu(userRoleModels);
            }

        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Long id) {
        return sysRoleDao.delete(id);
    }

    /**
     * 判断角色名称是否存在
     *
     * @param roleName 角色名称
     * @param id       主键id（修改时对自身不做校验）
     */
    private void checkRoleNameExist(String roleName, Long id) {
        SysRoleModel model = sysRoleDao.selectOne(SysRoleModel.builder().roleName(roleName).build());
        if (model != null && !model.getId().equals(id)) {
            throw new ServiceException("角色名称已存在");
        }
    }

}