package com.webforum.controller;

import com.webforum.dao.PostDao;
import com.webforum.entity.User;
import com.webforum.util.JsonUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userInfo = (User) session.getAttribute("userInfo");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (userInfo == null) {
            response.sendRedirect("login.html");
            out.print(false);
            return;
        }
        PostDao dao = new PostDao();
        List postList = dao.userPost(userInfo.getUid());
        String jsonArray = JsonUtil.jsonArray(postList);
        out.print(jsonArray);
    }
}
