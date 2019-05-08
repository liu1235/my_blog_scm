/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.service;


import com.liuzw.blog.model.LikeModel;

import java.util.List;


/**
 * interface Like
 *
 * @author liuzw
 */
public interface LikeService {


    /**
     *增加
     *
     * @param likeBean   数据
     * @return      Boolean
     */
    Boolean insert(LikeBean likeBean);

    /**
     *更新
     *
     * @param likeBean  数据
     * @return     Boolean
     */
    Boolean update(LikeBean likeBean);
	
}