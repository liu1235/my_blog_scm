package com.blog.framework.service;


import com.blog.framework.bo.BlogLikeOrCollectBo;
import com.blog.framework.bo.BlogQueryBo;
import com.blog.framework.common.PageBean;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.vo.blog.BlogArchiveVO;
import com.blog.framework.vo.blog.BlogDetailVO;
import com.blog.framework.vo.blog.BlogTopCommentVo;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;

import java.util.List;

/**
 * interface Blog
 *
 * @author liuzw
 */
public interface BlogService {


    /**
     * 获取博客列表数据
     *
     * @param bo 查询数据
     * @return list<Blog>
     */
    PageBean<BlogVO> list(BlogQueryBo bo);

    /**
     * 获取最热的十条博客
     *
     * @return List<BlogTopVO>
     */
    List<BlogTopVO> topBlogList();

    /**
     * 博客归档
     *
     * @return List<BlogArchiveVO>
     */
    List<BlogArchiveVO> archive();

    /**
     * 获取最新的十条评论的博客
     *
     * @return List<BlogTopCommentVo>
     */
    List<BlogTopCommentVo> topBlogCommentList();


    /**
     * 获取喜欢的博客列表数据
     *
     * @param bo 查询条件
     * @return PageBean<BlogVO>
     */
    PageBean<BlogVO> likeBlogList(BlogLikeOrCollectBo bo);

    /**
     * 获取收藏的博客列表数据
     *
     * @param bo 查询条件
     * @return PageBean<BlogVO>
     */
    PageBean<BlogVO> collectBlogList(BlogLikeOrCollectBo bo);

    /**
     * 根据id返回信息
     *
     * @param id id
     * @return BlogDetailVO
     */
    BlogDetailVO detail(Long id);

}