package com.chen.xbshop.utlis;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.Utils
 * @Author: ChenZengWen
 * @Description: 邮件工具类
 * @Date: 2020/9/27 10:49
 * @Version: 1.0
 */
public class EmailUtil {
    /**
     * @return void
     * @decription
     * @author admin
     * @date 2019/12/3 11:55
     * @params [emailName 收件人邮箱地址, code 验证码]
     */
    public static void sendEmail(String emailName, String code) {
        //第一步：设置发件人邮箱地址，第二步：开启smtp服务
        try {
            //设置发件人(服务器)
            String from = "1476389673@qq.com";
            //设置收件人
            String to = emailName;
            //设置邮件发送的服务器，这里为QQ邮件服务器
            String host = "smtp.qq.com";
            //获取系统属性
            Properties properties = System.getProperties();
            //SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //设置系统属性
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");

            //获取发送邮件会话、获取第三方登录授权码
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //第三方登录授权码
                    return new PasswordAuthentication(from, "tmqgclhgssqciicd");
                }
            });
            Message message = new MimeMessage(session);
            //防止邮件被当然垃圾邮件处理，披上Outlook的马甲
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //邮件标题
            message.setSubject("小标商城忘记密码验证码");
            BodyPart bodyPart = new MimeBodyPart();
            //消息体
            bodyPart.setText("修改密码验证码：" + String.valueOf(code));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
            //发送邮件
            Transport.send(message);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
