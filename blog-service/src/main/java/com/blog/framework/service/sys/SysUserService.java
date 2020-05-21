

package com.blog.framework.service.sys;

import com.blog.framework.common.PageBean;
import com.blog.framework.dto.sys.user.SysUserAddDto;
import com.blog.framework.dto.sys.user.SysUserEditDto;
import com.blog.framework.dto.sys.user.SysUserEditPwdDto;
import com.blog.framework.dto.sys.user.SysUserQueryDto;
import com.blog.framework.vo.sys.user.SysUserVo;

import java.util.List;


/**
 * interface SysUser
 *
 * @author liuzw
 */
public interface SysUserService {

    /**
     * 返回用户列表数据
     *
     * @param queryReq 查询参数
     * @return PageBean<SysMenuVo>
     */
    PageBean<SysUserVo> getList(SysUserQueryDto queryReq);

    /**
     * 根据id返回用户详细
     *
     * @param id id
     * @return SysUserModel
     */
    SysUserVo detail(Long id);

    /**
     * 根据用户获取对应角色id
     *
     * @param id id
     * @return List<Long>
     */
    List<Long> getRoleByUserId(Long id);

    /**
     * 新增用户
     *
     * @param addReq 新增用户数据
     * @return Boolean
     */
    Boolean add(SysUserAddDto addReq);

    /**
     * 修改用户
     *
     * @param editReq 修改用户数据
     * @return Boolean
     */
    Boolean edit(SysUserEditDto editReq);

    /**
     * 重置密码
     *
     * @param id 主键id
     * @return Boolean
     */
    Boolean resetPwd(Long id);

    /**
     * 修改密码
     *
     * @param pwdReq 密码
     * @return Boolean
     */
    Boolean editPwd(SysUserEditPwdDto pwdReq);

    /**
     * 根据ID删除用户
     *
     * @param id id
     * @return Boolean
     */
    Boolean delete(Long id);

}