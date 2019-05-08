package com.liuzw.blog.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * TABLE_NAME:(t_comment)
 *
 * @author liuzw
 */

@Data
public class CommentDto implements Serializable {

    /**
     * 博客id
     */
    @NotNull(message = "博客id不能为空")
    @ApiModelProperty(value = "博客id", required = true)
    private Long blogId;

    /**
     * 评论类型（1：博客评论 2：友情链接 3：留言板）
     */
    @NotNull(message = "评论类型不能为空")
    @ApiModelProperty(value = "评论类型（1：博客评论 2：友情链接 3：留言板）", required = true)
    private Integer commentType;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    @ApiModelProperty(value = "评论内容", required = true)
    private String content;

    /**
     * 评论用户id
     */
    @ApiModelProperty(value = "评论用户id", hidden = true)
    private Long userId;

}