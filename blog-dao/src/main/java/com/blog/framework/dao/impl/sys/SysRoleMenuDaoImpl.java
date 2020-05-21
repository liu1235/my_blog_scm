package com.blog.framework.dao.impl.sys;

import com.blog.framework.dao.sys.SysRoleMenuDao;
import com.blog.framework.mapper.sys.SysRoleMenuMapper;
import com.blog.framework.model.sys.SysRoleMenuModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;

/**
 * basic-framework
 *
 * @author liuzw
 * @date 2019-08-09
 **/
@Slf4j
@Repository
public class SysRoleMenuDaoImpl implements SysRoleMenuDao {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenuModel> getRoleMenuByRoleId(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        Example example = new Example(SysRoleMenuModel.class);
        example.createCriteria().andIn("roleId", roleIds);
        return sysRoleMenuMapper.selectByExample(example);
    }

    @Override
    public Boolean insertRoleMenu(List<SysRoleMenuModel> list) {
        return sysRoleMenuMapper.batchInsert(list) > 0;
    }

    @Override
    public Boolean deleteRoleMenuByRoleId(Long roleId) {
        return sysRoleMenuMapper.delete(SysRoleMenuModel.builder().roleId(roleId).build()) > 0;
    }
}
