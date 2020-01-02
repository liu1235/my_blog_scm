
package com.blog.framework.common;

import com.blog.framework.common.utils.CopyDataUtil;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 封装分页信息
 *
 * @author liuzw
 */
@Data
public class PageBean<T> {

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

    public PageBean() {

    }

    /**
     * 包装Page对象
     *
     * @param data List<T>
     */
    private PageBean(List<T> data) {
        if (data instanceof Page) {
            Page page = (Page) data;
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
    private PageBean(Integer pageNum, Integer pageSize, Long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }


    /**
     * 创建 PageBean
     *
     * @param list 分页查询结果集
     * @return PageBean
     */
    public static <E> PageBean<E> createPageBean(List<E> list) {
        return new PageBean<>(list);
    }


    /**
     * 创建 PageBean
     *
     * @param data  分页查询结果集
     * @param clazz 转换类型
     * @return PageBean
     */
    public static <V, E> PageBean<V> createPageBean(List<E> data, Class<V> clazz) {
        PageBean<V> p = new PageBean<>();
        if (data instanceof Page) {
            Page page = (Page) data;
            p.pageNum = page.getPageNum();
            p.pageSize = page.getPageSize();
            p.total = page.getTotal();
        } else {
            p.pageNum = 1;
            p.pageSize = 10;
            p.total = (long) data.size();
        }
        p.data = CopyDataUtil.copyList(data, clazz);
        return p;
    }


    /**
     * 创建 PageBean
     *
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @param total    总页数
     * @return PageBean
     */
    public static <E> PageBean<E> createPageBean(Integer pageNum, Integer pageSize, Long total, List<E> data) {
        PageBean<E> page = new PageBean<>(pageNum, pageSize, total);
        page.data = data;
        return page;
    }

}
