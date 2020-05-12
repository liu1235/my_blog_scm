package com.blog.framework.dto.blog.manage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liuzw
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogUpdateDto extends BlogAddDto implements Serializable {

    private static final long serialVersionUID = 836432607648215446L;


    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Long id;

}