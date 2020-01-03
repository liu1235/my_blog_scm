package com.blog.framework.service.impl;


import com.blog.framework.common.PageBean;
import com.blog.framework.common.constants.RedisConstants;
import com.blog.framework.common.enums.StatusEnum;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.common.exception.ServiceException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.common.utils.JsonUtil;
import com.blog.framework.dao.CommentDao;
import com.blog.framework.dao.LikeDao;
import com.blog.framework.dto.blog.BlogQueryDto;
import com.blog.framework.model.BlogModel;
import com.blog.framework.model.LikeModel;
import com.blog.framework.service.BlogDao;
import com.blog.framework.service.BlogService;
import com.blog.framework.service.TokenService;
import com.blog.framework.vo.CommentCountVo;
import com.blog.framework.vo.LikeCountVO;
import com.blog.framework.vo.LikeVO;
import com.blog.framework.vo.blog.BlogDetailVO;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
import com.blog.framework.vo.user.UserLoginVo;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;


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
            } else {
                vo.setCollectCount(0);
                vo.setLikeCount(0);
            }
            //组装评论数
            CommentCountVo commentCountVo = commentCountMap.get(vo.getId());
            if (commentCountVo != null) {
                vo.setCommentCount(commentCountVo.getCommentCount());
            } else {
                vo.setCommentCount(0);
            }
        }
        return PageBean.createPageBean(list);
    }

    @Override
    public List<BlogTopVO> topBlogList() {
        //首先从redis中获取数据
        String json = redisService.get(RedisConstants.REDIS_BLOG_LIST);
        if (StringUtils.isNotBlank(json)) {
            return JsonUtil.toList(json, BlogTopVO.class);
        }
        //从数据库获取
        List<BlogTopVO> vos = blogDao.topBlogList();
        if (CollectionUtils.isNotEmpty(vos)) {
            //存到redis中
            redisService.set(RedisConstants.REDIS_BLOG_LIST, JsonUtil.toJson(vos), 12, TimeUnit.HOURS);
            return vos;
        }
        return null;
    }

    @Override
    public PageBean<BlogVO> likeBlogList() {
        //获取当前登录信息
        UserLoginVo userInfo = getUserInfo();
        String key = RedisConstants.REDIS_BLOG_LIKE + userInfo.getUserId();
        Boolean flag = redisService.hasKey(key);
        if (flag) {
            throw new ServiceException("亲, 你操作的太频繁了 (´⊙ω⊙`)！");
        } else {
            redisService.set(key, "", 5, TimeUnit.SECONDS);
        }
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
        // 用户id获取
        UserLoginVo userInfo = tokenService.getUserInfo();
        if (userInfo != null && userInfo.getUserId() != null) {
            LikeModel likeModel = likeDao.getLikeByUserIdAndBlogId(blogId, userInfo.getUserId());
            if (likeModel != null) {
                likeVO = CopyDataUtil.copyObject(likeModel, LikeVO.class);
            }
        }

        //todo 更新博客阅读次数  这里需要优化 每次点击都对数据库操作  可以先存到redis 然后定时任务定时去更新
        blogDao.update(BlogModel.builder()
                .id(blogModel.getId())
                .readCount(blogModel.getReadCount() + 1)
                .build());

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


    /**
     * 获取博客列表
     */
    private PageBean<BlogVO> getBlogList(LikeModel model) {
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

    /**
     * 获取当前登录信息
     */
    private UserLoginVo getUserInfo() {
        // 获取当前登录人id
        UserLoginVo userInfo = tokenService.getUserInfo();
        if (userInfo == null || userInfo.getUserId() == null) {
            throw new LoginException();
        }
        return userInfo;
    }

}