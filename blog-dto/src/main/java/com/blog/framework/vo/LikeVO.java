/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.vo;


import lombok.*;
import io.swagger.annotations.ApiModelProperty;

/**
 * TABLE_NAME:(t_like)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeVO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 点赞状态(1:有效赞；0：取消赞)
     */
    @ApiModelProperty(value = "点赞状态(1:有效赞；0：取消赞)")
    private Integer likeStatus;

    /**
     * 点赞状态(1:有效赞；0：取消赞)
     */
    @ApiModelProperty(value = "收藏状态(1:已收藏；0：为收藏)")
    private Integer collectStatus;


}