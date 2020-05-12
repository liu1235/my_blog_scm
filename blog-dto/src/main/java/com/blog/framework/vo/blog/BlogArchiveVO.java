package com.blog.framework.vo.blog;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzw
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogArchiveVO {

    /**
     * 博客id
     */
    @ApiModelProperty(value = "博客id")
    private Long id;

    /**
     * 博客标题
     */
    @ApiModelProperty(value = "博客标题")
    private String title;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private String releaseTime;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

}