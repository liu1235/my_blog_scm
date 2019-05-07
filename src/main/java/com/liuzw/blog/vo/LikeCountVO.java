/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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