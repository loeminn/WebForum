package com.webforum.util;

import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class JdbcUtil {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        ResourceBundle dbInfo = ResourceBundle.getBundle("DBInfo");
        String url = dbInfo.getString("url");
        String user = dbInfo.getString("user");
        String pwd = dbInfo.getString("password");
        try {
            con = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    public PreparedStatement getPs(String sql) {
        try {
            ps = getCon().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    public void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void setPs(String sql, Object... values) {
        ps = getPs(sql);
        try {
            int col = 0;
            for (Object val : values) {
                col++;
                if (val instanceof String) {
                    ps.setString(col, (String) val);
                } else if (val instanceof Integer) {
                    ps.setInt(col, (Integer) val);
                } else {
                    Date date = (Date) val;
                    ps.setTimestamp(col, new Timestamp(date.getTime()));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet query(String sql, Object... values) {
        setPs(sql, values);
        try {
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public boolean update(String sql, Object... values) {
        setPs(sql, values);
        boolean update = false;
        try {
            if ((ps.executeUpdate()) != 0) {
                update = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update;
    }

    public int returnPk() {
        Integer pk = 0;
        try {
            ps = con.prepareStatement("select last_insert_id() as lastid");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pk = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pk;
    }
}
