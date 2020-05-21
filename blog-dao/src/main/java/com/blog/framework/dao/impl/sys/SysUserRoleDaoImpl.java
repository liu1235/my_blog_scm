package com.blog.framework.dao.impl.sys;

import com.blog.framework.dao.sys.SysUserRoleDao;
import com.blog.framework.mapper.sys.SysUserRoleMapper;
import com.blog.framework.model.sys.SysUserRoleModel;
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
public class SysUserRoleDaoImpl implements SysUserRoleDao {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRoleModel> getUserRoleByUserId(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        Example example = new Example(SysUserRoleModel.class);
        example.createCriteria().andIn("userId", userIds);
        return sysUserRoleMapper.selectByExample(example);
    }

    @Override
    public Boolean insertUserRole(List<SysUserRoleModel> list) {
        return sysUserRoleMapper.batchInsert(list) > 0;
    }

    @Override
    public Boolean deleteUserRoleByUserId(Long userId) {
        return sysUserRoleMapper.delete(SysUserRoleModel.builder().userId(userId).build()) > 0;
    }
}
