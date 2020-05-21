package com.blog.framework.dto.sys.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TABLE_NAME:(t_sys_user)
 *
 * @author liuzw
 */

@Data
public class SysUserAddDto {

    /**
     * 账户
     */
    @NotBlank(message = "账户不能为空")
    @ApiModelProperty(value = "账户", required = true, example = "zhangsan")
    private String account;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名", required = true, example = "zhangsan")
    private String userName;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", example = "[1,2]")
    private List<Long> roleIds;

    /**
     * 状态(0,禁用;1,启用)
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态(0,禁用;1,启用)", required = true, example = "1")
    private Integer userStatus;


}