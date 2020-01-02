package com.blog.framework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TABLE_NAME:(t_comment)
 *
 *  @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="t_comment")
public class CommentModel {


    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 博客id
     */
    @Column(name = "blog_id")
    private Long blogId;

    /**
     * 评论类型（1：博客评论 2：友情链接 3：留言板）
     */
    @Column(name = "comment_type")
    private Integer commentType;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 评论用户id
     */
    @Column(name = "user_id")
    private Long userId;

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