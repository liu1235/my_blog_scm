package com.liuzw.blog.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页基础类
 *
 * @author liuzw
 * @date 2018/5/28 14:22
 **/
@Setter
public class BasePage implements Serializable {

    private static final long serialVersionUID = 2718484963360838390L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNum;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数", required = true)
    private Integer pageSize;


    public Integer getPageNum() {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        return pageNum;
    }


    public Integer getPageSize() {
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        return pageSize;
    }

}

