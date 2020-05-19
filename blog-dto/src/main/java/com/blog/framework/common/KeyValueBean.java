package com.blog.framework.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class KeyValueBean<K,V> {
    /**
     * key
     */
    @ApiModelProperty("key")
    private K key;

    /**
     * value
     */
    @ApiModelProperty("value")
    private V value;
}
