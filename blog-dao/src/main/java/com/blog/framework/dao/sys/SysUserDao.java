

package com.blog.framework.dao.sys;

import com.blog.framework.dto.sys.user.SysUserQueryDto;
import com.blog.framework.model.sys.SysUserModel;

import java.util.List;


/**
 * interface SysUser
 *
 * @author liuzw
 */
public interface SysUserDao {

    /**
     * 返回用户列表数据
     *
     * @param queryReq 查询参数
     * @return PageBean<SysMenuVo>
     */
    List<SysUserModel> getList(SysUserQueryDto queryReq);

    /**
     * 根据id返回用户详细
     *
     * @param id id
     * @return SysUserModel
     */
    SysUserModel detail(Long id);

    /**
     * 根据相应条件获取用户信息
     *
     * @param model 查询数据
     * @return SysUserModel
     */
    SysUserModel selectOne(SysUserModel model);

    /**
     * 新增用户
     *
     * @param model 新增用户数据
     * @return Boolean
     */
    Boolean add(SysUserModel model);

    /**
     * 修改用户
     *
     * @param model 修改用户数据
     * @return Boolean
     */
    Boolean edit(SysUserModel model);

    /**
     * 根据ID删除用户
     *
     * @param id id
     * @return Boolean
     */
    Boolean delete(Long id);

}