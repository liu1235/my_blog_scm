package com.blog.framework.service;


import com.blog.framework.model.BlogModel;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;

import java.util.List;

/**
 * interface Blog
 *
 * @author liuzw
 */
public interface BlogDao {


    /**
     * 返回分页列表信息
     *
     * @param dto   数据
     * @return list<Blog>
     */
    List<BlogVO> list(BlogQueryDto dto);

    /**
     * 获取最热的十条博客
     *
     * @return List<BlogTopVO>
     */
    List<BlogTopVO> topBlogList();

    /**
     * 根据id返回信息
     *
     * @param id     id
     * @return BlogModel
     */
    BlogModel detail(Long id);

    /**
     * 根据主键更新
     * @param model 更新数据
     * @return Boolean
     */
    Boolean update(BlogModel model);


}