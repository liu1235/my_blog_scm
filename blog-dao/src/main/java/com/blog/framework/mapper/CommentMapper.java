package com.blog.framework.mapper;


import com.blog.framework.model.CommentModel;
import com.blog.framework.vo.CommentCountVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * interface CommentMapper
 *
 * @author liuzw
 */
public interface CommentMapper extends Mapper<CommentModel>, MySqlMapper<CommentModel> {


    /**
     * 统计各个博客的评论数
     *
     * @param blogIds 博客id集合
     * @return List<CommentCountVo>
     */
    @Select({
            "<script>",
            " select t.blog_id, count(1) commentCount from t_comment t ",
            " left join t_reply t1 on t.id = t1.comment_id",
            " where t.blog_id in",
            " <foreach collection = 'blogIds' item = 'blogId' separator = ',' open = '(' close = ')' > ",
            " #{blogId}",
            " </foreach>",
            " group by t.blog_id",
            "</script>"
    })
    List<CommentCountVo> getCommentCountByBlogId(@Param("blogIds") List<Long> blogIds);

    /**
     * 获取最新的十条评论
     *
     * @return List<CommentModel>
     */
    @Select("select * from t_comment order by create_time desc limit 10")
    List<CommentModel> topCommentList();
}