package com.liuzw.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户信息
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo implements Serializable {

    private static final long serialVersionUID = -8659004786382288843L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long userId;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;
}
