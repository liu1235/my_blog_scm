package com.blog.framework.dto.classs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 分类新增
 *
 * @author liuzw
 * @date 2020-01-14
 **/
@Data
public class ClassAddDto {

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    @ApiModelProperty(value = "分类名称", required = true)
    private String className;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    /**
     * 父id
     */
    @NotNull(message = "父id不能为空")
    @ApiModelProperty(value = "父id")
    private Long parentId;
}
