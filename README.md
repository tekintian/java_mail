# java_mail JAVA邮件发送表单
java mail, java send mail form with javax mail sender, build with Gradle , servlet

采用 gradle 管理依赖， junit单元测试， servlet , jsp等技术；

邮件发送账号在统一 的 properties文件中配置，方便集成管理。

邮件发送支持 gmail, 阿里云邮件等支持 smtp的邮件系统

到目录配置您的邮件发送账号：

    src\main\resources\mail.properties

- 配置示例， 一般只需要配置前三项即可， 默认采用SSL方式 465端口发送
```properties
#smtp HOST
smtp_host=smtp.mxhichina.com
#SMTP passWord
smtp_pass=123456
#Smtp User
smtp_user=yourname@domain.com

```

导包

    import cn.tekin.utils.Mail;


邮件发送示例：

```java
try {
    Mail mail=new Mail();
    mail.sendEmail("tekin@yeah.net","test","test from java mail");
} catch (GeneralSecurityException e) {
    e.printStackTrace();
}
```

完整的servlet邮件发送示例代码

```java

package cn.tekin.controller;

import cn.tekin.utils.Mail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

@WebServlet("/SendMail.do")
public class SendMail extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();

        String send_to = req.getParameter("send_to");
        String mail_title = req.getParameter("mail_title");
        String mail_text = req.getParameter("mail_text");

        try {
            Mail mail=new Mail();
            mail.sendEmail(send_to,mail_title,mail_text);
            out.println("邮件发送成功！");
            out.println("<br><a href='javascript:go(-1)'>点此返回</a>");
            resp.setHeader("Refresh","3;url="+ req.getServletContext().getRealPath()+"/index.jsp");

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }
}

```

form.jsp
```jsp

<%--
  @User: Tekin   @Date: 2018/5/14  @Time: 18:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Send Mail</title>
    <link href="https://cdn.bootcss.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">


<form action="<%=basePath %>SendMail.do" method="post">
    <ul class="list-view">
        <li>邮件接收人：<input type="text" name="send_to"></li>
        <li>邮件标题：<input type="text" name="mail_title">
            </li>
        <li>邮件内容：<textarea name="mail_text" cols="10" cols="5" style="width: 20rem; height:10rem;"></textarea>
            </li>
        <li class="btn btn-primary"><input type="submit" value="发送"></li>
    </ul>
</form>
</div>
</body>
</html>
```


[Tekin](http://tekin.yunnan.ws)




