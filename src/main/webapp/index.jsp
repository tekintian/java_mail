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