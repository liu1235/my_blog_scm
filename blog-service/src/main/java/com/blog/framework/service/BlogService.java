package com.blog.framework.service;


import com.blog.framework.common.PageBean;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.vo.blog.BlogDetailVO;
import com.blog.framework.vo.blog.BlogVO;

/**
 * interface Blog
 *
 * @author liuzw
 */
public interface BlogService {


    /**
     * 返回分页列表信息
     *
     * @param dto   数据
     * @return list<Blog>
     */
    PageBean<BlogVO> list(BlogQueryDto dto);

    /**
     * 根据id返回信息
     *
     * @param id     id
     * @return BlogModel
     */
    BlogDetailVO detail(Long id);


}