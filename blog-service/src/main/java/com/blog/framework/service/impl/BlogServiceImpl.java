package com.blog.framework.service.impl;


import com.blog.framework.bo.BlogLikeOrCollectBo;
import com.blog.framework.bo.BlogQueryBo;
import com.blog.framework.common.PageBean;
import com.blog.framework.common.constants.RedisConstants;
import com.blog.framework.common.enums.StatusEnum;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.common.utils.DateUtil;
import com.blog.framework.common.utils.JsonUtil;
import com.blog.framework.dao.BlogDao;
import com.blog.framework.dao.CommentDao;
import com.blog.framework.dao.LikeDao;
import com.blog.framework.dao.UserDao;
import com.blog.framework.model.BlogModel;
import com.blog.framework.model.CommentModel;
import com.blog.framework.model.LikeModel;
import com.blog.framework.model.UserModel;
import com.blog.framework.service.BlogService;
import com.blog.framework.service.TokenService;
import com.blog.framework.vo.LikeCountVO;
import com.blog.framework.vo.LikeVO;
import com.blog.framework.vo.blog.BlogArchiveVO;
import com.blog.framework.vo.blog.BlogDetailVO;
import com.blog.framework.vo.blog.BlogTopCommentVo;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
import com.blog.framework.vo.comment.CommentCountVo;
import com.blog.framework.vo.user.UserLoginVo;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    @Autowired
    private UserDao userDao;


    @Override
    public PageBean<BlogVO> list(BlogQueryBo bo) {
        PageHelper.startPage(bo.getPageNum(), bo.getPageSize());
        //获取数据
        List<BlogVO> list = blogDao.list(bo);
        if (CollectionUtils.isEmpty(list)) {
            return new PageBean<>();
        }
        handleBlogList(list);
        return PageBean.createPageBean(list);
    }

    @Override
    public List<BlogTopVO> topBlogList() {
        //首先从redis中获取数据
        String json = redisService.get(RedisConstants.REDIS_BLOG_TOP);
        if (StringUtils.isNotBlank(json)) {
            return JsonUtil.toList(json, BlogTopVO.class);
        }
        //从数据库获取
        List<BlogTopVO> vos = blogDao.topBlogList();
        if (CollectionUtils.isNotEmpty(vos)) {
            //存到redis中
            redisService.set(RedisConstants.REDIS_BLOG_TOP, vos, 12, TimeUnit.HOURS);
            return vos;
        }
        return null;
    }

    @Override
    public List<BlogArchiveVO> archive() {
        List<BlogArchiveVO> voList = new ArrayList<>();
        //先从redis中获取数据
        Map<String, String> map = redisService.getHash(RedisConstants.REDIS_BLOG_ARCHIVE);
        //如果redis没有 则从数据库获取数据
        if (MapUtils.isEmpty(map)) {
            List<BlogArchiveVO> list = blogDao.archive();
            if (CollectionUtils.isEmpty(list)) {
                return Collections.emptyList();
            }
            list.forEach(v -> v.setCreateDate(DateUtil.convertStringDate(DateUtil.DATE_TIME, v.getCreateDate())));
            Map<String, List<BlogArchiveVO>> listMap = list.stream()
                    .collect(Collectors.groupingBy(v -> v.getCreateDate().substring(0, 4)));
            //存到redis
            redisService.setHash(RedisConstants.REDIS_BLOG_ARCHIVE, listMap, 15, TimeUnit.DAYS);

            List<Map.Entry<String, List<BlogArchiveVO>>> entries = listMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, List<BlogArchiveVO>>comparingByKey().reversed())
                    .collect(Collectors.toList());
            for (Map.Entry<String, List<BlogArchiveVO>> entry : entries) {
                voList.add(BlogArchiveVO.builder().createDate(entry.getKey()).build());
                voList.addAll(entry.getValue());
            }
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                voList.add(BlogArchiveVO.builder().createDate(entry.getKey()).build());
                voList.addAll(JsonUtil.toList(entry.getValue(), BlogArchiveVO.class));
            }
        }
        return voList;
    }

    @Override
    public List<BlogTopCommentVo> topBlogCommentList() {
        //获取最新的10条评论
        List<CommentModel> commentList = commentDao.topCommentList();
        if (CollectionUtils.isEmpty(commentList)) {
            return Collections.emptyList();
        }
        //获取对应的博客id
        List<Long> blogIds = commentList.stream()
                .map(CommentModel::getBlogId)
                .distinct()
                .collect(Collectors.toList());

        //获取评论人id
        //获取对应的博客id
        List<Long> userIds = commentList.stream()
                .map(CommentModel::getUserId)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, UserModel> map = getUserInfo(userIds);
        //获取对应的博客信息
        List<BlogModel> blogList = blogDao.getByIds(blogIds);

        Map<Long, BlogModel> blogModelMap = blogList.stream().collect(Collectors.toMap(BlogModel::getId, v -> v));

        List<BlogTopCommentVo> list = new ArrayList<>(commentList.size());
        for (CommentModel model : commentList) {
            BlogModel blogModel = blogModelMap.get(model.getBlogId());
            if (blogModel != null) {
                BlogTopCommentVo vo = BlogTopCommentVo.builder()
                        .content(model.getContent())
                        .id(blogModel.getId())
                        .title(blogModel.getTitle())
                        .build();
                UserModel userModel = map.get(model.getUserId());
                if (userModel != null) {
                    vo.setUserName(userModel.getUserName());
                    vo.setHeadPhoto(userModel.getHeadPhoto());
                } else {
                    vo.setUserName("游客");
                }
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public PageBean<BlogVO> likeBlogList(BlogLikeOrCollectBo bo) {
        bo.setLikeStatus(StatusEnum.EFFECTIVE.getCode());
        return getLikeOrCollectBlogList(bo);
    }

    @Override
    public PageBean<BlogVO> collectBlogList(BlogLikeOrCollectBo bo) {
        bo.setCollectStatus(StatusEnum.EFFECTIVE.getCode());
        return getLikeOrCollectBlogList(bo);
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

        //todo 更新博客阅读次数  这里需要优化 每次点击都对数据库操作  可以先存到redis 然后定时任务定时去更新 因为此数据不需要很高准确性

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
    private PageBean<BlogVO> getLikeOrCollectBlogList(BlogLikeOrCollectBo bo) {
        PageHelper.startPage(bo.getPageNum(), bo.getPageSize());
        UserLoginVo userInfo = getUserInfo();
        bo.setUserId(userInfo.getUserId());
        List<BlogVO> list = blogDao.getLikeOrCollectBlogList(bo);
        if (CollectionUtils.isEmpty(list)) {
            return new PageBean<>();
        }
        handleBlogList(list);
        return PageBean.createPageBean(list);
    }


    /**
     * 处理博客列表数据
     */
    private void handleBlogList(List<BlogVO> list) {
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

    /**
     * 获取所有用户信息
     *
     * @return Map<userId, UserModel>
     */
    private Map<Long, UserModel> getUserInfo(List<Long> userIds) {
        //获取所有用户信息
        List<UserModel> list = userDao.selectByIds(userIds);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.toMap(UserModel::getId, u -> u));
    }

}