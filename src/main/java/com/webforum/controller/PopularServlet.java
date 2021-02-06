package com.webforum.controller;

import com.webforum.dao.PostDao;
import com.webforum.dao.SectionDao;
import com.webforum.entity.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PopularServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDao dao = new PostDao();
        List posts = dao.queryHotPost();
        SectionDao dao1 = new SectionDao();
        List sections = dao1.queryHotSection();
        request.setAttribute("posts", posts);
        request.setAttribute("sections", sections);
        request.getRequestDispatcher("popular.jsp").forward(request, response);
    }
}
