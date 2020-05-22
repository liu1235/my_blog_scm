package com.blog.framework.vo.statistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
@Data
public class StatisticsVo {

    /**
     * 发布博客总数
     */
    @ApiModelProperty("发布博客总数")
    private Integer blogTotal;

    /**
     * 发布博客数
     */
    @ApiModelProperty("发布博客数")
    private Integer blogNum;

    /**
     * 注册用户总数
     */
    @ApiModelProperty("注册用户总数")
    private Integer userTotal;

    /**
     * 注册用户数
     */
    @ApiModelProperty("注册用户数")
    private Integer userNum;
}
