package dao.impl;

import dao.RoleDao;
import entity.Customer;
import entity.Role;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl  implements RoleDao {

    public Customer login(String name, String password) {
        String sqlStr = "select * from customers where name=? and password=?";
        Connection conn = JDBCUtil.getConnect("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            pstmt.setString(1, name);
            pstmt.setString(2,password);
            ResultSet result = pstmt.executeQuery();

            if(result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setPassword(result.getString("password"));
                customer.setPhone(result.getString("phone"));
                customer.setMoney(result.getInt("money"));
                return customer;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public boolean register(Role role) {
        // role 向下轉型
        Customer customer = (Customer)role;
        String sqlStr = "insert into customers(name, password, phone, money) values(?,?,?,?)";
        Connection conn = JDBCUtil.getConnect("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2,customer.getPassword());
            pstmt.setString(3,customer.getPhone());
            pstmt.setInt(4,customer.getMoney());
            pstmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }

    public boolean check(Role role) {
        Customer customer = (Customer) role;
        String sqlStr = "select * from customers where phone=?";
        Connection conn = JDBCUtil.getConnect("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            pstmt.setString(1, customer.getPhone());
                 ResultSet result = pstmt.executeQuery();

            if(result.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(Role role) {
        return false;
    }

    public List<Role> findAll() {
        return null;
    }

    public Role findById(int id) {
        return null;
    }
}
