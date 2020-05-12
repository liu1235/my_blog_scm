package com.blog.framework.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 更新用户信息
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = -8659004786382288843L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 性别(0：女;1：男)
     */
    @NotNull(message = "性别不能为空")
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
