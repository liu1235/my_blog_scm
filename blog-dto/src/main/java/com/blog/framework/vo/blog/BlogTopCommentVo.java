package com.blog.framework.vo.blog;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogTopCommentVo {

    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Long id;

    /**
     * 博客标题
     */
    @ApiModelProperty(value = "博客标题")
    private String title;

    /**
     * 评论人名称
     */
    @ApiModelProperty(value = "评论人名称")
    private String userName;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headPhoto;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

}