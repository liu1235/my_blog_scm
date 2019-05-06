package com.liuzw.blog.vo;


import lombok.*;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * TABLE_NAME:(t_blog)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDetailVO implements Serializable {


    private static final long serialVersionUID = -1584251621561823206L;

    /**
     * 博客内容
     */
    @ApiModelProperty(value = "博客内容")
    private BlogVO blog;

    /**
     * 评论
     */
    @ApiModelProperty(value = "评论")
    private CommentVo comment;

    /**
     * 当前用户的点赞收藏
     */
    @ApiModelProperty(value = "当前用户的点赞收藏")
    private LikeVO like;

    /**
     * blog统计
     */
    @ApiModelProperty(value = "blog统计")
    private BlogCountVo blogCount;

}