package com.blog.framework.dto.user;

import com.blog.framework.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 更新用户信息
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserQueryDto extends BasePage implements Serializable {

    private static final long serialVersionUID = -807716512670666628L;

    /**
     * 用户名/昵称
     */
    @ApiModelProperty(value = "用户名/昵称")
    private String userName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

}
