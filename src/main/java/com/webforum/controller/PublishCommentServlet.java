package com.webforum.controller;

import com.webforum.dao.CommentsDao;
import com.webforum.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class PublishCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userInfo = (User) session.getAttribute("userInfo");
        PrintWriter out = response.getWriter();
        if (userInfo == null) {
            out.print(false);
            return;
        }
        String cdetails = request.getParameter("cdetails");
        int pid = Integer.parseInt(request.getParameter("pid"));
        Integer uid = userInfo.getUid();
        CommentsDao dao = new CommentsDao();
        boolean update = dao.addComments(cdetails, new Date(), uid, pid);
        out.print(update);
    }
}
