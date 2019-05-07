/**
 * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved.
 */

package com.liuzw.blog.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * TABLE_NAME:(t_comment)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo implements Serializable {


    private static final long serialVersionUID = 5161646451164295560L;
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

    /**
     * 评论用户id
     */
    @ApiModelProperty(value = "评论用户id")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headPhoto;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "评论时间")
    private Date createTime;


    private List<CommentVo> child;

}