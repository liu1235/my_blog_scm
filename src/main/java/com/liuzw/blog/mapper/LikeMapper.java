package com.liuzw.blog.mapper;


import com.liuzw.blog.model.LikeModel;
import com.liuzw.blog.vo.LikeCountVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * interface LikeMapper
 *
 * @author liuzw
 */
public interface LikeMapper extends Mapper<LikeModel>, MySqlMapper<LikeModel> {


    /**
     * 统计各个博客的收藏数和喜欢数
     *
     * @param blogIds 博客id集合
     * @return List<LikeCountVO>
     */
    @Select({
            "<script>",
            " select t.blog_id, sum(t.like_status) likeCount, sum(t.collect_status) collectCount",
            " from t_like t",
            " where t.blog_id in",
            " <foreach collection = 'blogIds' item = 'blogId' separator = ',' open = '(' close = ')' > ",
            " #{blogId}",
            " </foreach>",
            " group by t.blog_id",
            "</script>"
    })
    List<LikeCountVO> getCountByBlogId(@Param("blogIds") List<Long> blogIds);

}