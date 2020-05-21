package com.blog.framework.model.sys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * TABLE_NAME:(t_sys_menu)
 *
 *  @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="t_sys_menu")
public class SysMenuModel {


    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父菜单id，一级菜单为0
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单url
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 授权如:sys:user:view
     */
    @Column(name = "menu_perms")
    private String menuPerms;

    /**
     * 类型 (1,目录;2,菜单;3,按钮)
     */
    @Column(name = "menu_type")
    private Integer menuType;

    /**
     * 菜单图标
     */
    @Column(name = "menu_icons")
    private String menuIcons;

    /**
     * 排序
     */
    @Column(name = "menu_order")
    private Integer menuOrder;

    /**
     * 状态(0,禁用;1,启用)
     */
    @Column(name = "menu_status")
    private Integer menuStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
	

}