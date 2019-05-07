/**
 * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved.
 */

package com.liuzw.blog.vo;


import lombok.*;

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