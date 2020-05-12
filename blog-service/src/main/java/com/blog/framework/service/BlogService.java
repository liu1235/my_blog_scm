package com.blog.framework.service;


import com.blog.framework.bo.BlogLikeOrCollectBo;
import com.blog.framework.bo.BlogQueryBo;
import com.blog.framework.common.PageBean;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.dto.blog.manage.BlogAddDto;
import com.blog.framework.dto.blog.manage.BlogManageQueryDto;
import com.blog.framework.dto.blog.manage.BlogUpdateDto;
import com.blog.framework.vo.blog.BlogArchiveVO;
import com.blog.framework.vo.blog.BlogDetailVO;
import com.blog.framework.vo.blog.BlogTopCommentVo;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
import com.blog.framework.vo.blog.manage.BlogListVO;
import com.blog.framework.vo.blog.manage.BlogManageDetailVO;

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
     * @return list<BlogListVO>
     */
    PageBean<BlogListVO> list(BlogManageQueryDto bo);

    /**
     * 获取博客详情
     *
     * @param id id
     * @return BlogManageDetailVO
     */
    BlogManageDetailVO detailBlog(Long id);

    /**
     * 新增博客
     *
     * @param addDto 新增数据
     * @return Boolean
     */
    Boolean add(BlogAddDto addDto);

    /**
     * 编辑博客
     *
     * @param updateDto 编辑博客
     * @return Boolean
     */
    Boolean edit(BlogUpdateDto updateDto);

    /**
     * 发布博客
     *
     * @param ids 博客id集合
     * @return Boolean
     */
    Boolean published(List<Long> ids);

    /**
     * 取消发布博客
     *
     * @param ids 博客id集合
     * @return Boolean
     */
    Boolean unpublished(List<Long> ids);


    /**
     * 删除博客
     *
     * @param id 博客id
     * @return Boolean
     */
    Boolean delete(Long id);



    /**
     * 获取已发布博客列表数据
     *
     * @param bo 查询数据
     * @return list<BlogVO>
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

    /**
     * 更新阅读次数
     *
     * @param id id
     * @return BlogDetailVO
     */
    Boolean updateReadCount(Long id);


}