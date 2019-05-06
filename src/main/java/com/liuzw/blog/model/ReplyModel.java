/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * TABLE_NAME:(t_reply)
 *
 *  @liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="t_reply")
public class ReplyModel {


    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 评论id
     */
    @Column(name = "comment_id")
    private Long commentId;

    /**
     * 回复类型(1:评论的回复，2: 回复的回复)
     */
    @Column(name = "reply_type")
    private Integer replyType;

    /**
     * 回复内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 回复目标id
     */
    @Column(name = "reply_id")
    private Long replyId;

    /**
     * 回复用户id
     */
    @Column(name = "from_user_id")
    private Long fromUserId;

    /**
     * 目标用户id
     */
    @Column(name = "to_user_id")
    private Long toUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;
	

}