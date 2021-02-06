package com.webforum.controller;

import com.webforum.dao.SectionDao;
import com.webforum.entity.Section;
import com.webforum.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class PostingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userInfo = (User) session.getAttribute("userInfo");
        if (userInfo == null) {
            response.sendRedirect("login.html");
            return;
        }
        Integer sid = Integer.parseInt(request.getParameter("sid"));
        SectionDao dao = new SectionDao();
        Section section = dao.findById(sid);
        request.setAttribute("section", section);
        request.getRequestDispatcher("posting.jsp").forward(request, response);
    }
}
