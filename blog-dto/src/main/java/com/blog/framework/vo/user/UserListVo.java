package com.blog.framework.vo.user;

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
public class UserListVo implements Serializable {

    private static final long serialVersionUID = 5169096858442774241L;
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 性别(0：女;1：男)
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private List<String> tags;


    /**
     * 是否展示友联(0:否;1:是)
     */
    @ApiModelProperty(value = "是否展示友联(0:否;1:是)")
    private Integer showFlag;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headPhoto;

    /**
     * 网站名称
     */
    @ApiModelProperty(value = "网站名称")
    private String websiteName;

    /**
     * 网站地址
     */
    @ApiModelProperty(value = "网站地址")
    private String websiteAddress;

    /**
     * 网站简介
     */
    @ApiModelProperty(value = "网站简介")
    private String websiteIntroduction;

    /**
     * 网站logo
     */
    @ApiModelProperty(value = "网站logo")
    private String websiteLogo;

}
