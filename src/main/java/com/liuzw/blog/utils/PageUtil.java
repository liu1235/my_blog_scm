package com.liuzw.blog.utils;

import com.liuzw.blog.common.Page;

import java.util.List;

/**
 * 分页工具类
 * @author liuzw
 * @date 2018/6/25 14:16
 **/
public class PageUtil {

    private PageUtil() {

    }

    public static <T, V> Page<V> getPageList(List<T> list, Class<V> clazz) {
        if (list == null) {
            return null;
        } else {
            Page<V> result = new Page<>();
            if (list instanceof com.github.pagehelper.Page) {
                com.github.pagehelper.Page page = (com.github.pagehelper.Page) list;
                result.setPageNum(page.getPageNum());
                result.setPageSize(page.getPageSize());
                result.setTotal(page.getTotal());
                result.setData(CopyDataUtil.copyList(list, clazz));
            } else {
                result.setPageNum(1);
                result.setPageSize(list.size());
                result.setTotal((long) list.size());
            }
            return result;
        }
    }

}
