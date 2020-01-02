package com.liuzw.blog.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.liuzw.blog.common.Page;
import com.liuzw.blog.dto.BlogQueryDto;
import com.liuzw.blog.enums.StatuEnum;
import com.liuzw.blog.mapper.BlogMapper;
import com.liuzw.blog.mapper.CommentMapper;
import com.liuzw.blog.mapper.LikeMapper;
import com.liuzw.blog.model.BlogModel;
import com.liuzw.blog.model.LikeModel;
import com.liuzw.blog.service.BlogService;
import com.liuzw.blog.utils.CopyDataUtil;
import com.liuzw.blog.vo.*;
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
    private BlogMapper blogMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public Page<BlogVO> getList(BlogQueryDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        //获取数据
        List<BlogVO> list = blogMapper.getList(dto);
        if (CollectionUtils.isEmpty(list)) {
            return new Page<>();
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
        return Page.createPageBean(list);
    }

    @Override
    public BlogDetailVO getById(Long id) {
        BlogModel blogModel = blogMapper.selectByPrimaryKey(id);

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
        LikeModel likeModel = likeMapper.selectOne(LikeModel.builder().blogId(blogId).userId(null).build());

        LikeVO likeVO;

        if (likeModel == null) {
            likeVO = LikeVO.builder()
                    .collectStatus(StatuEnum.INVALID.getCode())
                    .likeStatus(StatuEnum.INVALID.getCode())
                    .build();
        } else {
            likeVO = CopyDataUtil.copyObject(likeModel, LikeVO.class);
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
        List<LikeCountVO> list = likeMapper.getCountByBlogId(blogIdList);
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
        List<CommentCountVo> list = commentMapper.getCommentCountByBlogId(blogIdList);
        //将list转换成map<blogId, LikeCountVO>
        return list.stream().collect(Collectors.toMap(CommentCountVo::getBlogId, s -> s));
    }

}