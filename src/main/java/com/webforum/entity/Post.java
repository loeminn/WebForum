package com.webforum.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Post {
    private Integer pid;
    private String ptitle;
    private String pdetails;
    private String pdate;
    private User user;
    private Section section;
    private Integer comments;

    public Post() {
    }

    public Post(Integer pid, String ptitle, String pdetails, String pdate, User user, Section section, Integer comments) {
        this.pid = pid;
        this.ptitle = ptitle;
        this.pdetails = pdetails;
        this.pdate = pdate;
        this.user = user;
        this.section = section;
        this.comments = comments;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPdetails() {
        return pdetails;
    }

    public void setPdetails(String pdetails) {
        this.pdetails = pdetails;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public static String timeDistance(Date upTime) {
        if (upTime == null) {
            return "无";
        }
        Date now = new Date();
        Calendar calNow = Calendar.getInstance();
        Calendar calUp = Calendar.getInstance();
        calNow.setTime(now);
        calUp.setTime(upTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf.format(upTime);
        if (calNow.get(Calendar.MONTH) == calUp.get(Calendar.MONTH)) {
            int day = calNow.get(Calendar.DATE) - calUp.get(Calendar.DATE);
            time = day + "天前";
            if (calNow.get(Calendar.DATE) == calUp.get(Calendar.DATE)) {
                int hour = calNow.get(Calendar.HOUR_OF_DAY) - calUp.get(Calendar.HOUR_OF_DAY);
                time = hour + "小时前";
                if (calNow.get(Calendar.HOUR_OF_DAY) == calUp.get(Calendar.HOUR_OF_DAY)) {
                    time = "刚刚";
                }
            }
        }
        return time;
    }
}
