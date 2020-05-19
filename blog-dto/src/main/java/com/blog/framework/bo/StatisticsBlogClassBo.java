package com.blog.framework.bo;

import com.blog.framework.common.KeyValueBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-19
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsBlogClassBo {

    /**
     * 博客id
     */
    @ApiModelProperty("博客id")
    private Long blogId;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String className;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Long classId;

    /**
     * 分类父id
     */
    @ApiModelProperty("分类父id")
    private Long parentId;
}
