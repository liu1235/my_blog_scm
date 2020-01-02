package com.blog.framework.dto.user;

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
public class UserLoginDto implements Serializable {

    private static final long serialVersionUID = -8659004786382288843L;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
