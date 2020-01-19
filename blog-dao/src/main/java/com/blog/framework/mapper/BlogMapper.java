package com.blog.framework.mapper;


import com.blog.framework.bo.BlogLikeOrCollectBo;
import com.blog.framework.bo.BlogQueryBo;
import com.blog.framework.model.BlogModel;
import com.blog.framework.vo.blog.BlogArchiveVO;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * interface BlogMapper
 *
 * @author liuzw
 */
public interface BlogMapper extends Mapper<BlogModel>, MySqlMapper<BlogModel> {

    /**
     * 获取博客列表数据
     *
     * @param bo 查询参数
     * @return List<BlogVO>
     */
    @Select({
            "<script>",
            " select t.id, t.title, t.content,t.class_id classId, t.description description,",
            " t.create_date createDate, t.read_count readCount,t1.class_name className",
            " from t_blog t left join t_class t1 on t.class_id = t1.id ",
            " <where> ",
            " <if test = \" title != null and title != '' \"> and t.title like concat(#{title}, '%') </if>",
            " <if test = \" classIds != null and classIds.size() > 0 \"> ",
            " and t.class_id in ",
            "   <foreach collection = 'classIds' item = 'classId' separator = ',' open = '(' close = ')' > ",
            "    #{classId}",
            "   </foreach>",
            " </if>",
            " </where> ",
            " order by t.create_date desc",
            "</script>"
    })
    List<BlogVO> list(BlogQueryBo bo);


    /**
     * 获取博客列表数据
     *
     * @param bo 查询参数
     * @return List<BlogVO>
     */
    @Select({
            "<script>",
            " select t.*, c.class_name from ",
            " (select b.id, b.title,b.read_count,b.description,b.class_id,b.create_date",
            " from t_like l",
            " join t_blog b on l.blog_id = b.id",
            " where l.user_id = #{userId} ",
            " <if test = \" title != null and title != '' \"> and b.title like concat(#{title}, '%') </if>",
            " <if test = \" likeStatus != null \"> and l.like_status = #{likeStatus} </if>",
            " <if test = \" collectStatus != null \"> and l.collect_status = #{collectStatus} </if>",
            " <if test = \" classIds != null and classIds.size() > 0 \"> ",
            " and b.class_id in ",
            "   <foreach collection = 'classIds' item = 'classId' separator = ',' open = '(' close = ')' > ",
            "    #{classId}",
            "   </foreach>",
            " </if>",
            ") t",
            " left join t_class c on t.class_id = c.id",
            " order by t.create_date desc",
            "</script>"
    })
    List<BlogVO> getLikeOrCollectBlogList(BlogLikeOrCollectBo bo);


    /**
     * 获取最热的十条博客
     *
     * @return List<BlogTopVO>
     */
    @Select("select id, read_count readCount,title title from t_blog order by read_count desc limit 10")
    List<BlogTopVO> topBlogList();

    /**
     * 获取归档
     *
     * @return List<BlogTopVO>
     */
    @Select("select id, create_date,title,description from t_blog order by create_date desc")
    List<BlogArchiveVO> archive();

}