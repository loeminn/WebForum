package com.webforum.controller;

import com.webforum.dao.UserDao;
import com.webforum.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        UserDao dao = new UserDao();
        User user = dao.login(uname, upwd);
        PrintWriter out = response.getWriter();
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", user);
            out.print(true);
        } else {
            out.print(false);
        }
    }
}
