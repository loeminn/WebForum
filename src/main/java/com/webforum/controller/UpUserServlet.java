package com.webforum.controller;

import com.webforum.dao.UserDao;
import com.webforum.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UpUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer uid = Integer.parseInt(request.getParameter("uid"));
        String uname = request.getParameter("uname");
        String usex = request.getParameter("usex");
        Integer uage = Integer.parseInt(request.getParameter("uage"));
        UserDao dao = new UserDao();
        boolean update = dao.updateUser(uid, uname, usex, uage);
        if (update) {
            HttpSession session = request.getSession();
            User user = dao.findById(uid);
            session.setAttribute("userInfo", user);
        }
        PrintWriter out = response.getWriter();
        out.print(update);
    }
}
