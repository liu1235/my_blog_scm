package com.blog.framework.dto.classs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 分类新增
 *
 * @author liuzw
 * @date 2020-01-14
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassUpdateDto extends ClassAddDto {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

}
