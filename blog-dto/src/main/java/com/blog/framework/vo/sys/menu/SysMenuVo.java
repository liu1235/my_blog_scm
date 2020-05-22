package com.blog.framework.vo.sys.menu;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TABLE_NAME:(t_sys_menu)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuVo {

    /**
     *
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 父菜单id，一级菜单为0
     */
    @ApiModelProperty(value = "父菜单id，一级菜单为0")
    private Long parentId;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
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
     * 类型 (1,目录;2,菜单;3,按钮)
     */
    @ApiModelProperty(hidden = true)
    private Integer menuType;
    /**
     * 类型 (1,目录;2,菜单;3,按钮)
     */
    @ApiModelProperty(value = "类型 (1,目录;2,菜单;3,按钮)")
    private String menuTypeName;

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
    @ApiModelProperty(value = "状态(0,禁用;1,启用)")
    private Integer menuStatus;

    /**
     * 状态(0,禁用;1,启用)
     */
    @ApiModelProperty(value = "状态(0,禁用;1,启用)")
    private String menuStatusName;

    /**
     * 子级
     */
    private List<SysMenuVo> children;
}