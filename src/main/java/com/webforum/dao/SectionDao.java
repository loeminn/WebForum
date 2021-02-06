package com.webforum.dao;

import com.webforum.entity.Post;
import com.webforum.entity.Section;
import com.webforum.entity.User;
import com.webforum.util.JdbcUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionDao {
    private final JdbcUtil jdbc = new JdbcUtil();

    public List searchSection(String sname) {
        String sql = "select s.sid, Sname, s.Uid, u.Uname, t.posts, p.uptime\n" +
                "from section s\n" +
                "         left join user u on s.Uid = u.Uid\n" +
                "         left join (select p.Sid, count(p.PID) posts\n" +
                "                    from section s\n" +
                "                             left join post p on s.Sid = p.Sid\n" +
                "                    group by p.Sid) t on t.Sid = s.Sid\n" +
                "         left join (select Sid, min(Pdate) uptime\n" +
                "                    from post\n" +
                "                    group by sid) p on p.Sid = s.Sid\n" +
                "where Sname like ?\n" +
                "order by t.posts desc";
        sname = "%" + sname + "%";
        ResultSet rs = jdbc.query(sql, sname);
        List sectionlist = new ArrayList<>();
        try {
            while (rs.next()) {
                Section section = getSection(rs);
                sectionlist.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return sectionlist;
    }

    public Section findById(Integer sid) {
        String sql = "select sid, Sname\n" +
                "from section\n" +
                "where Sid = ?;";
        ResultSet rs = jdbc.query(sql, sid);
        Section section = null;
        try {
            if (rs.next()) {
                String sname = rs.getString("sname");
                section = new Section(sid, sname, null, null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return section;
    }

    public Section querySection(Integer sid) {
        String sql = "select s.sid, Sname, s.Uid, u.Uname, t.posts, p.uptime\n" +
                "from section s\n" +
                "         left join user u on s.Uid = u.Uid\n" +
                "         left join (select p.Sid, count(p.PID) posts\n" +
                "                    from section s\n" +
                "                             left join post p on s.Sid = p.Sid\n" +
                "                    group by p.Sid) t on t.Sid = s.Sid\n" +
                "         left join (select Sid, min(Pdate) uptime\n" +
                "                    from post\n" +
                "                    group by sid) p on p.Sid = s.Sid\n" +
                "where p.Sid = ?\n" +
                "order by t.posts desc";
        ResultSet rs = jdbc.query(sql, sid);
        Section section = null;
        try {
            if (rs.next()) {
                section = getSection(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return section;
    }

    public List queryHotSection() {
        String sql = "select s.sid, Sname, s.Uid, u.Uname, t.posts, p.uptime\n" +
                "from section s\n" +
                "         left join user u on s.Uid = u.Uid\n" +
                "         left join (select p.Sid, count(p.PID) posts\n" +
                "                    from section s\n" +
                "                             left join post p on s.Sid = p.Sid\n" +
                "                    group by p.Sid) t on t.Sid = s.Sid\n" +
                "         left join (select Sid, min(Pdate) uptime\n" +
                "                    from post\n" +
                "                    group by sid) p on p.Sid = s.Sid\n" +
                "order by t.posts desc";
        ResultSet rs = jdbc.query(sql);
        List<Section> sectionlist = new ArrayList<>();
        try {
            while (rs.next()) {
                Section section = getSection(rs);
                sectionlist.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return sectionlist;
    }

    public Section getSection(ResultSet rs) throws SQLException {
        int sid = rs.getInt("sid");
        String sname = rs.getString("sname");
        int uid = rs.getInt("uid");
        String uname = rs.getString("uname");
        int posts = rs.getInt("posts");
        Date uptime = rs.getTimestamp("uptime");
        User user = new User(uid, uname, null, null, null, null);
        String time = Post.timeDistance(uptime);
        Section section = new Section(sid, sname, user, posts, time);
        return section;
    }
}
