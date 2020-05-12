package com.blog.framework.vo.classs;

import com.blog.framework.common.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
public class ClassListVo {

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long id;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;

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
     * 分类状态
     */
    @ApiModelProperty(value = "分类状态")
    private String classStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtil.DATE_TIME)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 二级分类
     */
    @ApiModelProperty(value = "二级分类")
    private List<ClassListVo> children;
}
