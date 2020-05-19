package com.blog.framework.service.impl;

import com.blog.framework.bo.StatisticsBlogClassBo;
import com.blog.framework.common.KeyValueBean;
import com.blog.framework.common.utils.DateUtil;
import com.blog.framework.dao.StatisticsDao;
import com.blog.framework.dto.statistics.StatisticsQueryDto;
import com.blog.framework.service.StatisticsService;
import com.blog.framework.vo.statistics.StatisticsChartVo;
import com.blog.framework.vo.statistics.StatisticsVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public StatisticsVo statistics() {
        StatisticsQueryDto dto = new StatisticsQueryDto();
        Date date = new Date();
        dto.setStartTime(DateUtil.getMorningDate(date));
        dto.setEndTime(DateUtil.getEveningDate(date));
        return statisticsDao.statistics(dto);
    }

    @Override
    public StatisticsChartVo statisticsBlog(StatisticsQueryDto dto) {
        buildQueryData(dto);
        //获取时间段类的数据
        List<KeyValueBean<String, Long>> list = statisticsDao.statisticsBlog(dto);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        buildData(dto, list);
        return StatisticsChartVo.builder()
                .columns(Arrays.asList("日期", "今日发博客数数"))
                .rows(list)
                .build();
    }

    @Override
    public StatisticsChartVo statisticsBlogClass() {
        List<StatisticsBlogClassBo> list = statisticsDao.statisticsBlogClass();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        //获取一级分类
        Map<Long, List<StatisticsBlogClassBo>> map = list.stream().filter(v -> v.getParentId() == 0)
                .collect(Collectors.groupingBy(StatisticsBlogClassBo::getClassId));

        //获取一级分类
        Map<Long, String> classMap = list.stream().filter(v -> v.getParentId() == 0)
                .collect(Collectors.toMap(StatisticsBlogClassBo::getClassId, StatisticsBlogClassBo::getClassName, (v1, v2) -> v2));

        //获取二级分类
        Map<Long, List<StatisticsBlogClassBo>> secondMap = list.stream().filter(v -> v.getParentId() != 0)
                .collect(Collectors.groupingBy(StatisticsBlogClassBo::getParentId));

        List<KeyValueBean<String, Long>> result = new ArrayList<>();

        for (Map.Entry<Long, List<StatisticsBlogClassBo>> entry : map.entrySet()) {
            Long classId = entry.getKey();
            int size = entry.getValue().size();
            size += secondMap.get(classId).size();
            result.add(KeyValueBean.<String, Long>builder()
                    .key(classMap.get(classId))
                    .value((long) size)
                    .build());
        }

        return StatisticsChartVo.builder()
                .columns(Arrays.asList("分类", "博客数量"))
                .rows(result)
                .build();
    }

    @Override
    public StatisticsChartVo statisticsUser(StatisticsQueryDto dto) {
        buildQueryData(dto);
        //获取时间段类的数据
        List<KeyValueBean<String, Long>> list = statisticsDao.statisticsUser(dto);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        buildData(dto, list);
        return StatisticsChartVo.builder()
                .columns(Arrays.asList("日期", "今日注册用户数"))
                .rows(list)
                .build();
    }


    /**
     * 处理请求参数
     */
    private void buildQueryData(StatisticsQueryDto dto) {
        //判断时间是否为空
        if (dto.getStartTime() == null || dto.getEndTime() == null) {
            dto.setEndTime(new Date());
            //获取最近的30天
            Date beforeDay = DateUtil.getBeforeDay(30);
            dto.setStartTime(beforeDay);
        }
        dto.setStartTime(DateUtil.getMorningDate(dto.getStartTime()));
        dto.setEndTime(DateUtil.getEveningDate(dto.getEndTime()));
    }

    /**
     * 封装返回数据
     */
    private void buildData(StatisticsQueryDto dto, List<KeyValueBean<String, Long>> list) {
        //获取时间区间内的所有时间
        List<String> dates = DateUtil.getBetweenDates(DateUtil.convertDateToString(dto.getStartTime()), DateUtil.convertDateToString(dto.getEndTime()));
        List<String> collect = list.stream().map(KeyValueBean::getKey).collect(Collectors.toList());
        for (String date : dates) {
            if (!collect.contains(date)) {
                list.add(KeyValueBean.<String, Long>builder()
                        .key(date)
                        .value(0L)
                        .build());
            }
        }
        //按照时间排序
        list.sort(Comparator.comparing(KeyValueBean::getKey));
    }
}
