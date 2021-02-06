package com.webforum.controller;

import com.webforum.dao.CommentsDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class DelCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));
        CommentsDao dao = new CommentsDao();
        boolean update = dao.delComment(cid);
        PrintWriter out = response.getWriter();
        out.print(update);
    }
}
