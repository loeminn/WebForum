package com.webforum.controller;

import com.webforum.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class QueryUnameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        UserDao dao = new UserDao();
        boolean info = dao.queryUname(uname);
        PrintWriter out = response.getWriter();
        out.print(info);
    }
}
