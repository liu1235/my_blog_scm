package com.blog.framework.service;


import com.blog.framework.dto.like.LikeDto;

/**
 * interface Like
 *
 * @author liuzw
 */
public interface LikeService {

    /**
     * 喜欢
     *
     * @param dto 博客id
     * @return Boolean
     */
    Boolean like(LikeDto dto);

    /**
     * 收藏
     *
     * @param dto 博客id
     * @return Boolean
     */
    Boolean collect(LikeDto dto);

    /**
     * 展示like me的数据
     *
     * @return Integer
     */
    Integer likeMeData();

    /**
     * 新增喜欢我的数据
     *
     */
    void addLikeMeData();


}