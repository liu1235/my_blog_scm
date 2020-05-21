package com.blog.framework.dto.sys.role;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * TABLE_NAME:(t_sys_role)
 *
 * @author liuzw
 */

@Data
public class SysRoleAddDto implements Serializable {

    private static final long serialVersionUID = -3323839582637474170L;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 状态(0,禁用;1,启用)
     */
    @NotNull(message = "角色状态不能为空")
    @ApiModelProperty(value = "状态(0,禁用;1,启用)", required = true)
    private Integer roleStatus;

    /**
     * 角色对应菜单id
     */
    @ApiModelProperty(value = "角色对应菜单id")
    private List<Long> menuIds;

}