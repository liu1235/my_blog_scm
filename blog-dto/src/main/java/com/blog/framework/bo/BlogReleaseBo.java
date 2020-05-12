package com.blog.framework.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-12
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogReleaseBo {

    /**
     * 博客id
     */
    private List<Long> ids;

    /**
     * 状态
     */
    private Integer status;
}
