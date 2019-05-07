package com.liuzw.blog.service;


import com.liuzw.blog.common.Page;
import com.liuzw.blog.dto.BlogQueryDto;
import com.liuzw.blog.vo.BlogDetailVO;
import com.liuzw.blog.vo.BlogVO;

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
    Page<BlogVO> getList(BlogQueryDto dto);

    /**
     * 根据id返回信息
     * @param id     id
     * @return BlogModel
     */
    BlogDetailVO getById(Long id);


}