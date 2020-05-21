package com.blog.framework.dto.sys.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Data
public class AdminUserLoginDto implements Serializable {

    private static final long serialVersionUID = -8659004786382288843L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
