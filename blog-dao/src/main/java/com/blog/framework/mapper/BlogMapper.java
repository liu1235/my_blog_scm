package com.blog.framework.mapper;


import com.blog.framework.bo.BlogLikeOrCollectBo;
import com.blog.framework.bo.BlogQueryBo;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.dto.blog.manage.BlogManageQueryDto;
import com.blog.framework.model.BlogModel;
import com.blog.framework.vo.blog.BlogArchiveVO;
import com.blog.framework.vo.blog.manage.BlogListVO;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
     * @param dto 查询参数
     * @return List<BlogVO>
     */
    @Select({
            "<script>",
            " select t.id, t.title,t.class_id classId, t.description description,",
            " t.release_time releaseTime,t.create_time createTime, t.read_count readCount,t1.class_name className, t.status",
            " from t_blog t left join t_class t1 on t.class_id = t1.id ",
            " <where> ",
            " <if test = \" title != null and title != '' \"> and t.title like concat(#{title}, '%') </if>",
            " <if test = \" classId != null \"> and t.class_id = #{classId}  </if>",
            " <if test = \" status != null \">  and t.status = #{status}  </if>",
            " </where> ",
            " order by t.create_time desc",
            "</script>"
    })
    List<BlogListVO> getAllList(BlogManageQueryDto dto);


    /**
     * 获取博客列表数据
     *
     * @param bo 查询参数
     * @return List<BlogVO>
     */
    @Select({
            "<script>",
            " select t.id, t.title,t.class_id classId, t.description description,",
            " t.release_time releaseTime, t.read_count readCount,t1.class_name className",
            " from t_blog t left join t_class t1 on t.class_id = t1.id ",
            " where t.status = 1 ",
            " <if test = \" title != null and title != '' \"> and t.title like concat(#{title}, '%') </if>",
            " <if test = \" classIds != null and classIds.size() > 0 \"> ",
            " and t.class_id in ",
            "   <foreach collection = 'classIds' item = 'classId' separator = ',' open = '(' close = ')' > ",
            "    #{classId}",
            "   </foreach>",
            " </if>",
            " order by t.release_time desc",
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
            " where l.user_id = #{userId} and b.status = 1 ",
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
    @Select("select id, read_count readCount,title title from t_blog where status = 1 order by read_count desc limit 10")
    List<BlogTopVO> topBlogList();

    /**
     * 获取归档
     *
     * @return List<BlogTopVO>
     */
    @Select("select id, release_time releaseTime, title,description from t_blog where status = 1  order by release_time desc")
    List<BlogArchiveVO> archive();

    /**
     * 更新阅读次数
     *
     * @param id  博客id
     * @return Integer
     */
    @Update(" update t_blog set read_count = read_count + 1 where id = #{id} and status = 1")
    Integer updateReadCount(@Param("id") Long id);
}