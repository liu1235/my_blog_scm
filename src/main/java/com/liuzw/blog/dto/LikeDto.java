package com.liuzw.blog.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * TABLE_NAME:(t_like)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {


    /**
     * 博客id
     */
    @NotNull(message = "博客id不能为空")
    @ApiModelProperty(value = "博客id", required = true)
    private Long blogId;



    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

}