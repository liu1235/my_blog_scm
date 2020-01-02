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
 * TABLE_NAME:(t_blog)
 *
 *  @liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="t_blog")
public class BlogModel {


    /**
     * 博客id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 博客标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 博客内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 原始输入数据
     */
    @Column(name = "raw_data")
    private String rawData;

    /**
     * 博客类别
     */
    @Column(name = "class_id")
    private Long classId;

    /**
     * 是否发布(1:是, 0:否)
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 发布日期
     */
    @Column(name = "create_date")
    private String createDate;

    /**
     * 阅读次数
     */
    @Column(name = "read_count")
    private Integer readCount;

}