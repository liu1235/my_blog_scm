package com.blog.framework.dto.sys.menu;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * TABLE_NAME:(t_sys_menu)
 *
 * @author liuzw
 */

@Data
public class SysMenuAddDto implements Serializable {

    private static final long serialVersionUID = -5088712218787603535L;
    /**
     * 父菜单id，一级菜单为0
     */
    @NotNull(message = "父菜单id不能为空")
    @ApiModelProperty(value = "父菜单id，一级菜单为0", required = true, example = "0")
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名称", required = true, example = "测试")
    private String menuName;

    /**
     * 菜单url
     */
    @ApiModelProperty(value = "菜单url")
    private String menuUrl;

    /**
     * 授权如:sys:user:view
     */
    @ApiModelProperty(value = "授权如:sys:user:view")
    private String menuPerms;

    /**
     * 菜单code
     */
    @ApiModelProperty(value = "菜单code")
    private String menuCode;

    /**
     * 菜单对应前端页面路径（如: /sys/user.vue）
     */
    @ApiModelProperty(value = "菜单对应前端页面路径（如: /sys/user.vue）")
    private String menuPath;

    /**
     * 类型 (1,目录;2,菜单;3,按钮)
     */
    @NotNull(message = "菜单类型不能为空")
    @ApiModelProperty(value = "菜单类型 (1,目录;2,菜单;3,按钮)", required = true)
    private Integer menuType;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String menuIcons;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer menuOrder;

    /**
     * 状态(0,禁用;1,启用)
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态(0,禁用;1,启用)", required = true)
    private Integer menuStatus;

    /**
     * 平台（1：业务；2：其他）
     */
    @ApiModelProperty(value = "平台（1：业务；2：其他）")
    private Integer menuPlatform;


}