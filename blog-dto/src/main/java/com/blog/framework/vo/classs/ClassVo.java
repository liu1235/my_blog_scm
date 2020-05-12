package com.blog.framework.vo.classs;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分类
 *
 * @author liuzw
 * @date 2020-01-14
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassVo {

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long classId;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String className;

    /**
     * 二级分类
     */
    @ApiModelProperty(value = "二级分类")
    private List<ClassVo> child;
}
