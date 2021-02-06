package com.webforum.controller;

import com.webforum.dao.CommentsDao;
import com.webforum.dao.PostDao;
import com.webforum.entity.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        PostDao dao = new PostDao();
        Post post = dao.findById(pid);
        CommentsDao dao1 = new CommentsDao();
        List commentList = dao1.postComments(pid);
        request.setAttribute("postInfo", post);
        request.setAttribute("commentList", commentList);
        request.getRequestDispatcher("post.jsp").forward(request, response);
    }
}
