package com.webforum.controller;

import com.webforum.dao.PostDao;
import com.webforum.entity.Post;
import com.webforum.util.JsonUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ModPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        PostDao dao = new PostDao();
        Post post = dao.queryTitleContents(pid);
        String jsonObject = JsonUtil.jsonObject(post);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
    }
}
