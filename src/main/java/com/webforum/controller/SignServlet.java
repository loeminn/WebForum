package com.webforum.controller;

import com.webforum.dao.UserDao;
import com.webforum.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SignServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("upwd");
        UserDao dao = new UserDao();
        boolean info = dao.sign(uname, pwd);
        PrintWriter out = response.getWriter();
        if (info) {
            User user = dao.login(uname, pwd);
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", user);
        }
        out.print(info);
    }
}
