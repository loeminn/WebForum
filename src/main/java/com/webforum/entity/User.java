package com.webforum.entity;

public class User {
    private Integer uid;
    private String uname;
    private String upwd;
    private String usex;
    private Integer uage;
    private Identity identity;

    public User() {
    }

    public User(Integer uid, String uname, String upwd, String usex, Integer uage, Identity identity) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.usex = usex;
        this.uage = uage;
        this.identity = identity;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public Integer getUage() {
        return uage;
    }

    public void setUage(Integer uage) {
        this.uage = uage;
    }

}
