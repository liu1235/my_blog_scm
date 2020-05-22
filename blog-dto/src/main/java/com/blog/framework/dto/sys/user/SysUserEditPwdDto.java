package com.blog.framework.dto.sys.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码
 *
 * @author liuzw
 * @date 2019-08-22
 **/
@Data
public class SysUserEditPwdDto {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    private String password;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPasswordOnce;
}
