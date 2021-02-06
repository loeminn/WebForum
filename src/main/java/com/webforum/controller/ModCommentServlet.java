package com.webforum.controller;

import com.webforum.dao.CommentsDao;
import com.webforum.dao.PostDao;
import com.webforum.entity.Comments;
import com.webforum.entity.Post;
import com.webforum.util.JsonUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ModCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        CommentsDao dao = new CommentsDao();
        Comments comments = dao.queryDetails(cid);
        String jsonObject = JsonUtil.jsonObject(comments);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
    }
}
