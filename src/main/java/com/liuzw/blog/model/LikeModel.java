/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * TABLE_NAME:(t_like)
 *
 *  @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="t_like")
public class LikeModel {


    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 博客id
     */
    @Column(name = "blog_id")
    private Long blogId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 点赞状态(1:有效赞；0：取消赞)
     */
    @Column(name = "like_status")
    private Integer likeStatus;

    /**
     * 收藏状态(1:已收藏；0：未收藏)
     */
    @Column(name = "collect_status")
    private Integer collectStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;
	

}