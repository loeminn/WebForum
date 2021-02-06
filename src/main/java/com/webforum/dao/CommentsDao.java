package com.webforum.dao;

import com.webforum.entity.Comments;
import com.webforum.entity.Post;
import com.webforum.entity.User;
import com.webforum.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentsDao {
    private final JdbcUtil jdbc = new JdbcUtil();

    public boolean delComment(Integer cid) {
        String sql = "delete from comments where cid = ?";
        boolean update = false;
        try {
            update = jdbc.update(sql, cid);
        } finally {
            jdbc.close();
        }
        return update;
    }

    public boolean updateComment(Integer cid, String cdetails) {
        String sql = "update comments set cdetails = ? where cid = ?";
        boolean update = false;
        try {
            update = jdbc.update(sql, cdetails, cid);
        } finally {
            jdbc.close();
        }
        return update;
    }

    public Comments queryDetails(Integer cid) {
        String sql = "select cdetails from comments where cid=?";
        ResultSet rs = jdbc.query(sql, cid);
        Comments comments = null;
        try {
            while (rs.next()) {
                String cdetails = rs.getString("cdetails");
                comments = new Comments(cid, cdetails, null, null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return comments;
    }

    public boolean delCommentByPid(Integer pid) {
        String sql = "delete from comments where pid = ?";
        boolean update = false;
        try {
            update = jdbc.update(sql, pid);
        } finally {
            jdbc.close();
        }
        return update;
    }

    public List myComments(Integer uid) {
        String sql = "select Cid, Cdetails, Cdate, u.Uid, u.Uname,c.pid,p.ptitle\n" +
                "from comments c\n" +
                "         left join user u on c.Uid = u.Uid\n" +
                "         left join post p on c.pid = p.pid\n" +
                "where c.uid = ?";
        ResultSet rs = jdbc.query(sql, uid);
        List commentlist = new ArrayList<>();
        try {
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String cdetails = rs.getString("cdetails");
                Timestamp cdate = rs.getTimestamp("cdate");
                int pid = rs.getInt("pid");
                String ptitle = rs.getString("ptitle");
                String uname = rs.getString("uname");
                String ctime = Post.timeDistance(cdate);
                User user = new User(uid, uname, null, null, null, null);
                Post post = new Post(pid, ptitle, null, null, null, null, null);
                Comments comments = new Comments(cid, cdetails, ctime, user, post);
                commentlist.add(comments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return commentlist;
    }

    public List postComments(Integer pid) {
        String sql = "select Cid, Cdetails, Cdate, u.Uid, u.Uname\n" +
                "from comments c\n" +
                "         left join user u on c.Uid = u.Uid\n" +
                "where Pid = ?";
        ResultSet rs = jdbc.query(sql, pid);
        List commentlist = new ArrayList<>();
        try {
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String cdetails = rs.getString("cdetails");
                Timestamp cdate = rs.getTimestamp("cdate");
                int uid = rs.getInt("uid");
                String uname = rs.getString("uname");
                String ctime = Post.timeDistance(cdate);
                User user = new User(uid, uname, null, null, null, null);
                Comments comments = new Comments(cid, cdetails, ctime, user, null);
                commentlist.add(comments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return commentlist;
    }

    public boolean addComments(String cdetails, Date date, Integer uid, Integer pid) {
        String sql = "insert into comments(cdetails,cdate,uid,pid) values(?,?,?,?)";
        boolean update = false;
        try {
            update = jdbc.update(sql, cdetails, date, uid, pid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return update;
    }
}
