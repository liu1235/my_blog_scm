package com.blog.framework.dto.sys.menu;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * TABLE_NAME:(t_sys_menu)
 *
 * @author liuzw
 */

@Data
public class SysMenuQueryDto implements Serializable {

    private static final long serialVersionUID = -4350429346756521328L;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 状态(0,禁用;1,启用)
     */
    @ApiModelProperty(value = "状态(0,禁用;1,启用)")
    private Integer menuStatus;

    /**
     * 平台（1：业务；2：其他）
     */
    @ApiModelProperty(value = "平台（1：业务；2：其他）")
    private Integer menuPlatform;

    /**
     * 菜单类型
     */
    @ApiModelProperty(hidden = true)
    private Integer menuType;


}