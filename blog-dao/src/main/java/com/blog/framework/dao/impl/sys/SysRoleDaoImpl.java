package com.blog.framework.dao.impl.sys;

import com.blog.framework.dao.sys.SysRoleDao;
import com.blog.framework.mapper.sys.SysRoleMapper;
import com.blog.framework.model.sys.SysRoleModel;
import com.blog.framework.dto.sys.role.SysRoleQueryDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * basic-framework
 *
 * @author liuzw
 * @date 2019-08-07
 **/
@Repository
public class SysRoleDaoImpl implements SysRoleDao {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRoleModel> getList(SysRoleQueryDto queryReq) {
        Example example = new Example(SysRoleModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(queryReq.getRoleName())) {
            criteria.andLike("roleName", queryReq.getRoleName() + "%");
        }
        if (queryReq.getRoleStatus() != null) {
            criteria.andEqualTo("roleStatus", queryReq.getRoleStatus());
        }
        return sysRoleMapper.selectByExample(example);
    }

    @Override
    public SysRoleModel detail(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysRoleModel selectOne(SysRoleModel model) {
        return sysRoleMapper.selectOne(model);
    }


    @Override
    public Boolean add(SysRoleModel model) {
        return sysRoleMapper.insertSelective(model) > 0;
    }

    @Override
    public Boolean edit(SysRoleModel model) {
        return sysRoleMapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    public Boolean delete(Long id) {
        return sysRoleMapper.deleteByPrimaryKey(id) > 0;
    }
}
