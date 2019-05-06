package com.liuzw.blog.service;

import com.liuzw.blog.dto.MailDto;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public interface MailService {

    /**
     * 发送普通文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容（可以包含<html>等标签）
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param mailDto 模板参数
     */
    void sendTemplateMail(String to, MailDto mailDto);

}
