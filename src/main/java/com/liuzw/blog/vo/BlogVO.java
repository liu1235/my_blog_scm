package com.liuzw.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuzw
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogVO implements Serializable {

    private static final long serialVersionUID = -7359643838651578359L;

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
     * 博客内容
     */
    @ApiModelProperty(value = "博客内容")
    private String content;

    /**
     * 博客类别id
     */
    @ApiModelProperty(value = "博客类别id")
    private Long tagId;

    /**
     * 博客类别
     */
    @ApiModelProperty(value = "博客类别")
    private String tagName;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    private String createDate;

    /**
     * 阅读次数
     */
    @ApiModelProperty(value = "阅读次数", hidden = true)
    private Integer readCount;

}