<%@ page import="com.webforum.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webforum.entity.Post" %>
<%@ page import="com.webforum.entity.Section" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>热门</title>
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.min.css">
    <link href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.15.1/css/all.css" rel="stylesheet">
    <link rel="stylesheet" href="css/forum.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.3/js/bootstrap.min.js"></script>
    <script src="js/forum.js"></script>
</head>
<body>
    <%
        User userInfo = (User) session.getAttribute("userInfo");
        List<Post> posts = (List) request.getAttribute("posts");
        List<Section> sections = (List) request.getAttribute("sections");
    %>
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
            <div class="col-9">
                <div class="container">
                    <div class="row"><span class="listTitle">热门帖子</span></div>
                    <%
                        for (Post post : posts) {
                    %>
                    <div class="post">
                        <h5><a class="btn-link" href="post?pid=<%=post.getPid()%>"><%=post.getPtitle()%>
                        </a></h5>
                        <div><%=post.getPdetails()%>
                        </div>
                        <a href="section?sid=<%=post.getSection().getSid()%>"><%=post.getSection().getSname()%>
                        </a>
                        <a href="user?uid=<%=post.getUser().getUid()%>"><i
                                class="fa fa-user"></i><%=post.getUser().getUname()%>
                        </a>
                        <span><i class="fa fa-comment"></i><%=post.getComments()%></span>
                        <span><i class="fa fa-clock"></i><%=post.getPdate()%></span>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="col-3">
                <div class="container">
                    <div class="row"><span class="listTitle">热门板块</span></div>
                    <%
                        for (Section section : sections) {
                    %>
                    <div class="section">
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
        </div>
    </div>
</body>
</html>
