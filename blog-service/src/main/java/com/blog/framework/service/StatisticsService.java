package com.blog.framework.service;

import com.blog.framework.dto.statistics.StatisticsQueryDto;
import com.blog.framework.vo.statistics.StatisticsChartVo;
import com.blog.framework.vo.statistics.StatisticsVo;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
public interface StatisticsService {

    /**
     * 统计基础数据
     *
     * @return StatisticsVo
     */
    StatisticsVo statistics();

    /**
     * 统计博客发布数据
     *
     * @param dto 查询参数
     * @return StatisticsChartVo
     */
    StatisticsChartVo statisticsBlog(StatisticsQueryDto dto);

    /**
     * 统计博客分类数据
     *
     * @return StatisticsChartVo
     */
    StatisticsChartVo statisticsBlogClass();

    /**
     * 统计博客分类子类数据
     *
     * @return StatisticsChartVo
     */
    StatisticsChartVo statisticsBlogClassChild(Long classId);

    /**
     * 统计用户数据
     *
     * @param dto 查询参数
     * @return StatisticsChartVo
     */
    StatisticsChartVo statisticsUser(StatisticsQueryDto dto);
}
