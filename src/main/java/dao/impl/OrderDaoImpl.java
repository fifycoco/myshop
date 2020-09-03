package dao.impl;


import dao.OrderDao;
import entity.Order;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao<Order> {

    @Override
    public List findByCustomerId(int customerId) throws Exception {
        Connection conn = JDBCUtil.getConnect();

        List<Order> orders = new ArrayList<>();
        String sql = "select * from orders where customer_id=" + customerId;
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            Date date = resultSet.getDate("order_date");
            int itemId = resultSet.getInt("item_id");
            int amount = resultSet.getInt("amount");
            orders.add(new Order(id, date, itemId, customerId,amount));
        }

        conn.close();

        return orders;

    }

    @Override
    public List findByItemId(int itemId) throws Exception {
        Connection conn = JDBCUtil.getConnect();

        List<Order> orders = new ArrayList<>();
        String sql = "select * from orders where item_id=" + itemId;
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            Date date = resultSet.getDate("order_date");
            int customerId = resultSet.getInt("item_id");
            int amount = resultSet.getInt("amount");
            orders.add(new Order(id, date, itemId, customerId,amount));
        }

        conn.close();

        return orders;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Connection conn = JDBCUtil.getConnect();
        String sql = "insert into orders (order_date,item_id,customer_id,amount) " +
                "values(?,?,?,?)";

        Order order=(Order) object;
        Date sqlDate = order.getOrderDate();
        if (sqlDate == null) {
            sqlDate = new Date(System.currentTimeMillis());
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, sqlDate);
            pstmt.setInt(2, order.getItemId());
            pstmt.setInt(3, order.getCustomerId());
            pstmt.setInt(4, order.getAmount());
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
    public boolean check(Object item) {
        return false;
    }

    @Override
    public List<Order> findAll() {
        Connection conn = JDBCUtil.getConnect();

        List<Order> orders = new ArrayList<>();
        String sql = "select * from orders";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("order_date");
                int itemId = resultSet.getInt("item_id");
                int customerId = resultSet.getInt("customer_id");
                int amount = resultSet.getInt("amount");
                orders.add(new Order(id, date, itemId, customerId, amount));
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

        return orders;
    }

    @Override
    public Order findById(int id) {
        String sql = "select * from orders where id=?";
        Connection conn = JDBCUtil.getConnect();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Date date = resultSet.getDate("order_date");
                int itemId = resultSet.getInt("item_id");
                int customerId = resultSet.getInt("customer_id");
                int amount = resultSet.getInt("amount");
                return new Order(id, date, itemId, customerId, amount);
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