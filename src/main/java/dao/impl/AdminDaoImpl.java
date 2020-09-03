package dao.impl;

import entity.Admin;
import entity.Role;
import dao.RoleDao;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/***
 * 顧客類別
 */
public class AdminDaoImpl implements RoleDao {
    public Role login(String name, String password) {
        String sql = "select * from admin where name=? and password=?";
        Connection conn = JDBCUtil.getConnect("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                Admin admin = new Admin();
                admin.setId(result.getInt("id"));
                admin.setName(result.getString("name"));
                admin.setPassword(result.getString("password"));
                admin.setLevel(result.getInt("level"));
                return admin;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 註冊
     *
     * @param role
     * @return
     */
    public boolean register(Role role) {
        Admin admin = (Admin) role;
        String sql = "insert into admin (name,password,level) values(?,?,?)";
        Connection conn = JDBCUtil.getConnect("myshop");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getPassword());
            pstmt.setInt(3, admin.getLevel());
            pstmt.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }

    public boolean check(Role role) {
        Admin admin = (Admin) role;
        String sql = "select * from admin where name=?";
        Connection conn = JDBCUtil.getConnect("myshop");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin.getName());
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }


    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        String sql = "select * from admin";
        Connection conn = JDBCUtil.getConnect("myshop");
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    int level = resultSet.getInt("level");

                    roles.add(new Admin(id, name, password, level));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role findById(int id) {
        return null;
    }
}
