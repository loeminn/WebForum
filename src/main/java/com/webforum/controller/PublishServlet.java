package com.webforum.controller;

import com.webforum.dao.PostDao;
import com.webforum.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class PublishServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userInfo = (User) session.getAttribute("userInfo");
        if (userInfo == null) {
            response.sendRedirect("login.html");
        }
        String ptitle = request.getParameter("ptitle");
        String pdetails = request.getParameter("pdetails");
        int sid = Integer.parseInt(request.getParameter("sid"));
        Integer uid = userInfo.getUid();
        PostDao dao = new PostDao();
        Date pdate = new Date();
        Integer lastId = dao.addPost(ptitle, pdetails, pdate, uid, sid);
        PrintWriter out = response.getWriter();
        out.print(lastId);
    }
}
