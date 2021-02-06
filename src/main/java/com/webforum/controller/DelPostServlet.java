package com.webforum.controller;

import com.webforum.dao.CommentsDao;
import com.webforum.dao.PostDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class DelPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        PostDao dao = new PostDao();
        boolean delInfo = dao.delPost(pid);
        if (delInfo) {
            CommentsDao commentsDao = new CommentsDao();
            delInfo = commentsDao.delCommentByPid(pid);
        }
        PrintWriter out = response.getWriter();
        out.print(delInfo);
    }
}
