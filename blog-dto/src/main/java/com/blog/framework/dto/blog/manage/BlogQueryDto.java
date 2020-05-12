package com.blog.framework.dto.blog.manage;

import com.blog.framework.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * my_blog_scm
 *
 * @author liuzw
 * @date 2019-05-07
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogQueryDto extends BasePage {

    private static final long serialVersionUID = -3546731565744438096L;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long classId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}
