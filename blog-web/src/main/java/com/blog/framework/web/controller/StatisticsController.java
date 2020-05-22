package com.blog.framework.web.controller;

import com.blog.framework.common.Id;
import com.blog.framework.common.ResultData;
import com.blog.framework.dto.statistics.StatisticsQueryDto;
import com.blog.framework.service.StatisticsService;
import com.blog.framework.vo.statistics.StatisticsChartVo;
import com.blog.framework.vo.statistics.StatisticsVo;
import com.blog.framework.web.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Api(tags = "统计")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 统计基础数据
     *
     * @return ResultData
     */
    @SysLog
    @ApiOperation(value = "统计基础数据")
    @PostMapping("/base")
    public ResultData<StatisticsVo> statistics() {
        return ResultData.createSelectResult(statisticsService.statistics());
    }

    /**
     * 统计博客发布数据
     *
     * @param dto 查询参数
     * @return ResultData
     */
    @SysLog
    @ApiOperation(value = "统计博客发布数据")
    @PostMapping("/statisticsBlog")
    public ResultData<StatisticsChartVo> statisticsBlog(@RequestBody StatisticsQueryDto dto) {
        return ResultData.createSelectResult(statisticsService.statisticsBlog(dto));
    }

    /**
     * 统计博客分类数据
     *
     * @return ResultData
     */
    @SysLog
    @ApiOperation(value = "统计博客分类数据")
    @PostMapping("/statisticsBlogClass")
    public ResultData<StatisticsChartVo> statisticsBlogClass() {
        return ResultData.createSelectResult(statisticsService.statisticsBlogClass());
    }

    /**
     * 统计博客分类子类数据
     *
     * @return ResultData<StatisticsChartVo>
     */
    @SysLog
    @ApiOperation(value = "统计博客分类子类数据")
    @PostMapping("/statisticsBlogClassChild")
    public ResultData<StatisticsChartVo> statisticsBlogClassChild(@RequestBody Id<Long> longId) {
        return ResultData.createSelectResult(statisticsService.statisticsBlogClassChild(longId.getId()));
    }



    /**
     * 统计用户数据
     *
     * @param dto 查询参数
     * @return ResultData
     */
    @SysLog
    @ApiOperation(value = "统计用户数据")
    @PostMapping("/statisticsUser")
    public ResultData<StatisticsChartVo> statisticsUser(@RequestBody StatisticsQueryDto dto) {
        return ResultData.createSelectResult(statisticsService.statisticsUser(dto));
    }
}
