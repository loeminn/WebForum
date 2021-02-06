package com.webforum.dao;

import com.webforum.entity.Post;
import com.webforum.entity.Section;
import com.webforum.entity.User;
import com.webforum.util.JdbcUtil;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    private final JdbcUtil jdbc = new JdbcUtil();

    public boolean updatePost(Integer pid, String ptitle, String pdetails) {
        String sql = "update post set ptitle = ?, pdetails = ? where pid = ?";
        boolean update;
        try {
            update = jdbc.update(sql, ptitle, pdetails, pid);
        } finally {
            jdbc.close();
        }
        return update;
    }

    public boolean delPost(Integer pid) {
        String sql = "delete from post where pid=?";
        boolean update = false;
        try {
            update = jdbc.update(sql, pid);
        } finally {
            jdbc.close();
        }
        return update;
    }

    public Post queryTitleContents(Integer pid) {
        String sql = "select pid, Ptitle, Pdetails\n" +
                "from post p\n" +
                "where pid = ?";
        ResultSet rs = jdbc.query(sql, pid);
        Post post = null;
        try {
            if (rs.next()) {
                String ptitle = rs.getString("Ptitle");
                String pdetails = rs.getString("Pdetails");
                post = new Post(pid, ptitle, pdetails, null, null, null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return post;
    }

    public List userPost(Integer uid) {
        String sql = "select p.pid,\n" +
                "       p.ptitle,\n" +
                "       p.pdetails,\n" +
                "       p.pdate,\n" +
                "       p.uid,\n" +
                "       u.Uname,\n" +
                "       p.sid,\n" +
                "       s.Sname,\n" +
                "       c.comments\n" +
                "from post p\n" +
                "         left join section s on p.Sid = s.Sid\n" +
                "         left join user u on p.UId = u.Uid\n" +
                "         left join (select p.PID, count(c.cid) comments\n" +
                "                    from post p\n" +
                "                             left join comments c on p.PID = c.Pid\n" +
                "                    group by p.Pid) c on p.pid = c.PID\n" +
                "where p.uid = ?\n" +
                "order by p.pdate DESC";
        ResultSet rs = jdbc.query(sql, uid);
        List postList = new ArrayList<>();
        try {
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pdetails = pdetailsCut(rs.getString("pdetails"));
                Post post = getPost(rs, pdetails, pid);
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return postList;
    }

    public List searchPost(String ptitle) {
        String sql = "select p.pid,\n" +
                "       p.ptitle,\n" +
                "       p.pdetails,\n" +
                "       p.pdate,\n" +
                "       p.uid,\n" +
                "       u.Uname,\n" +
                "       p.sid,\n" +
                "       s.Sname,\n" +
                "       c.comments\n" +
                "from post p\n" +
                "         left join section s on p.Sid = s.Sid\n" +
                "         left join user u on p.UId = u.Uid\n" +
                "         left join (select p.PID, count(c.cid) comments\n" +
                "                    from post p\n" +
                "                             left join comments c on p.PID = c.Pid\n" +
                "                    group by p.Pid) c on p.pid = c.PID\n" +
                "where p.ptitle like ?\n" +
                "order by comments DESC";
        ptitle = "%" + ptitle + "%";
        ResultSet rs = jdbc.query(sql, ptitle);
        List postList = new ArrayList<>();
        try {
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pdetails = pdetailsCut(rs.getString("pdetails"));
                Post post = getPost(rs, pdetails, pid);
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return postList;
    }

    public Integer addPost(String ptitle, String pdetails, Date pdate, Integer uid, Integer sid) {
        String sql = "insert into post(ptitle,pdetails,pdate,uid,sid) values(?,?,?,?,?)";
        boolean update = jdbc.update(sql, ptitle, pdetails, pdate, uid, sid);
        Integer pk = 0;
        if (update) {
            pk = jdbc.returnPk();
        }
        jdbc.close();
        return pk;
    }

    public Post findById(Integer pid) {
        String sql = "select p.pid,\n" +
                "       Ptitle,\n" +
                "       Pdetails,\n" +
                "       Pdate,\n" +
                "       p.UId,\n" +
                "       u.Uname,\n" +
                "       p.Sid,\n" +
                "       s.Sname,\n" +
                "       t.comments\n" +
                "from post p\n" +
                "         left join user u on p.UId = u.Uid\n" +
                "         left join section s on p.Sid = s.Sid\n" +
                "         right join (select p.PID, count(c.cid) comments\n" +
                "                     from post p\n" +
                "                              left join comments c on p.PID = c.Pid\n" +
                "                     where p.PID = ?\n" +
                "                     group by p.Pid) t on t.PID = p.PID";
        ResultSet rs = jdbc.query(sql, pid);
        Post post = null;
        try {
            if (rs.next()) {
                String pdetails = rs.getString("pdetails");
                post = getPost(rs, pdetails, pid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return post;
    }

    public List<Post> queryHotPost() {
        String sql = "select p.pid,\n" +
                "       p.ptitle,\n" +
                "       p.pdetails,\n" +
                "       p.pdate,\n" +
                "       p.uid,\n" +
                "       u.Uname,\n" +
                "       p.sid,\n" +
                "       s.Sname,\n" +
                "       c.comments\n" +
                "from post p\n" +
                "         left join section s on p.Sid = s.Sid\n" +
                "         left join user u on p.UId = u.Uid\n" +
                "         left join (select p.PID, count(c.cid) comments\n" +
                "                    from post p\n" +
                "                             left join comments c on p.PID = c.Pid\n" +
                "                    group by p.Pid) c on p.pid = c.PID\n" +
                "order by comments DESC";
        ResultSet rs = jdbc.query(sql);
        List<Post> postList = new ArrayList<>();
        try {
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pdetails = pdetailsCut(rs.getString("pdetails"));
                Post post = getPost(rs, pdetails, pid);
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return postList;
    }

    public List querySectionPost(Integer sid, String oederBy) {
        if (oederBy == null) {
            oederBy = "pdate";
        }
        String sql = "select p.pid,\n" +
                "       p.ptitle,\n" +
                "       p.pdetails,\n" +
                "       p.pdate,\n" +
                "       p.uid,\n" +
                "       u.Uname,\n" +
                "       p.sid,\n" +
                "       s.Sname,\n" +
                "       c.comments\n" +
                "from post p\n" +
                "         left join section s on p.Sid = s.Sid\n" +
                "         left join user u on p.UId = u.Uid\n" +
                "         left join (select p.PID, count(c.cid) comments\n" +
                "                    from post p\n" +
                "                             left join comments c on p.PID = c.Pid\n" +
                "                    group by p.Pid) c on p.pid = c.PID\n" +
                "where p.Sid = ?\n" +
                "order by ? DESC";
        ResultSet rs = jdbc.query(sql, sid, oederBy);
        List<Post> postList = new ArrayList<>();
        try {
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pdetails = pdetailsCut(rs.getString("pdetails"));
                Post post = getPost(rs, pdetails, pid);
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return postList;
    }

    public Post getPost(ResultSet rs, String pdetails, Integer pid) throws SQLException {
        String ptitle = rs.getString("ptitle");
        Date pdate = rs.getTimestamp("pdate");
        int uid = rs.getInt("uid");
        String uname = rs.getString("uname");
        int sid = rs.getInt("sid");
        String sname = rs.getString("sname");
        int comments = rs.getInt("comments");
        String ptime = Post.timeDistance(pdate);
        User user = new User(uid, uname, null, null, null, null);
        Section section = new Section(sid, sname, null, null, null);
        Post post = new Post(pid, ptitle, pdetails, ptime, user, section, comments);
        return post;
    }

    public String pdetailsCut(String pdetails) {
        if (pdetails.length() > 100) {
            pdetails = pdetails.substring(0, 100) + "……";
        }
        return pdetails;
    }
}
