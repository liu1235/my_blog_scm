package com.blog.framework.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsBlogClassBo {

    /**
     * 博客数量
     */
    @ApiModelProperty("博客数量")
    private Long blogNum;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String className;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Long classId;
}
