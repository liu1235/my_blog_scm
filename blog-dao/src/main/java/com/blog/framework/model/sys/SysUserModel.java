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
 * TABLE_NAME:(t_sys_user)
 *
 *  @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="t_sys_user")
public class SysUserModel {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账户
     */
    @Column(name = "account")
    private String account;

    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 状态(0,禁用;1,启用)
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * 是否管理员(0,否;1,是)
     */
    @Column(name = "admin_flag")
    private Integer adminFlag;

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