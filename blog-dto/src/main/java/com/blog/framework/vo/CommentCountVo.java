/**
 * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved.
 */

package com.blog.framework.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TABLE_NAME:(t_comment)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentCountVo {


    /**
     * 博客Id
     */
    private Long blogId;

    /**
     * 评论数
     */
    private Integer commentCount;


}