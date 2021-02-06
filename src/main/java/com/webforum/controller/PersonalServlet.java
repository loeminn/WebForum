package com.webforum.controller;

import com.webforum.dao.CommentsDao;
import com.webforum.dao.PostDao;
import com.webforum.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.util.List;

public class PersonalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userInfo = (User) session.getAttribute("userInfo");
        if (userInfo == null) {
            response.sendRedirect("login.html");
            return;
        }
        request.getRequestDispatcher("personalCenter.jsp").forward(request, response);
    }
}
