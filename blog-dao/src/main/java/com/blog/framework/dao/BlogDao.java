package com.blog.framework.dao;


import com.blog.framework.bo.BlogLikeOrCollectBo;
import com.blog.framework.bo.BlogQueryBo;
import com.blog.framework.model.BlogModel;
import com.blog.framework.vo.blog.BlogArchiveVO;
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
     * 获取博客列表数据
     *
     * @param bo 查询条件
     * @return list<Blog>
     */
    List<BlogVO> list(BlogQueryBo bo);

    /**
     * 归档
     *
     * @return List<BlogModel>
     */
    List<BlogArchiveVO> archive();


    /**
     * 获取最热的十条博客
     *
     * @return List<BlogTopVO>
     */
    List<BlogTopVO> topBlogList();


    /**
     * 获取喜欢或收藏的博客
     *
     * @param bo 查询条件
     * @return List<BlogVO>
     */
    List<BlogVO> getLikeOrCollectBlogList(BlogLikeOrCollectBo bo);

    /**
     * 根据id返回信息
     *
     * @param id id
     * @return BlogModel
     */
    BlogModel detail(Long id);

    /**
     * 根据主键更新
     *
     * @param model 更新数据
     * @return Boolean
     */
    Boolean update(BlogModel model);


    /**
     * 根据博客id批量获取
     *
     * @param blogIds 博客id
     * @return List<BlogModel>
     */
    List<BlogModel> getByIds(List<Long> blogIds);


}