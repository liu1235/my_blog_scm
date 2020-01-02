package com.blog.framework.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象复制
 *
 * @author liuzw
 */
@Slf4j
public class CopyDataUtil {

    private CopyDataUtil(){}


    public static <T, V> V copyObject(T source, Class<V> clazz) {
        if (source == null) {
            return null;
        }
        V newObj = null;
        try {
            newObj = clazz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, newObj);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("错误", e);
        }
        return newObj;
    }

    public static <T, V> List<V> copyList(List<T> list, Class<V> clazz) {
        List<V> data = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            try {
                for (T obj : list) {
                    V dto = clazz.newInstance();
                    org.springframework.beans.BeanUtils.copyProperties(obj, dto);
                    data.add(dto);
                }
            } catch (IllegalAccessException | InstantiationException e) {
                log.error("", e);
            }
            return data;
        } else {
            return new ArrayList<>();
        }
    }

}
