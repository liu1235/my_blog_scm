package com.blog.framework.vo.statistics;

import com.blog.framework.common.KeyValueBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

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
public class StatisticsChartVo {

    /**
     * 列名
     */
    @ApiModelProperty("列名")
    private List<String> columns;

    /**
     * 每列数据
     */
    @ApiModelProperty("每列数据")
    private List<Map<String, Object>> rows;


    /**
     * 每列数据
     */
    @ApiModelProperty("每列数据")
    private List<StatisticsBlogClassVo> data;
}
