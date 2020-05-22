package com.blog.framework.vo.statistics;

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
    private Long value;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Long classId;
}
