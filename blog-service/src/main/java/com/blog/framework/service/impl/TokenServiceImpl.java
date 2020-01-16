package com.blog.framework.service.impl;

import com.blog.framework.common.constants.BaseConstants;
import com.blog.framework.common.constants.RedisConstants;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.service.TokenService;
import com.blog.framework.vo.user.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzw
 **/
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${expire_time:90}")
    private Long expireTime;

    @Autowired
    private RedisService redisService;

    @Override
    public void saveUserInfo(String token, UserLoginVo userInfo) {
        redisService.set(getTokenKey(token), userInfo, expireTime, TimeUnit.MINUTES);
    }

    @Override
    public Boolean deleteUserInfo() {
        String token = getToken();
        if (StringUtils.isNotEmpty(token)) {
            redisService.delete(getTokenKey(token));
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkTokenExists(String token) {
        if (StringUtils.isNotBlank(token)) {
            return redisService.hasKey(getTokenKey(token));
        } else {
            return false;
        }
    }

    @Override
    public UserLoginVo getUserInfo() {
        return getUserInfo(getToken());
    }

    /**
     * 获取token
     */
    private String getToken() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return  "";
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(BaseConstants.ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        log.info("-------获取token:{}---------", token);
        return token;
    }

    /**
     * 获取用户信息
     *
     * @param token token
     * @return UserInfo
     */
    private UserLoginVo getUserInfo(String token) {
        String keyName = getTokenKey(token);
        if (StringUtils.isNotBlank(token) && redisService.hasKey(keyName)) {
            return redisService.get(keyName, UserLoginVo.class);
        }
        return null;
    }

    /**
     * 返回redis key
     *
     * @param token token
     * @return String
     */
    private String getTokenKey(String token) {
        return RedisConstants.REDIS_BLOG_TOKEN + token;
    }
}
