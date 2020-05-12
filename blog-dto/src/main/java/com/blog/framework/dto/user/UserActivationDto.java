package com.blog.framework.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 激活
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Data
public class UserActivationDto implements Serializable {


    private static final long serialVersionUID = -6530758955743976886L;

    /**
     * 激活参数
     */
    @NotBlank(message = "激活参数不能为空")
    @ApiModelProperty(value = "激活参数", required = true)
    private String param;

}
