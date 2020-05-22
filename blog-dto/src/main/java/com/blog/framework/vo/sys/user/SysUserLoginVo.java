package com.blog.framework.vo.sys.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserLoginVo implements Serializable {

    private static final long serialVersionUID = -8659004786382288843L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * token
     */
    private String token;

    /**
     * 是否管理员(0,否;1,是)
     */
    private Integer adminFlag;

    /**
     * 用户拥有权限
     */
    private List<String> permsList;

    /**
     * 用户能够访问的路由
     */
    private List<String> urls;

    /**
     * 用户拥有菜单
     */
    private List<?> menuList;
}
