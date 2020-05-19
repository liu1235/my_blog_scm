package com.blog.framework.mapper;

import com.blog.framework.bo.StatisticsBlogClassBo;
import com.blog.framework.common.KeyValueBean;
import com.blog.framework.dto.statistics.StatisticsQueryDto;
import com.blog.framework.vo.statistics.StatisticsBlogClassVo;
import com.blog.framework.vo.statistics.StatisticsChartVo;
import com.blog.framework.vo.statistics.StatisticsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
public interface StatisticsMapper {

    /**
     * 统计基础数据
     *
     * @return StatisticsVo
     */
    @Select(" SELECT SUM(blogTotal) blogTotal, SUM(blogNum) blogNum, SUM(userTotal) userTotal, SUM(userNum) userNum" +
            " FROM " +
            "(" +
            " SELECT COUNT(id) AS blogTotal, 0 AS blogNum, 0 AS userTotal, 0 AS userNum" +
            " FROM t_blog UNION ALL" +
            " SELECT 0 blogTotal, COUNT(id) AS blogNum, 0 AS userTotal, 0 AS userNum" +
            " FROM t_blog" +
            " WHERE release_time BETWEEN #{startTime} AND #{endTime} AND `status` = 1 UNION ALL" +
            " SELECT 0 AS blogTotal, 0 AS blogNum, COUNT(id) AS userTotal, 0 AS userNum" +
            " FROM t_user UNION ALL" +
            " SELECT COUNT(id) blogTotal, 0 AS blogNum, 0 AS userTotal, 0 AS userNum" +
            " FROM t_user" +
            " WHERE create_time BETWEEN #{startTime} AND #{endTime} " +
            ") temp")
    StatisticsVo statistics(StatisticsQueryDto dto);

    /**
     * 统计博客发布数据
     *
     * @param dto 查询参数
     * @return StatisticsChartVo
     */
    @Select(" SELECT DATE_FORMAT(release_time, '%Y-%m-%d') `key`, COUNT(id) value FROM t_blog" +
            " WHERE release_time " +
            " BETWEEN #{startTime} AND #{endTime} AND `status` = 1" +
            " GROUP BY `key`")
    List<KeyValueBean<String, Long>> statisticsBlog(StatisticsQueryDto dto);

    /**
     * 统计博客分类数据
     *
     * @return StatisticsChartVo
     */
    @Select(" SELECT c.class_name className, b.class_id classId , c.parent_id parentId, b.id bloId " +
            " FROM t_blog b " +
            " left join t_class c on c.id = b.class_id" +
            " WHERE  b.`status` = 1 ")
    List<StatisticsBlogClassBo> statisticsBlogClass();

    /**
     * 统计用户数据
     *
     * @param dto 查询参数
     * @return StatisticsChartVo
     */
    @Select(" SELECT DATE_FORMAT(create_time, '%Y-%m-%d') `key`, COUNT(id) value FROM t_user" +
            " WHERE create_time " +
            " BETWEEN #{startTime} AND #{endTime} " +
            " GROUP BY `key`")
    List<KeyValueBean<String, Long>> statisticsUser(StatisticsQueryDto dto);
}
