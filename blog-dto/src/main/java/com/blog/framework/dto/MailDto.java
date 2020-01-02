package com.blog.framework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发送邮件模板参数
 *
 * @author liuzw
 * @date 2019-04-30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 跳转地址
     */
    private String url;

}
