package cn.tekin.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * java邮件发送类
 * @author Tekin
 * @url http://yunnan.ws
 */
public class Mail {

    private static Properties pconf=null;
    private static InputStream ist=null;
    //smtp user config
    private static String smtp_host;
    private static String smtp_user;
    private static String smtp_pass;
    private static Integer smtp_port;
    private static String smtp_auth;
    private static String smtp_ssl_enable;

    //构造函数
    public Mail(){
        pconf=new Properties();
        //resources根目录加载 mail.properties
        ist = Mail.class.getClassLoader().getResourceAsStream("mail.properties");
        try {
            //加载配置文件
            pconf.load(ist);

            this.smtp_host = pconf.getProperty("smtp_host");
            this.smtp_pass = pconf.getProperty("smtp_pass");
            this.smtp_user = pconf.getProperty("smtp_user");
            this.smtp_auth = pconf.getProperty("smtp_auth");
            this.smtp_port = Integer.parseInt(pconf.getProperty("smtp_port"));
            this.smtp_ssl_enable = pconf.getProperty("smtp_ssl_enable");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //测试主入口文件
    public static void main(String[] args) {
        //邮件发送测试， 必须要先实例化，否则无法获取构造函数中的邮件配置信息
        Mail mail = new Mail();

        System.out.println(smtp_host);
        System.out.println(smtp_user);
        System.out.println(smtp_pass);

        try {
            mail.sendEmail("tekin@yeah.net","yck","test");
            System.out.println("mail send OK!");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


    }

    /**
     * java mail sender
     * JAVA 邮件发送
     * @Author Tekin <tekintian@gmail.com>
     * @param To 收件人电子邮箱
     * @param Title 邮件标题
     * @param Text 文本邮件内容
     * @throws GeneralSecurityException
     */
    public static void sendEmail(String To , String Title ,String Text) throws GeneralSecurityException{

        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", smtp_host);
        properties.put("mail.smtp.auth", ""+smtp_auth+"");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", ""+smtp_ssl_enable+"");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        properties.put("mail.smtp.socketFactory.port", smtp_port);

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtp_user,smtp_pass); // 发件人邮件用户名、第三方密码
            }
        });
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(smtp_user));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
            // Set Subject: 头部头字段
            message.setSubject(Title);
            // 设置消息体
            message.setText(Text);
            // 发送消息
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
