package com.blog.framework.dto.like;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * my_blog_scm
 *
 * @author liuzw
 * @date 2019-05-07
 **/
@Data
public class LikeDto {


    /**
     * 喜欢或者收藏的状态（1：有效赞或喜欢; 0 无效赞或喜欢）
     */
    @NotNull(message = "喜欢或者收藏的状态不能为空")
    @ApiModelProperty(value = "喜欢或者收藏的状态（1：有效赞或喜欢; 0 无效赞或喜欢）")
    private Integer status;

    /**
     * 博客id
     */
    @NotNull(message = "博客id不能为空")
    @ApiModelProperty(value = "博客id")
    private Long blogId;

}
