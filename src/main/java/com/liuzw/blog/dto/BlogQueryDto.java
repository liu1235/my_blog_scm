package com.liuzw.blog.dto;

import com.liuzw.blog.common.BasePage;
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

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Integer classId;

}
