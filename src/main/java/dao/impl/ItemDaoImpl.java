package dao.impl;

import entity.Item;
import dao.ItemDao;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemDaoImpl implements ItemDao<Item> {

    @Override
    public boolean update(Item item) {
        Connection conn = JDBCUtil.getConnect();
        String sql = "update items set qty=? where id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, item.getQty());
            pstmt.setInt(2, item.getId());
            pstmt.execute();
            return true;


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
    public boolean add(Item item) {
        Connection conn = JDBCUtil.getConnect();
        String sql = "insert into items (name,price,qty,create_date,info) values(?,?,?,?,?)";

        Date sqlDate = item.getCreateDate();
        if (sqlDate == null) {
            sqlDate = new Date(System.currentTimeMillis());
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setFloat(2, item.getPrice());
            pstmt.setInt(3, item.getQty());
            pstmt.setDate(4, sqlDate);
            pstmt.setString(5, item.getInfo());

            pstmt.execute();
            return true;


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
    public boolean check(Item item) {
        String sql = "select * from items where name=?";
        Connection conn = JDBCUtil.getConnect();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
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
    public List<Item> findAll() {

        List<Item> items = new ArrayList<Item>();
        String sql = "select * from items";
        Connection conn = JDBCUtil.getConnect();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");
                int qty = resultSet.getInt("qty");
                Date date = resultSet.getDate("create_date");
                String text = resultSet.getString("info");
                items.add(new Item(id, name, price, qty, date, text));
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
        return items;
    }

    @Override
    public Item findById(int id) {
        String sql = "select * from items where id=?";
        Connection conn = JDBCUtil.getConnect();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                float price = result.getFloat("price");
                int qty = result.getInt("qty");
                Date date = result.getDate("create_date");
                String text = result.getString("info");
                return new Item(id, name, price, qty, date, text);
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

        return null;
    }


}
