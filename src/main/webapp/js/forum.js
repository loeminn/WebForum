$(function () {
    $("#updateUser").on("click", function () {
        location.reload();
    })
    $("#upUserInfo").on("click", function () {
        let uid = $("#userId").val();
        let uname = $("#userName").val();
        let usex = $(".userInfo input[name='usex']:checked").val();
        let uage = $("#userAge").val();
        if ($(".userInfo .uname").hasClass("is-invalid")) {
            return;
        }
        $.get("update/user", {uid: uid, uname: uname, usex: usex, uage: uage}, function (resp) {
            if (resp) {
                $("#UserIndoModal").modal("show");
            }
        }, "json")
    })
    $("#myPost").on("click", function () {
        getPostList();
    })
    $("#myComment").on("click", function () {
        getComment();
    })
    let myComList = $(".myComList");
    $(myComList).on("click", "#conComModify", function () {
        let cid = $("#conComModify").attr("name");
        let cdetails = $("#modCdetails").val().replace(/\n/g, "").replace(/\s|\xA0/g, "");
        $.post("update/comment", {cid: cid, cdetails: cdetails}, function (resp) {
            if (resp) {
                getComment();
                $("#modifyComModal").modal("hide");
            }
        }, "json")
    })
    $(myComList).on("click", "#conDelCom", function () {
        let cid = $("#conDelCom").attr("name");
        $.get("del/comment", {cid: cid}, function (resp) {
            if (resp) {
                getComment();
                $("#delComModal").modal("hide");
            }
        }, "json")
    })
    $(".userInfo .uname").on("input", function () {
        let name = $(this).attr("name");
        let uname = $(this).val();
        if (name === uname) {
            $(".userInfo .uname").removeClass("is-invalid");
            return;
        }
        $.get("query/uname", {uname: uname}, function (resp) {
            if (resp) {
                $(".userInfo .uname").addClass("is-invalid");
            } else {
                $(".userInfo .uname").removeClass("is-invalid");
            }
        }, "json")
    })
    $(myComList).on("click", ".delComment", function () {
        let cid = $(this).attr("name");
        $("#conDelCom").attr("name", cid);
        $("#delComModal").modal("show");
    })
    $(myComList).on("click", ".upComment", function () {
        let cid = $(this).attr("name");
        $.get("mod/comment", {cid: cid}, function (resp) {
            $("#modCdetails").val(resp.cdetails);
            $("#conComModify").attr("name", cid);
        }, "json")
        $("#modifyComModal").modal("show");
    })
    let postList = $(".myPostList");
    $(postList).on("click", "#conModify", function () {
        let pid = $("#conModify").attr("name");
        let ptitle = $("#modPtitle").val();
        let pdetails = $("#modPdetails").val().replace(/\n/g, "").replace(/\s|\xA0/g, "");
        $.post("update/post", {pid: pid, ptitle: ptitle, pdetails: pdetails}, function (resp) {
            if (resp) {
                getPostList();
                $("#modifyModal").modal("hide");
            }
        }, "json")
    })
    $(postList).on("click", "#conDel", function () {
        let pid = $("#conDel").attr("name");
        $.get("del/post", {pid: pid}, function (resp) {
            if (resp) {
                getPostList();
                $("#delModal").modal("hide");
            }
        }, "json")
    })
    $(postList).on("click", '.delPost', function () {
        let pid = $(this).attr("name");
        $("#conDel").attr("name", pid);
        $("#delModal").modal("show");
    })
    $(postList).on("click", '.modify', function () {
        let pid = $(this).attr("name");
        $.get("mod/post", {pid: pid}, function (resp) {
            $("#modPtitle").val(resp.ptitle);
            $("#modPdetails").val(resp.pdetails);
            $("#conModify").attr("name", resp.pid);
        }, "json")
        $("#modifyModal").modal("show");
    })
    $('.myList li').on('click', function () {
        $(this).addClass("active").siblings().removeClass("active");
        let i = $(this).index();
        $(".tab-content>div").eq(i).addClass("active").siblings().removeClass("active");
    })
    $("#searchBtn").on("click", function () {
        let status = $(".mySelect>option:selected").val();
        let val = $("#search").val();
        window.location.href = "search?" + status + "=" + val;
    })
    $("#Tologin").on("click", function () {
        window.location.href = "login.html";
    })
    $("#Tosuccess").on("click", function () {
        location.reload();
    })
    $("#upComment").on("click", function () {
        let comment = $(this).next().val().replace(/\n/g, "").replace(/\s|\xA0/g, "");
        let pid = $(this).next().attr("name");
        if (comment.length === 0) {
            $(this).text("不能为空").removeClass("btn-success").addClass("btn-danger");
            setTimeout(function () {
                $("#upComment").text("发表评论").removeClass("btn-danger").addClass("btn-success");
            }, 1500)
            return;
        }
        $.get("publish/comment", {cdetails: comment, pid: pid}, function (resp) {
            if (resp) {
                $("#commentModal .modal-body").text("评论发表成功");
                $("#Tosuccess").show().siblings().hide();
                $("#commentModal").modal("show");
            } else {
                $("#commentModal .modal-body").text("请先登录");
                $("#Tologin").show().siblings().hide();
                $("#commentModal").modal("show");
            }
        }, "json")
    })
    $("#publish").on("click", function () {
        let ptitle = $("#ptitle").val();
        let pdetails = $("#pdetails").val().replace(/\n/g, "").replace(/\s|\xA0/g, "");
        let sid = $("#sid").attr("href").substring(12, this.length);
        if (ptitle.length === 0 || pdetails.length === 0) {
            $("#publish").text("不能为空").removeClass("btn-primary").addClass("btn-danger");
            setTimeout(function () {
                $("#publish").text("发布帖子").removeClass("btn-danger").addClass("btn-primary");
            }, 1500)
            return;
        }
        $.post("publish", {ptitle: ptitle, pdetails: pdetails, sid: sid}, function (resp) {
            if (resp !== 0) {
                $("#publish").text("发布成功").removeClass("btn-primary").addClass("btn-success");
                setTimeout(function () {
                    window.location.href = "post?pid=" + resp;
                }, 800)
            } else {
                $("#publish").text("发布失败").removeClass("btn-primary").addClass("btn-danger");
                setTimeout(function () {
                    $("#publish").text("发布帖子").removeClass("btn-danger").addClass("btn-primary");
                }, 800)
            }
        }, "json")
    })
    $("#loginBtn").on("click", function () {
        let uname = $("#loginFrom .uname").val();
        let upwd = $("#loginFrom .upwd").val();
        $.post("login", {uname: uname, upwd: upwd}, function (resp) {
            if (resp) {
                $("#loginBtn").text("登录成功").removeClass("btn-primary").addClass("btn-success");
                setTimeout(function () {
                    window.location.href = "popular";
                }, 800)
            } else {
                $("#loginBtn").text("用户名或密码错误").removeClass("btn-primary").addClass("btn-danger");
                setTimeout(function () {
                    $("#loginBtn").text("登录").removeClass("btn-danger").addClass("btn-primary");
                }, 1500)
            }
        }, "json")
    })
    $("#signBtn").on("click", function () {
        if ($("#signFrom input").hasClass("is-invalid")) {
        } else {
            let uname = $("#signFrom .uname").val();
            let upwd = $("#signFrom .upwd").val();
            if (uname.length === 0 || upwd.length === 0) {
                $("#signBtn").text("不能为空").removeClass("btn-primary").addClass("btn-danger");
                setTimeout(function () {
                    $("#signBtn").text("注册").removeClass("btn-danger").addClass("btn-primary");
                }, 1500)
                return;
            }
            $.post("sign", {uname: uname, upwd: upwd}, function (resp) {
                if (resp) {
                    $("#signBtn").text("注册成功").removeClass("btn-primary").addClass("btn-success");
                    setTimeout(function () {
                        window.location.href = "popular";
                    }, 800)
                } else {
                    $("#signBtn").text("注册失败").removeClass("btn-primary").addClass("btn-danger");
                    setTimeout(function () {
                        $("#signBtn").text("注册").removeClass("btn-danger").addClass("btn-primary");
                    }, 1500)
                }
            }, "json");
        }
    })
    $("#signFrom").on("input", ".uname", function () {
        let uname = $(this).val();
        $.get("query/uname", {uname: uname}, function (resp) {
            if (resp) {
                $("#signFrom .uname").addClass("is-invalid");
            } else {
                $("#signFrom .uname").removeClass("is-invalid");
            }
        }, "json")
    })
    $("#changePwd").on("click", function () {
        if ($(".pwdTab input").hasClass("is-invalid")) {
            return;
        }
        let uid = $("#userId").val();
        let upwd = $(".oripwd").val();
        let conupwd = $(".conupwd").val();
        $.post("update/possword", {uid: uid, upwd: upwd, newPwd: conupwd}, function (resp) {
            if (resp) {
                $("#pwdSuModal").modal("show");
                $(".pwdTab input").val("");
            } else {
                $("#pwdFailModal").modal("show");
            }
        }, "json")
    })
    $(".pwd").on("change", function () {
        let conupwd = $(".upwd").val();
        let upwd = $(".conupwd").val();
        if (conupwd === upwd) {
            $(".conupwd").removeClass("is-invalid");
        } else {
            $(".conupwd").addClass("is-invalid");
        }
    })
    $("#signFrom").on("change", "input[class*=pwd]", function () {
        let conupwd = $("#signFrom .upwd").val();
        let upwd = $("#signFrom .conupwd").val();
        if (conupwd === upwd) {
            $("#signFrom .conupwd").removeClass("is-invalid");
        } else {
            $("#signFrom .conupwd").addClass("is-invalid");
        }
    })
    $("#loginTab").on("click", "li", function () {
        $(this).children("a").addClass("active");
        $(this).siblings().children("a").removeClass("active");
        let i = $(this).index();
        $(".con>div").eq(i).addClass("con_now").siblings().removeClass("con_now");
    })
});

function getPostList() {
    $.get("user/post", null, function (resp) {
        let $myPostList = $(".myPostList");
        $(".myPostList .secName").text("共" + resp.length + "条文章")
        $(".myPost").remove();
        eachPostList($myPostList, resp);
    }, "json")
}

function getComment() {
    $.get("user/comment", null, function (resp) {
        let $myComList = $(".myComList");
        $(".myComList .secName").text("共" + resp.length + "条评论")
        $(".comment").remove();
        eachCommentList($myComList, resp);
    }, "json")
}

function eachPostList($myPostList, resp) {
    for (let post of resp) {
        $myPostList.append("<div class='post sectionPost myPost'>" +
            "                                <h5><a class='btn-link' href='post?pid=" + post.pid + "'>" + post.ptitle +
            "                                </a></h5>" +
            "                                <div>" + post.pdetails +
            "                                </div>" +
            "                                <a href='section?sid=" + post.section.sid + "'>" + post.section.sname +
            "                                </a>" +
            "                                <span><i class='fa fa-comment'></i>" + post.comments + "</span>" +
            "                                <span><i class='fa fa-clock'></i>" + post.pdate + "</span>" +
            "                                <button name='" + post.pid + "' class='btn badge-danger float-right delPost'>删除" +
            "                                </button>" +
            "                                <button name='" + post.pid + "' class='btn badge-info float-right modify'>修改</button>" +
            "                            </div>")
    }
}

function eachCommentList($myComList, resp) {
    for (let comment of resp) {
        $myComList.append("<div class='comment'>" +
            "                                <a href='post?pid=" + comment.post.pid + "'>" + comment.post.ptitle +
            "                                </a>" +
            "                                <p>" + comment.cdetails +
            "                                </p>" +
            "                                <div>" +
            "                                    <span>" + comment.cdate + "</span>" +
            "                                    <button name='" + comment.cid + "' class='btn btn-danger float-right delComment'>删除</button>" +
            "                                    <button name='" + comment.cid + "' class='btn btn-info float-right upComment'>修改</button>" +
            "                                </div>" +
            "                            </div>")
    }
}