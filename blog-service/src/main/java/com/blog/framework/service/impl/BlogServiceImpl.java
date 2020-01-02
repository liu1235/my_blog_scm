package com.blog.framework.service.impl;


import com.blog.framework.common.PageBean;
import com.blog.framework.common.enums.StatusEnum;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.dao.CommentDao;
import com.blog.framework.dao.LikeDao;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.model.BlogModel;
import com.blog.framework.model.LikeModel;
import com.blog.framework.service.BlogDao;
import com.blog.framework.service.BlogService;
import com.blog.framework.vo.CommentCountVo;
import com.blog.framework.vo.LikeCountVO;
import com.blog.framework.vo.LikeVO;
import com.blog.framework.vo.blog.BlogDetailVO;
import com.blog.framework.vo.blog.BlogVO;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BlogServiceImpl
 *
 * @author liuzw
 */
@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogDao blogDao;

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private CommentDao commentDao;


    @Override
    public PageBean<BlogVO> list(BlogQueryDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        //获取数据
        List<BlogVO> list = blogDao.list(dto);
        if (CollectionUtils.isEmpty(list)) {
            return new PageBean<>();
        }
        //获取博客id集合
        List<Long> blogIdList = list.stream().map(BlogVO::getId).distinct().collect(Collectors.toList());
        //收藏数和点赞数
        Map<Long, LikeCountVO> likeCountMap = getLikeCountMap(blogIdList);
        //统计评论数
        Map<Long, CommentCountVo> commentCountMap = getCommentCount(blogIdList);

        for (BlogVO vo : list) {
            LikeCountVO likeCountVO = likeCountMap.get(vo.getId());
            //组装收藏数和点赞数
            if (likeCountVO != null) {
                vo.setCollectCount(likeCountVO.getCollectCount());
                vo.setLikeCount(likeCountVO.getLikeCount());
            }
            //组装评论数
            CommentCountVo commentCountVo = commentCountMap.get(vo.getId());
            if (commentCountVo != null) {
                vo.setCommentCount(commentCountVo.getCommentCount());
            }
        }
        return PageBean.createPageBean(list);
    }

    @Override
    public PageBean<BlogVO> likeBlogList() {
        return getBlogList(LikeModel.builder()
                .likeStatus(StatusEnum.EFFECTIVE.getCode())
                .build());
    }

    @Override
    public PageBean<BlogVO> collectBlogList() {
        return getBlogList(LikeModel.builder()
                .collectStatus(StatusEnum.EFFECTIVE.getCode())
                .build());
    }

    @Override
    public BlogDetailVO detail(Long id) {
        //获取详情
        BlogModel blogModel = blogDao.detail(id);

        BlogVO blogVO = CopyDataUtil.copyObject(blogModel, BlogVO.class);
        //博客id
        Long blogId = blogVO.getId();

        List<Long> blogIds = Lists.newArrayList(blogId);

        //收藏数和点赞数
        Map<Long, LikeCountVO> likeCountMap = getLikeCountMap(blogIds);
        //统计评论数
        Map<Long, CommentCountVo> commentCountMap = getCommentCount(blogIds);

        //收藏数和点赞数
        LikeCountVO likeCountVO = likeCountMap.get(blogId);
        if (likeCountVO == null) {
            blogVO.setLikeCount(0);
            blogVO.setCollectCount(0);
        } else {
            blogVO.setLikeCount(likeCountVO.getLikeCount());
            blogVO.setCollectCount(likeCountVO.getCollectCount());
        }
        //评论数
        CommentCountVo commentCountVo = commentCountMap.get(blogId);
        if (commentCountVo == null) {
            blogVO.setCommentCount(0);
        } else {
            blogVO.setCommentCount(commentCountVo.getCommentCount());
        }

        //获取当前阅读人的点赞和收藏状态
        LikeVO likeVO = LikeVO.builder()
                .collectStatus(StatusEnum.INVALID.getCode())
                .likeStatus(StatusEnum.INVALID.getCode())
                .build();
        //todo 用户id获取
        Long userId = null;
        if (userId != null) {
            LikeModel likeModel = likeDao.getLikeByUserIdAndBlogId(blogId, null);
            if (likeModel != null) {
                likeVO = CopyDataUtil.copyObject(likeModel, LikeVO.class);
            }
        }
        return BlogDetailVO.builder()
                .like(likeVO)
                .blog(blogVO)
                .build();
    }

    /**
     * 统计各个博客文章的收藏数和点赞数
     *
     * @param blogIdList 博客id集合
     * @return Map<blogId, LikeCountVO>
     */
    private Map<Long, LikeCountVO> getLikeCountMap(List<Long> blogIdList) {
        if (CollectionUtils.isEmpty(blogIdList)) {
            return Collections.emptyMap();
        }
        //统计各个博客文章的收藏数和点赞数
        List<LikeCountVO> list = likeDao.getCountByBlogId(blogIdList);
        //将list转换成map<blogId, LikeCountVO>
        return list.stream().collect(Collectors.toMap(LikeCountVO::getBlogId, s -> s));
    }


    /**
     * 获取评论数
     *
     * @param blogIdList 博客id集合
     * @return Integer
     */
    private Map<Long, CommentCountVo> getCommentCount(List<Long> blogIdList) {
        if (CollectionUtils.isEmpty(blogIdList)) {
            return Collections.emptyMap();
        }
        //统计各个博客评论数
        List<CommentCountVo> list = commentDao.getCommentCountByBlogId(blogIdList);
        //将list转换成map<blogId, LikeCountVO>
        return list.stream().collect(Collectors.toMap(CommentCountVo::getBlogId, s -> s));
    }

    private PageBean<BlogVO> getBlogList(LikeModel model) {
        //todo 获取当前登录人id
        Long userId = null;
        if (userId == null) {
            return new PageBean<>();
        }
        model.setUserId(userId);
        List<LikeModel> likeModels = likeDao.select(model);
        if (CollectionUtils.isEmpty(likeModels)) {
            return new PageBean<>();
        }
        //获取对应博客id
        List<Long> blogIds = likeModels.stream().map(LikeModel::getBlogId).collect(Collectors.toList());
        BlogQueryDto dto = new BlogQueryDto();
        dto.setBlogIds(blogIds);
        return list(dto);
    }

}