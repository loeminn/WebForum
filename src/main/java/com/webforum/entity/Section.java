package com.webforum.entity;

public class Section {
    private Integer sid;
    private String sname;
    private User user;
    private Integer posts;
    private String upTime;

    public Section() {
    }

    public Section(Integer sid, String sname, User user, Integer posts, String upTime) {
        this.sid = sid;
        this.sname = sname;
        this.user = user;
        this.posts = posts;
        this.upTime = upTime;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }
}
