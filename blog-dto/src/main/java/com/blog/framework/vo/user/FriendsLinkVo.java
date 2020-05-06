package com.blog.framework.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class FriendsLinkVo {

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
