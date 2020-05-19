package com.blog.framework.dao.impl;

import com.blog.framework.bo.StatisticsBlogClassBo;
import com.blog.framework.common.KeyValueBean;
import com.blog.framework.dao.StatisticsDao;
import com.blog.framework.dto.statistics.StatisticsQueryDto;
import com.blog.framework.mapper.StatisticsMapper;
import com.blog.framework.vo.statistics.StatisticsChartVo;
import com.blog.framework.vo.statistics.StatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
@Repository
public class StatisticsDaoImpl implements StatisticsDao {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public StatisticsVo statistics(StatisticsQueryDto dto) {
        return statisticsMapper.statistics(dto);
    }

    @Override
    public List<KeyValueBean<String, Long>> statisticsBlog(StatisticsQueryDto dto) {
        return statisticsMapper.statisticsBlog(dto);
    }

    @Override
    public List<StatisticsBlogClassBo> statisticsBlogClass() {
        return statisticsMapper.statisticsBlogClass();
    }

    @Override
    public List<KeyValueBean<String, Long>> statisticsUser(StatisticsQueryDto dto) {
        return statisticsMapper.statisticsUser(dto);
    }
}
