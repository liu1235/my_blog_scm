package com.blog.framework.service.impl.sys;

import com.blog.framework.common.PageBean;
import com.blog.framework.common.constants.BaseConstants;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.common.exception.ServiceException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.common.utils.EncryptMd5Util;
import com.blog.framework.dao.sys.SysUserDao;
import com.blog.framework.dao.sys.SysUserRoleDao;
import com.blog.framework.dto.sys.user.SysUserAddDto;
import com.blog.framework.dto.sys.user.SysUserEditDto;
import com.blog.framework.dto.sys.user.SysUserEditPwdDto;
import com.blog.framework.dto.sys.user.SysUserQueryDto;
import com.blog.framework.model.sys.SysUserModel;
import com.blog.framework.model.sys.SysUserRoleModel;
import com.blog.framework.service.TokenService;
import com.blog.framework.service.sys.SysUserService;
import com.blog.framework.vo.sys.user.SysUserLoginVo;
import com.blog.framework.vo.sys.user.SysUserVo;
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
 * SysUserServiceImpl
 *
 * @author liuzw
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private TokenService tokenService;


    @Override
    public PageBean<SysUserVo> getList(SysUserQueryDto queryReq) {
        PageHelper.startPage(queryReq.getPageNum(), queryReq.getPageSize());
        List<SysUserModel> list = sysUserDao.getList(queryReq);
        return PageBean.createPageBean(list, SysUserVo.class);
    }

    @Override
    public SysUserVo detail(Long id) {
        SysUserModel detail = sysUserDao.detail(id);
        if (detail != null) {
            return CopyDataUtil.copyObject(detail, SysUserVo.class);
        }
        return null;
    }

    @Override
    public List<Long> getRoleByUserId(Long id) {
        List<SysUserRoleModel> list = sysUserRoleDao.getUserRoleByUserId(Lists.newArrayList(id));
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(SysUserRoleModel::getRoleId).distinct().collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SysUserAddDto addReq) {
        //判断账户是否存在
        checkAccountExist(addReq.getAccount());
        SysUserModel insertObj = CopyDataUtil.copyObject(addReq, SysUserModel.class);
        insertObj.setPassword(EncryptMd5Util.getMD5(BaseConstants.PASSWORD));
        insertObj.setId(null);
        Boolean flag = sysUserDao.add(insertObj);
        if (flag && CollectionUtils.isNotEmpty(addReq.getRoleIds())) {
            List<SysUserRoleModel> userRoleModels = addReq.getRoleIds().stream()
                    .map(v -> SysUserRoleModel.builder()
                            .roleId(v)
                            .userId(insertObj.getId())
                            .build())
                    .collect(Collectors.toList());
            sysUserRoleDao.insertUserRole(userRoleModels);
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(SysUserEditDto editReq) {
        SysUserModel updateObj = CopyDataUtil.copyObject(editReq, SysUserModel.class);
        Boolean flag = sysUserDao.edit(updateObj);
        if (flag && CollectionUtils.isNotEmpty(editReq.getRoleIds())) {
            sysUserRoleDao.deleteUserRoleByUserId(editReq.getId());
            List<SysUserRoleModel> userRoleModels = editReq.getRoleIds().stream()
                    .map(v -> SysUserRoleModel.builder()
                            .roleId(v)
                            .userId(editReq.getId())
                            .build())
                    .collect(Collectors.toList());
            sysUserRoleDao.insertUserRole(userRoleModels);
        }
        return flag;
    }

    @Override
    public Boolean resetPwd(Long id) {
        SysUserModel model = SysUserModel.builder()
                .id(id)
                .password(EncryptMd5Util.getMD5(BaseConstants.PASSWORD))
                .build();
        return sysUserDao.edit(model);
    }

    @Override
    public Boolean editPwd(SysUserEditPwdDto pwdReq) {
        SysUserLoginVo userInfo = tokenService.getSysUserInfo();
        //判断账号密码是否正确
        SysUserModel model = sysUserDao.selectOne(SysUserModel.builder()
                .account(userInfo.getAccount())
                .password(pwdReq.getPassword().toLowerCase())
                .build());

        if (model == null) {
            throw new LoginException("旧密码错误");
        }
        String newPassword = pwdReq.getNewPassword();
        String newPasswordOnce = pwdReq.getNewPasswordOnce();
        if (!newPasswordOnce.equals(EncryptMd5Util.getMD5(newPassword).toLowerCase())) {
            throw new ServiceException("两次密码输入不一样");
        }

        SysUserModel build = SysUserModel.builder()
                .id(userInfo.getId())
                .password(newPasswordOnce)
                .build();
        return sysUserDao.edit(build);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Long id) {
        return sysUserDao.delete(id);
    }

    /**
     * 判断账户是否存在
     *
     * @param account 角色名称
     */
    private void checkAccountExist(String account) {
        SysUserModel model = sysUserDao.selectOne(SysUserModel.builder().account(account).build());
        if (model != null) {
            throw new ServiceException("账户已存在");
        }
    }
}