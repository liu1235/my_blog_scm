package com.blog.framework.dao.impl.sys;

import com.blog.framework.common.enums.BaseStatusEnum;
import com.blog.framework.dao.sys.SysMenuDao;
import com.blog.framework.dto.sys.menu.SysMenuQueryDto;
import com.blog.framework.mapper.sys.SysMenuMapper;
import com.blog.framework.model.sys.SysMenuModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
@Repository
public class SysMenuDaoImpl implements SysMenuDao {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuModel> getList(SysMenuQueryDto queryReq) {
        Example example = new Example(SysMenuModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(queryReq.getMenuName())) {
            criteria.andLike("menuName", queryReq.getMenuName() + "%");
        }
        if (queryReq.getMenuStatus() != null) {
            criteria.andEqualTo("menuStatus", queryReq.getMenuStatus());
        }
        if (queryReq.getMenuType() != null) {
            criteria.andEqualTo("menuType", queryReq.getMenuType());
        }
        return sysMenuMapper.selectByExample(example);
    }

    @Override
    public SysMenuModel detail(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysMenuModel selectOne(SysMenuModel model) {
        return sysMenuMapper.selectOne(model);
    }

    @Override
    public Boolean add(SysMenuModel model) {
        return sysMenuMapper.insertSelective(model) > 0;
    }

    @Override
    public Boolean edit(SysMenuModel model) {
        return sysMenuMapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    public Boolean delete(Long id) {
        return sysMenuMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<SysMenuModel> getMenuListByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        Example example = new Example(SysMenuModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        criteria.andEqualTo("menuStatus", BaseStatusEnum.ENABLE.getCode());
        return sysMenuMapper.selectByExample(example);
    }

    @Override
    public Boolean checkMenuHasChild(Long id) {
        List<SysMenuModel> list = sysMenuMapper.select(SysMenuModel.builder().parentId(id)
                .menuStatus(BaseStatusEnum.ENABLE.getCode())
                .build());
        return CollectionUtils.isNotEmpty(list);
    }
}
