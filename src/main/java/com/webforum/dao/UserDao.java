package com.webforum.dao;

import com.webforum.entity.Identity;
import com.webforum.entity.User;
import com.webforum.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final JdbcUtil jdbc = new JdbcUtil();

    public boolean updateUser(Integer uid, String uname, String usex, Integer uage) {
        String sql = "update user set uname = ?,usex = ?,uage=? where uid = ?";
        boolean update;
        try {
            update = jdbc.update(sql, uname, usex, uage, uid);
        } finally {
            jdbc.close();
        }
        return update;
    }

    public boolean queryUname(String uname) {
        boolean info = false;
        ResultSet rs = jdbc.query("select * from user where uname=?", uname);
        try {
            info = rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            jdbc.close();
        }
        return info;
    }

    public boolean updatePwd(Integer uid, String upwd, String newPwd) {
        String sql = "update user set upwd = ? where uid = ? and upwd=?";
        boolean update;
        try {
            update = jdbc.update(sql, newPwd, uid, upwd);
        } finally {
            jdbc.close();
        }
        return update;
    }

    public User findById(Integer uid) {
        String sql = "select uname, upwd, usex, uage, u.eid, ename from user u left join identity e on u.EId = e.Eid where uid = ?";
        ResultSet rs = jdbc.query(sql, uid);
        User user = null;
        try {
            while (rs.next()) {
                String uname = rs.getString("uname");
                String upwd = rs.getString("upwd");
                String usex = rs.getString("usex");
                Integer uage = rs.getInt("uage");
                Integer eid = rs.getInt("eid");
                String ename = rs.getString("ename");
                Identity identity = new Identity(eid, ename);
                user = new User(uid, uname, upwd, usex, uage, identity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return user;
    }

    public User login(String uname, String upwd) {
        String sql = "select uid, uname, usex, uage, u.eid, ename from user u left join identity e on u.EId = e.Eid where uname = ? and upwd = ?";
        ResultSet rs = jdbc.query(sql, uname, upwd);
        User user = null;
        try {
            while (rs.next()) {
                int uid = rs.getInt("uid");
                String usex = rs.getString("usex");
                Integer uage = rs.getInt("uage");
                Integer eid = rs.getInt("eid");
                String ename = rs.getString("ename");
                Identity identity = new Identity(eid, ename);
                user = new User(uid, uname, upwd, usex, uage, identity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return user;
    }

    public boolean sign(String uname, String pwd) {
        boolean signInfo = false;
        String sql = "insert into user(uname,upwd,usex,eid) values(?,?,'',3)";
        signInfo = jdbc.update(sql, uname, pwd);
        jdbc.close();
        return signInfo;
    }
}
