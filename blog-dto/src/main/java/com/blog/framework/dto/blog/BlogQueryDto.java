package com.blog.framework.dto.blog;

import com.blog.framework.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
    private Long classId;

    /**
     * 博客id
     */
    @ApiModelProperty(hidden = true)
    private List<Long> blogIds;

}
