package com.white.blog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author white_
 * @version 1.0
 * @project MyWeb-system
 * @description 邮件发送工具类
 * @date 2024/1/7 15:48:00
 */
@Component
public class EmailSend {
    //注入邮件发送服务类
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String myEmail;

    /**
     * @param toEmail 收件人
     * @param subject 标题
     * @param content 邮件正文
     * @return
     */
    public boolean send(String toEmail, String subject, String content) throws Exception {
        // 创建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 定义收件人邮箱
        message.setFrom(myEmail);
        // 定义发件人邮箱
        message.setTo(toEmail);
        // 定义邮件标题
        message.setSubject(subject);
        // 定义邮件内容
        message.setText(content);

        javaMailSender.send(message);

        return true;
    }
}
