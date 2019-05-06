
package com.liuzw.blog.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 封装分页信息
 *
 * @author liuzw
 */
@Data
public class Page<T> {

    /**
     * 结果集
     */
    @ApiModelProperty(value = "结果集")
    private List<T> data;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer pageSize;

    /**
     * 总页数
     */
    private Long total;

    public Page() {

    }

    /**
     * 包装Page对象
     *
     * @param data List<T>
     */
    private Page(List<T> data) {
        if (data instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page page = (com.github.pagehelper.Page) data;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
        } else {
            this.pageNum = 1;
            this.pageSize = 10;
            this.total = (long) data.size();
        }
        this.data = data;
    }

    /**
     * 包装Page对象
     *
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @param total    总页数
     */
    private Page(Integer pageNum, Integer pageSize, Long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }


    /**
     * 创建 Page
     *
     * @param list 分页查询结果集
     * @return PageBean
     */
    public static <E> Page<E> createPageBean(List<E> list) {
        return new Page<>(list);
    }

    /**
     * 创建 Page
     *
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @param total    总页数
     * @return Page
     */
    public static <E> Page<E> createPageBean(Integer pageNum, Integer pageSize, Long total) {
        return new Page<>(pageNum, pageSize, total);
    }

}
