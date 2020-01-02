package com.blog.framework.service.impl;

import com.blog.framework.dto.MailDto;
import com.blog.framework.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件
 *
 * @author liuzw
 * @date 2019-04-29
 **/
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;


    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        log.info("---------------->发送邮件开始：{},{},{}", to, subject, content);
        SimpleMailMessage message = new SimpleMailMessage();
        //收信人
        message.setTo(to);
        //主题
        message.setSubject(subject);
        //内容
        message.setText(content);
        //发信人
        message.setFrom(from);
        mailSender.send(message);
        log.info("-------------->发送HTML邮件成功");
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        //使用MimeMessage，MIME协议
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            //true代表支持html
            helper.setText(content, true);
            mailSender.send(message);
            log.info("-------------->发送HTML邮件成功");
        } catch (MessagingException e) {
            log.error("-------------->发送HTML邮件失败：", e);
        }
    }

    @Async
    @Override
    public void sendTemplateMail(String to, MailDto mailDto) {
        log.info("---------------->发送模板邮件开始：{},{}", to, mailDto);
        //向Thymeleaf模板传值，并解析成字符串
        Context context = new Context();
        context.setVariable("userName", mailDto.getUserName());
        context.setVariable("url", mailDto.getUrl());
        String content = templateEngine.process("mail", context);
        this.sendHtmlMail(to, "请激活你的邮箱", content);

    }

}
