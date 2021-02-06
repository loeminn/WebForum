package com.webforum.controller;

import com.webforum.dao.PostDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UpPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pid = Integer.parseInt(request.getParameter("pid"));
        String ptitle = request.getParameter("ptitle");
        String pdetails = request.getParameter("pdetails");
        PostDao dao = new PostDao();
        boolean update = dao.updatePost(pid, ptitle, pdetails);
        PrintWriter out = response.getWriter();
        out.print(update);
    }
}
