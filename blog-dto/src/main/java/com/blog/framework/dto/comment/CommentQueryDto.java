package com.blog.framework.dto.comment;

import com.blog.framework.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * my_blog_scm
 *
 * @author liuzw
 * @date 2019-05-07
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentQueryDto extends BasePage {

    /**
     * 评论类型
     */
    @NotNull(message = "评论类型不能为空")
    @ApiModelProperty(value = "评论类型")
    private Integer type;

    /**
     * 博客id
     */
    @ApiModelProperty(value = "博客id")
    private Long blogId;

}
