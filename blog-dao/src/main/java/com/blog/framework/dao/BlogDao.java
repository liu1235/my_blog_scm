package com.blog.framework.service;


import com.blog.framework.model.BlogModel;
import com.blog.framework.dto.blog.BlogQueryDto;
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
     * 根据id返回信息
     *
     * @param id     id
     * @return BlogModel
     */
    BlogModel detail(Long id);


}