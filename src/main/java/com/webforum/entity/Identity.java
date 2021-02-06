package com.webforum.entity;

public class Identity {
    private Integer eid;
    private String ename;

    public Identity() {
    }

    public Identity(Integer eid, String ename) {
        this.eid = eid;
        this.ename = ename;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
