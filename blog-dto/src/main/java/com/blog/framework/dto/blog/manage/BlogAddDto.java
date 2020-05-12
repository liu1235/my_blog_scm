package com.blog.framework.dto.blog.manage;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liuzw
 */
@Data
public class BlogAddDto implements Serializable {


    private static final long serialVersionUID = -2294394333686600444L;

    /**
     * 博客标题
     */
    @NotBlank(message = "博客标题不能为空")
    @ApiModelProperty(value = "博客标题")
    private String title;

    /**
     * 博客内容描述
     */
    @NotBlank(message = "博客内容描述不能为空")
    @ApiModelProperty(value = "博客内容描述")
    private String description;

    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空")
    @ApiModelProperty(value = "分类id")
    private Long classId;

    /**
     * 原始输入数据
     */
    @ApiModelProperty(value = "原始输入数据")
    private String rawData;

    /**
     * 博客内容
     */
    @ApiModelProperty(value = "博客内容")
    private String content;

}