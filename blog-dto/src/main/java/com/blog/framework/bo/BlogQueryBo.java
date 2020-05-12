package com.blog.framework.bo;

import com.blog.framework.common.BasePage;
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
public class BlogQueryBo extends BasePage {

    /**
     * 标题
     */
    private String title;

    /**
     * 分类id
     */
    private Long classId;

    /**
     * 分类id
     */
    List<Long> classIds;

}
