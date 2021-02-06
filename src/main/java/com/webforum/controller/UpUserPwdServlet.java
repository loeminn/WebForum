package com.webforum.controller;

import com.webforum.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UpUserPwdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer uid = Integer.parseInt(request.getParameter("uid"));
        String upwd = request.getParameter("upwd");
        String newPwd = request.getParameter("newPwd");
        UserDao dao = new UserDao();
        boolean update = dao.updatePwd(uid, upwd, newPwd);
        PrintWriter out = response.getWriter();
        out.print(update);
    }
}
