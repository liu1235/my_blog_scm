package com.blog.framework.dao;

import com.blog.framework.bo.StatisticsBlogClassBo;
import com.blog.framework.common.KeyValueBean;
import com.blog.framework.dto.statistics.StatisticsQueryDto;
import com.blog.framework.vo.statistics.StatisticsVo;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
public interface StatisticsDao {

    /**
     * 统计基础数据
     *
     * @param dto 查询参数
     * @return StatisticsVo
     */
    StatisticsVo statistics(StatisticsQueryDto dto);

    /**
     * 统计博客发布数据
     *
     * @param dto 查询参数
     * @return StatisticsChartVo
     */
    List<KeyValueBean<String, Long>> statisticsBlog(StatisticsQueryDto dto);

    /**
     * 统计博客分类数据
     *
     * @return StatisticsChartVo
     */
    List<StatisticsBlogClassBo> statisticsBlogClass();

    /**
     * 统计博客分类子类数据
     *
     * @param classIds 分类id
     * @return StatisticsChartVo
     */
    List<KeyValueBean<String, Long>> statisticsBlogClassChild(List<Long> classIds);

    /**
     * 统计用户数据
     *
     * @param dto 查询参数
     * @return StatisticsChartVo
     */
    List<KeyValueBean<String, Long>> statisticsUser(StatisticsQueryDto dto);
}
