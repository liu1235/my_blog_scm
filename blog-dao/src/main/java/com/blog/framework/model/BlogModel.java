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
import java.util.Date;

/**
 * TABLE_NAME:(t_blog)
 *
 *  @author liuzw
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
     * 描述
     */
    @Column(name = "description")
    private String description;


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
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 阅读次数
     */
    @Column(name = "read_count")
    private Integer readCount;

}