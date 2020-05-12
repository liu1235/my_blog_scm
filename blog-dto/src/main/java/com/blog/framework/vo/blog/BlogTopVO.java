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
public class BlogTopVO {

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
     * 阅读次数
     */
    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;

}