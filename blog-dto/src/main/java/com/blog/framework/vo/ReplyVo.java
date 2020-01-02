package com.blog.framework.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ReplyVo implements Serializable {


    private static final long serialVersionUID = 5161646451164295560L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 回复目标id
     */
    private Long replyId;

    /**
     * 回复类型(1:评论的回复，2: 回复的回复)
     */
    private Integer replyType;

    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Long commentId;

    /**
     * 回复内容
     */
    @ApiModelProperty(value = "回复内容")
    private String content;

    /**
     * 回复用户id
     */
    @ApiModelProperty(value = "回复用户id")
    private Long fromUserId;

    /**
     * 回复用户名
     */
    @ApiModelProperty(value = "回复用户名")
    private String fromUserName;

    /**
     * 回复用户头像
     */
    @ApiModelProperty(value = "回复用户头像")
    private String fromHeadPhoto;

    /**
     * 目标用户id
     */
    @ApiModelProperty(value = "目标用户id")
    private Long toUserId;

    /**
     * 目标用户名
     */
    @ApiModelProperty(value = "目标用户名")
    private String toUserName;

    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间")
    private Date createTime;

    /**
     * 子类
     */
    private List<ReplyVo> child;

}