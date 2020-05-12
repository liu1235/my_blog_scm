package com.blog.framework.vo.blog.manage;

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
public class BlogManageDetailVO implements Serializable {

    private static final long serialVersionUID = -8148561001968218686L;

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
     * 博客内容描述
     */
    @ApiModelProperty(value = "博客内容描述")
    private String description;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private String classId;

    /**
     * 博客状态
     */
    @ApiModelProperty(value = "博客状态")
    private Integer status;

    /**
     * 原始输入数据
     */
    @ApiModelProperty(value = "原始输入数据")
    private String rawData;

}