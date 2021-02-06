<%@ page import="com.webforum.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webforum.entity.Section" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        User userInfo = (User) session.getAttribute("userInfo");
        List<Section> sectionList = (List) request.getAttribute("sectionList");
    %>
    <title>板块搜索</title>
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.min.css">
    <link href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.15.1/css/all.css" rel="stylesheet">
    <link rel="stylesheet" href="css/forum.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.3/js/bootstrap.min.js"></script>
    <script src="js/forum.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="popular">MyForum</a>
            <div class="input-group w-50">
                <div class="input-group-prepend">
                    <select class="form-control mySelect">
                        <option value="section">搜板块</option>
                        <option value="post">搜帖子</option>
                    </select>
                </div>
                <input type="text" id="search" class="form-control" placeholder="请输入关键字">
                <div class="input-group-append">
                    <button id="searchBtn" class="btn btn-primary">搜索</button>
                </div>
            </div>
            <%
                if (userInfo != null) {
            %>
            <div>
                <a href="personal" id="userInfo" class="btn btn-link px-1"><%=userInfo.getUname()%>
                </a>
                <span class="dLine">|</span>
                <a href="logout" class="btn btn-link px-1">退出</a>
            </div>
            <%
            } else {
            %>
            <a href="login.html" class="btn btn-link">登录</a>
            <%}%>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="sectionHead col-12">
                <span class="secName"><%=sectionList.size()%>条板块搜索结果</span>
            </div>
        </div>
        <div class="row">
            <%
                for (Section section : sectionList) {
            %>
            <div class="searchSection col-3">
                <a class="sname" href="section?sid=<%=section.getSid()%>"><%=section.getSname()%>
                </a>
                <a href="user?uid=<%=section.getUser().getUid()%>">版主：<%=section.getUser().getUname()%>
                </a><a>贴子数：<%=section.getPosts()%>
            </a>
                <div>最后更新：<%=section.getUpTime()%>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
