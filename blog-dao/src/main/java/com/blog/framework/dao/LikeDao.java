package com.blog.framework.dao;

import com.blog.framework.model.LikeModel;
import com.blog.framework.vo.LikeCountVO;

import java.util.List;

/**
 * my_blog_scm
 *
 * @author liuzw
 * @date 2020-01-02
 **/
public interface LikeDao {

    /**
     * 根据条件获取数据
     *
     * @param model 查询条件
     * @return LikeModel
     */
    List<LikeModel> select(LikeModel model);

    /**
     * 根据用户id和博客id获取点赞和喜欢状态
     *
     * @param blogId 博客id
     * @param userId 用户id
     * @return LikeModel
     */
    LikeModel getLikeByUserIdAndBlogId(Long blogId, Long userId);

    /**
     * 统计博客的喜欢和收藏数
     *
     * @param blogIds 博客id
     * @return List<LikeCountVO>
     */
    List<LikeCountVO> getCountByBlogId(List<Long> blogIds);

    /**
     * 根据博客id和用户id更新喜欢和收藏状态
     *
     * @param model 更新条件
     * @return Boolean
     */
    Boolean update(LikeModel model);
}
