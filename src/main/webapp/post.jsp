<%@ page import="com.webforum.entity.User" %>
<%@ page import="com.webforum.entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webforum.entity.Comments" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        User userInfo = (User) session.getAttribute("userInfo");
        Post post = (Post) request.getAttribute("postInfo");
        List<Comments> commentList = (List) request.getAttribute("commentList");
    %>
    <title><%=post.getPtitle()%>
    </title>
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
            <div class="col-12 postPage">
                <div class="postHead">
                    <a class="sec" href="section?sid=<%=post.getSection().getSid()%>"><i
                            class="fa fa-angle-left"></i><%=post.getSection().getSname()%>
                    </a>
                    <a href="posting?sid=<%=post.getSection().getSid()%>" class="btn btn-outline-info">我要发贴</a>
                </div>
                <div class="postBody">
                    <h3 class="text-center"><%=post.getPtitle()%>
                    </h3>
                    <div class="postInfo text-center">
                        <a><i class="fa fa-user"></i><%=post.getUser().getUname()%>
                        </a>
                        <a><i class="fa fa-clock"></i><%=post.getPdate()%>
                        </a>
                        <a><i class="fa fa-comment"></i><%=post.getComments()%>
                        </a>
                    </div>
                    <p><%=post.getPdetails()%>
                    </p>
                </div>
                <div class="myComment clearfix">
                    <button id="upComment" class="btn btn-success">发表评论</button>
                    <textarea placeholder="发表你的观点" class="form-control w-75" name="<%=post.getPid()%>"></textarea>
                    <div class="modal fade" id="commentModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">发布状态</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                </div>
                                <div class="modal-footer">
                                    <button id="Tologin" class="btn btn-primary">登录</button>
                                    <button style="display: none" id="Tosuccess" class="btn btn-success">确认</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="comments">
                    <h4 class="commentNumber"><%=post.getComments()%>评论</h4>
                    <%
                        for (Comments comments : commentList) {
                    %>
                    <div class="comment">
                        <a><%=comments.getUser().getUname()%>
                        </a>
                        <p><%=comments.getCdetails()%>
                        </p>
                        <div><%=comments.getCdate()%>
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
