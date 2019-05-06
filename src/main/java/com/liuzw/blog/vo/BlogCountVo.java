package com.liuzw.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-05-06
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogCountVo implements Serializable {

    /**
     * 阅读次数
     */
    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;

    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;

    /**
     * 收藏数
     */
    @ApiModelProperty(value = "收藏数")
    private Integer collectCount;

    /**
     * 评论数
     */
    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

}
