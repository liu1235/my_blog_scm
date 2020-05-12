package com.blog.framework.vo.blog;

import com.blog.framework.common.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuzw
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogVO implements Serializable {

    private static final long serialVersionUID = -7359643838651578359L;

    /**
     * 博客id
     */
    @ApiModelProperty(value = "博客id")
    private Long id;

    /**
     * 博客标题
     */
    @ApiModelProperty(value = "博客标题")
    private String title;

    /**
     * 博客内容描述
     */
    @ApiModelProperty(value = "博客内容描述")
    private String description;

    /**
     * 博客类别id
     */
    @ApiModelProperty(value = "博客类别id")
    private Long classId;

    /**
     * 博客类别
     */
    @ApiModelProperty(value = "博客类别")
    private String className;

    /**
     * 博客内容
     */
    @ApiModelProperty(value = "博客内容")
    private String content;

    /**
     * 发布日期
     */
    @JsonFormat(pattern = DateUtil.DATE_TIME)
    @ApiModelProperty(value = "发布日期")
    private Date releaseTime;

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