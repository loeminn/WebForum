package com.webforum.entity;

import java.util.Date;

public class Comments {
    private Integer cid;
    private String cdetails;
    private String cdate;
    private User user;
    private Post post;

    public Comments() {
    }

    public Comments(Integer cid, String cdetails, String cdate, User user, Post post) {
        this.cid = cid;
        this.cdetails = cdetails;
        this.cdate = cdate;
        this.user = user;
        this.post = post;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCdetails() {
        return cdetails;
    }

    public void setCdetails(String cdetails) {
        this.cdetails = cdetails;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
