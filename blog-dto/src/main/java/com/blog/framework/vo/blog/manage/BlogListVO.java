package com.blog.framework.vo.blog.manage;

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
public class BlogListVO implements Serializable {

    private static final long serialVersionUID = -5710684877024649444L;

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
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String className;

    /**
     * 发布日期
     */
    @JsonFormat(pattern = DateUtil.DATE)
    @ApiModelProperty(value = "发布日期")
    private Date releaseTime;

    /**
     * 阅读次数
     */
    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;

}