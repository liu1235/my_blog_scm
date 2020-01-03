package com.blog.framework.service.impl;

import com.blog.framework.common.exception.LoginException;
import com.blog.framework.dao.LikeDao;
import com.blog.framework.dto.like.LikeDto;
import com.blog.framework.model.LikeModel;
import com.blog.framework.service.LikeService;
import com.blog.framework.service.TokenService;
import com.blog.framework.vo.user.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * my_blog_scm
 *
 * @author liuzw
 * @date 2020-01-02
 **/
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private TokenService tokenService;

    @Override
    public Boolean like(LikeDto dto) {
        UserLoginVo userInfo = tokenService.getUserInfo();
        if (userInfo == null ||  userInfo.getUserId() == null) {
            throw new LoginException();
        }
        Long userId = userInfo.getUserId();
        return likeDao.update(LikeModel.builder()
                .likeStatus(dto.getStatus())
                .userId(userId)
                .blogId(dto.getBlogId())
                .build());
    }

    @Override
    public Boolean collect(LikeDto dto) {
        // 获取用户id
        UserLoginVo userInfo = tokenService.getUserInfo();
        if (userInfo == null ||  userInfo.getUserId() == null) {
            throw new LoginException();
        }
        Long userId = userInfo.getUserId();
        return likeDao.update(LikeModel.builder()
                .collectStatus(dto.getStatus())
                .userId(userId)
                .blogId(dto.getBlogId())
                .build());
    }
}
