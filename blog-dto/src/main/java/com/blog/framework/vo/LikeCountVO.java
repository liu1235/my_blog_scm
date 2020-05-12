/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.blog.framework.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TABLE_NAME:(t_like)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeCountVO {

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 点赞数量
     */
    private Integer likeCount;

    /**
     * 收藏数量
     */
    private Integer collectCount;

}