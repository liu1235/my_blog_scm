package com.blog.framework.mapper;


import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.model.BlogModel;
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
     * @param dto 查询参数
     * @return List<BlogVO>
     */
    @Select({
            "<script>",
            " select t.id, t.title, t.content,t.class_id, t.create_date, t.read_count,t1.class_name",
            " from t_blog t left join t_blog_class t1 on t.class_id = t1.id ",
            " <where> ",
            " <if test = \" title != null and title != '' \"> and t.title like concat(#{title}, '%') </if>",
            " <if test = \" classId != null \"> and t.class_id = #{classId} </if>",
            " <if test = \" blogIds != null blogIds.size() > 0 \"> ",
            " and t.id in ",
            "   <foreach collection = 'blogIds' item = 'blogId' separator = ',' open = '(' close = ')' > ",
            "    #{blogId}",
            "   </foreach>",
            " </if>",
            " </where> ",
            " order by t.create_date desc",
            "</script>"
    })
    List<BlogVO> list(BlogQueryDto dto);


}