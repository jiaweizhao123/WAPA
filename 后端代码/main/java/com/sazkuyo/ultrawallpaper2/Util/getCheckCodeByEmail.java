package com.sazkuyo.ultrawallpaper2.Util;

import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class getCheckCodeByEmail {
    private final static String emailAddress = "3529133972@qq.com";
    private static final String token = "smhlviolxpoodbhe";


    public static String getCheckCode() {
        String checkCode = "";
        int code_item;
        for (int i = 0; i < 6; i++) {
            code_item = (int) (Math.random() * 10);
            checkCode += code_item;
        }
        return checkCode;
    }


    public static String sendCheckCode(String email) throws MessagingException, GeneralSecurityException {
        String checkCode = getCheckCode();
        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host", "smtp.qq.com");

        properties.setProperty("mail.transport.protocol", "smtp");

        properties.setProperty("mail.smtp.auth", "true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAddress, token);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com", emailAddress, token);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(emailAddress));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

        //邮件标题
        mimeMessage.setSubject("邮件验证码");

        //邮件内容
        mimeMessage.setText("您的验证码是:" + checkCode + "，注意该验证码不要告知他人，否则会影响您的账号安全");

        //发送邮件
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
        return checkCode;
    }
}
