package com.blog.framework.service.impl;

import com.blog.framework.common.enums.AdminEnum;
import com.blog.framework.common.enums.MenuTypeEnum;
import com.blog.framework.common.enums.ResultDataEnum;
import com.blog.framework.common.enums.UserStatusEnum;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.common.exception.ServiceException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.common.utils.EncryptMd5Util;
import com.blog.framework.dao.UserDao;
import com.blog.framework.dao.sys.SysUserDao;
import com.blog.framework.dto.sys.user.AdminUserLoginDto;
import com.blog.framework.dto.user.UserLoginDto;
import com.blog.framework.model.UserModel;
import com.blog.framework.model.sys.SysUserModel;
import com.blog.framework.service.LoginService;
import com.blog.framework.service.TokenService;
import com.blog.framework.service.sys.SysMenuService;
import com.blog.framework.vo.sys.menu.SysMenuVo;
import com.blog.framework.vo.sys.user.SysUserLoginVo;
import com.blog.framework.vo.user.UserLoginVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserLoginVo login(UserLoginDto dto) {
        //根据邮箱获取用户信息
        UserModel userModel = userDao.selectByEmail(dto.getEmail());
        if (userModel == null) {
            throw new ServiceException(ResultDataEnum.USERNAME_OR_PASSWORD_IS_WRONG.getCode(),
                    ResultDataEnum.USERNAME_OR_PASSWORD_IS_WRONG.getMsg());
        }
        //密码错误
        String md5 = EncryptMd5Util.getMD5(dto.getPassword());
        if (!userModel.getPassword().equals(md5)) {
            throw new ServiceException(ResultDataEnum.USERNAME_OR_PASSWORD_IS_WRONG.getCode(),
                    ResultDataEnum.USERNAME_OR_PASSWORD_IS_WRONG.getMsg());
        }

        //未激活
        if (UserStatusEnum.INACTIVATED.getCode().equals(userModel.getStatus())) {
            throw new ServiceException(ResultDataEnum.EMAIL_NOT_ACTIVATED.getCode(),
                    ResultDataEnum.EMAIL_NOT_ACTIVATED.getMsg());
        }
        String token = UUID.randomUUID().toString();
        token = token.replace("-", "").toLowerCase();
        // 存放用户信息到redis
        UserLoginVo vo = UserLoginVo.builder()
                .email(userModel.getEmail())
                .userName(userModel.getUserName())
                .token(token)
                .userId(userModel.getId())
                .build();
        tokenService.saveUserInfo(token, vo);
        vo.setUserId(null);
        return vo;
    }

    @Override
    public Boolean logout() {
        // 删除redis 用户数据
        return tokenService.deleteUserInfo();
    }

    @Override
    public SysUserLoginVo loginAdmin(AdminUserLoginDto dto) {
        //判断账号密码是否正确
        SysUserModel model = sysUserDao.selectOne(SysUserModel.builder()
                .account(dto.getAccount())
                .password(dto.getPassword().toLowerCase())
                .build());
        if (model == null) {
            throw new LoginException("用户名密码错误");
        }

        //生成token
        String token = UUID.randomUUID().toString().replace("-", "");

        //存入redis
        SysUserLoginVo userInfo = SysUserLoginVo.builder()
                .account(model.getAccount())
                .userName(model.getAccount())
                .adminFlag(model.getAdminFlag())
                .token(token)
                .build();
        //获取当前用户对应的菜单
        List<SysMenuVo> menuList;
        if (AdminEnum.YES.getCode().equals(model.getAdminFlag())) {
            menuList = sysMenuService.selectAllByAdmin();
        } else {
            menuList = sysMenuService.getMenuByUserId(model.getId());
        }

        if (CollectionUtils.isNotEmpty(menuList)) {
            //获取权限数据
            List<String> permsList = menuList.stream()
                    .filter(v -> MenuTypeEnum.BUTTON.getCode().equals(v.getMenuType()))
                    .map(SysMenuVo::getMenuPerms)
                    .collect(Collectors.toList());
            userInfo.setPermsList(permsList);

            //获取菜单数据
            List<SysMenuVo> list = menuList.stream()
                    .filter(v -> !MenuTypeEnum.BUTTON.getCode().equals(v.getMenuType()))
                    .collect(Collectors.toList());
            userInfo.setMenuList(sysMenuService.formatMenuList(list));
            //获取菜单url
            List<String> urls = menuList.stream().map(SysMenuVo::getMenuUrl)
                    .filter(StringUtils::isNotBlank)
                    .distinct().collect(Collectors.toList());
            userInfo.setUrls(urls);
        }
        SysUserLoginVo info = CopyDataUtil.copyObject(userInfo, SysUserLoginVo.class);
        info.setMenuList(null);
        info.setUrls(null);
        tokenService.saveUserInfo(token, info);

        return userInfo;
    }


}
