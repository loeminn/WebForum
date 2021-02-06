package com.webforum.controller;

import com.webforum.dao.CommentsDao;
import com.webforum.dao.PostDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UpCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));
        String cdetails = request.getParameter("cdetails");
        CommentsDao dao = new CommentsDao();
        boolean update = dao.updateComment(cid, cdetails);
        PrintWriter out = response.getWriter();
        out.print(update);
    }
}
