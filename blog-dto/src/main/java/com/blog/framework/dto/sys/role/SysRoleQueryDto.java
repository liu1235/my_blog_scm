package com.blog.framework.dto.sys.role;

import com.blog.framework.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TABLE_NAME:(t_sys_role)
 *
 * @author liuzw
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleQueryDto extends BasePage implements Serializable {


    private static final long serialVersionUID = 7704063219137464975L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", example = "超管")
    private String roleName;

    /**
     * 状态(0,禁用;1,启用)
     */
    @ApiModelProperty(value = "状态(0,禁用;1,启用)", example = "0")
    private Integer roleStatus;

}