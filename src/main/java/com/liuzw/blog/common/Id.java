package com.liuzw.blog.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Getter
@Setter
public class Id<T> implements Serializable {

    private static final long serialVersionUID = 1516349955865002667L;

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id", required = true)
    private T id;
}
