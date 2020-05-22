package com.blog.framework.dto.statistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
@Data
public class StatisticsQueryDto {

    /**
     * 起始时间
     */
    @ApiModelProperty("起始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;
    
}
