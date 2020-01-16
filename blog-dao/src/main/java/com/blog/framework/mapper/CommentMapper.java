package com.blog.framework.mapper;


import com.blog.framework.model.CommentModel;
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
     * 获取最新的十条评论博客id
     *
     * @return List<CommentModel>
     */
    @Select("select blog_id,content,id,user_id from t_comment where comment_type = 1 order by create_time desc limit 10")
    List<CommentModel> topCommentList();
}