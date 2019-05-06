/**
 * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved.
 */

package com.liuzw.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * TABLE_NAME:(t_user)
 *
 *  @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
public class UserModel {


    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名/昵称
     */
    @Column(name = "username")
    private String userName;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 性别  0：女   1：男
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 标签
     */
    @Column(name = "tags")
    private String tags;


    /**
     * 状态(0:未激活;1:已激活)
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 是否展示友联(0:否;1:是)
     */
    @Column(name = "show_flag")
    private Integer showFlag;

    /**
     * 头像
     */
    @Column(name = "head_photo")
    private String headPhoto;

    /**
     * 网站名称
     */
    @Column(name = "website_name")
    private String websiteName;

    /**
     * 网站地址
     */
    @Column(name = "website_address")
    private String websiteAddress;

    /**
     * 网站简介
     */
    @Column(name = "website_introduction")
    private String websiteIntroduction;

    /**
     * 网站logo
     */
    @Column(name = "website_logo")
    private String websiteLogo;

    /**
     * 激活码
     */
    @Column(name = "activation_code")
    private String activationCode;

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