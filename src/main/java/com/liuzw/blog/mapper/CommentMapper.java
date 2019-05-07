package com.liuzw.blog.mapper;


import com.liuzw.blog.model.CommentModel;
import com.liuzw.blog.vo.CommentCountVo;
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
    List<CommentCountVo> getCommentCountByBlogId(List<Long> blogIds);

}