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
public class StatisticsBlogClassVo {

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String name;

    /**
     * 博客数量
     */
    @ApiModelProperty("博客数量")
    private String value;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Long classId;
}
