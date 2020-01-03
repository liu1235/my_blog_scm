
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
    private List<T> list;

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
     * @param list List<T>
     */
    private PageBean(List<T> list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
        } else {
            this.pageNum = 1;
            this.pageSize = 10;
            this.total = (long) list.size();
        }
        this.list = list;
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
     * @param list  分页查询结果集
     * @param clazz 转换类型
     * @return PageBean
     */
    public static <V, E> PageBean<V> createPageBean(List<E> list, Class<V> clazz) {
        PageBean<V> p = new PageBean<>();
        if (list instanceof Page) {
            Page page = (Page) list;
            p.pageNum = page.getPageNum();
            p.pageSize = page.getPageSize();
            p.total = page.getTotal();
        } else {
            p.pageNum = 1;
            p.pageSize = 10;
            p.total = (long) list.size();
        }
        p.list = CopyDataUtil.copyList(list, clazz);
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
    public static <E> PageBean<E> createPageBean(Integer pageNum, Integer pageSize, Long total, List<E> list) {
        PageBean<E> page = new PageBean<>(pageNum, pageSize, total);
        page.list = list;
        return page;
    }

}
