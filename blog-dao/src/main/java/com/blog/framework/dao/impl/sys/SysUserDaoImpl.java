package com.blog.framework.dao.impl.sys;

import com.blog.framework.common.enums.AdminEnum;
import com.blog.framework.dao.sys.SysUserDao;
import com.blog.framework.dto.sys.user.SysUserQueryDto;
import com.blog.framework.mapper.sys.SysUserMapper;
import com.blog.framework.model.sys.SysUserModel;
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
public class SysUserDaoImpl implements SysUserDao {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUserModel> getList(SysUserQueryDto queryReq) {
        Example example = new Example(SysUserModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(queryReq.getAccount())) {
            criteria.andEqualTo("account", queryReq.getAccount());
        }
        if (queryReq.getUserStatus() != null) {
            criteria.andEqualTo("userStatus", queryReq.getUserStatus());
        }
        criteria.andEqualTo("adminFlag", AdminEnum.NO.getCode());
        return sysUserMapper.selectByExample(example);
    }

    @Override
    public SysUserModel detail(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUserModel selectOne(SysUserModel model) {
        return sysUserMapper.selectOne(model);
    }

    @Override
    public Boolean add(SysUserModel model) {
        return sysUserMapper.insertSelective(model) > 0;
    }

    @Override
    public Boolean edit(SysUserModel model) {
        return sysUserMapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    public Boolean delete(Long id) {
        return sysUserMapper.deleteByPrimaryKey(id) > 0;
    }
}
