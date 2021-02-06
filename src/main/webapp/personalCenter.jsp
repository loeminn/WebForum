<%@ page import="com.webforum.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webforum.entity.Post" %>
<%@ page import="com.webforum.entity.Comments" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        User userInfo = (User) session.getAttribute("userInfo");
    %>
    <title>个人中心</title>
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
                <a href="personal" id="userInfo"
                   class="btn btn-link px-1"><%=userInfo.getUname()%>
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
    <div class="container-fluid personal">
        <div class="row">
            <div class="col-3 mytab">
                <ul class="list-group myList">
                    <li class="list-group-item active">个人信息</li>
                    <li id="myPost" class="list-group-item">我的文章</li>
                    <li id="myComment" class="list-group-item">我的评论</li>
                    <li class="list-group-item">修改密码</li>
                    <%
                        if (userInfo.getIdentity().getEid() == 1) {
                    %>
                    <li class="list-group-item">用户管理</li>
                    <li class="list-group-item">板块管理</li>
                    <li class="list-group-item">文章管理</li>
                    <%
                        }
                    %>
                </ul>
            </div>
            <div class="col-9">
                <div class="mytab-con tab-content">
                    <div class="tab-pane active">
                        <div class="userInfo">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">用户ID：</label>
                                <div class="col-sm-10">
                                    <input id="userId" type="text" class="form-control" value="<%=userInfo.getUid()%>"
                                           readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">用户身份：</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"
                                           value="<%=userInfo.getIdentity().getEname()%>" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label uname">用户名：</label>
                                <div class="col-sm-10">
                                    <input id="userName" type="text" name="<%=userInfo.getUname()%>"
                                           value="<%=userInfo.getUname()%>"
                                           class="form-control">
                                    <div class='invalid-feedback'>此用户名已被使用</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <legend class="col-form-label col-sm-2 pt-0">性别：</legend>
                                    <div class="col-10">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="usex"
                                                   value="男" <%=userInfo.getUsex().equals("男")?"checked":""%>>
                                            <label class="form-check-label">男</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="usex"
                                                   value="女" <%=userInfo.getUsex().equals("女")?"checked":""%>>
                                            <label class="form-check-label">女</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="usex"
                                                   value="" <%=userInfo.getUsex().equals("")?"checked":""%>>
                                            <label class="form-check-label">保密</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">用户年龄：</label>
                                <div class="col-sm-10">
                                    <input id="userAge" type="text" class="form-control"
                                           value="<%=userInfo.getUage()%>">
                                </div>
                            </div>
                            <button id="upUserInfo" class="btn btn-success float-right">确认修改</button>
                        </div>
                        <div class="modal fade" id="UserIndoModal" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">修改用户信息</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        修改成功
                                        <div class="modal-footer">
                                            <button id="updateUser" class="btn btn-success">确认</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane">
                        <div class="myPostList">
                            <div class="sectionHead">
                                <span class="secName"></span>
                            </div>
                            <div class="modal fade" id="modifyModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">修改文章</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label class="col-form-label">标题：</label>
                                                <input id="modPtitle" type="text" class="form-control"
                                                       placeholder="帖子标题">
                                                <div class="form-group">
                                                    <label class=" col-form-label">内容：</label>
                                                    <textarea id="modPdetails" type="text" class="form-control"
                                                              placeholder="帖子内容"></textarea>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button id="conModify" class="btn btn-success">确认修改</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="delModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">删除文章</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            确认删除此文章?
                                            <div class="modal-footer">
                                                <button id="conDel" class="btn btn-danger">确认</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane">
                        <div class="myComList">
                            <div class="sectionHead">
                                <span class="secName">共条评论</span>
                            </div>
                            <div class="modal fade" id="modifyComModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">修改评论</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label class=" col-form-label">内容：</label>
                                                <textarea id="modCdetails" type="text" class="form-control"
                                                          placeholder="评论内容"></textarea>
                                            </div>
                                            <div class="modal-footer">
                                                <button id="conComModify" class="btn btn-success">确认修改</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="delComModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">删除评论</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            确认删除此评论?
                                            <div class="modal-footer">
                                                <button id="conDelCom" class="btn btn-danger">确认</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane">
                        <div class="pwdTab">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">原密码：</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control oripwd">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">新密码：</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control pwd upwd">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">确认密码：</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control pwd conupwd">
                                    <div class='invalid-feedback'>两次输入的密码不一致</div>
                                </div>
                            </div>
                            <button id="changePwd" class="btn btn-success float-right">确认修改</button>
                        </div>
                        <div class="modal fade" id="pwdSuModal" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">修改状态</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        密码修改成功
                                        <div class="modal-footer">
                                            <button class="btn btn-success" data-dismiss="modal">确认</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="pwdFailModal" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">修改状态</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        原密码输入错误
                                        <div class="modal-footer">
                                            <button class="btn btn-danger" data-dismiss="modal">确认</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        if (userInfo.getIdentity().getEid() == 1) {
                    %>
                    <div class="tab-pane">用户</div>
                    <div class="tab-pane">板块</div>
                    <div class="tab-pane">文章</div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
