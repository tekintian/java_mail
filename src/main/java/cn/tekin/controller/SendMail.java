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
