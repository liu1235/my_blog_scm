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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String statusName;

    /**
     * 发布日期
     */
    @JsonFormat(pattern = DateUtil.DATE_TIME)
    @ApiModelProperty(value = "发布日期")
    private Date releaseTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtil.DATE_TIME)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 阅读次数
     */
    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;

}