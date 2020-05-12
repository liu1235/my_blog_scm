package com.blog.framework.dto.comment;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author liuzw
 */

@Data
public class ReplyDto {

    /**
     * 博客id
     */
    @NotNull(message = "博客id不能为空")
    @ApiModelProperty(value = "博客id", required = true)
    private Long commentId;

    /**
     * 回复目标id
     */
    @NotNull(message = "回复目标id不能为空")
    @ApiModelProperty(value = "回复目标id", required = true)
    private Long replyId;

    /**
     * 评论类型（1：博客评论 2：友情链接 3：留言板）
     */
    @NotNull(message = "评论类型不能为空")
    @ApiModelProperty(value = "评论类型（1：博客评论 2：友情链接 3：留言板）", required = true)
    private Integer replyType;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    @ApiModelProperty(value = "评论内容", required = true)
    private String content;

    /**
     * 评论目标用户id
     */
    @NotNull(message = "评论目标用户id不能为空")
    @ApiModelProperty(value = "评论目标用户id", required = true)
    private Long toUserId;

}