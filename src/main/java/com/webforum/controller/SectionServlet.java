package com.webforum.controller;

import com.webforum.dao.PostDao;
import com.webforum.dao.SectionDao;
import com.webforum.entity.Post;
import com.webforum.entity.Section;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class SectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sid = Integer.parseInt(request.getParameter("sid"));
        String ordeyBy = request.getParameter("ordeyBy");
        PostDao dao = new PostDao();
        SectionDao sectionDao = new SectionDao();
        Section section = sectionDao.querySection(sid);
        List postList = dao.querySectionPost(sid, ordeyBy);
        request.setAttribute("postList", postList);
        request.setAttribute("section", section);
        request.getRequestDispatcher("section.jsp").forward(request, response);
    }
}
