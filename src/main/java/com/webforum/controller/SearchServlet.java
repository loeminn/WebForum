package com.webforum.controller;

import com.webforum.dao.PostDao;
import com.webforum.dao.SectionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ptitle = request.getParameter("post");
        if (ptitle != null) {
            PostDao dao = new PostDao();
            List postList = dao.searchPost(ptitle);
            request.setAttribute("postList", postList);
            request.getRequestDispatcher("searchPost.jsp").forward(request, response);
            return;
        }
        String sname = request.getParameter("section");
        if (sname != null) {
            SectionDao dao = new SectionDao();
            List sectionList = dao.searchSection(sname);
            request.setAttribute("sectionList", sectionList);
            request.getRequestDispatcher("searchSection.jsp").forward(request, response);
            return;
        }
    }
}
