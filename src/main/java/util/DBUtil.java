package util;

import java.sql.*;

public class DBUtil {
    private Connection connection;
    private Statement statement;

    private String dbName;
    private String passWord;
    private String userName;



    public DBUtil(String dbName, String userName, String passWord) {
        this.dbName = dbName;
        this.passWord = passWord;
        this.userName = userName;

        connection();
    }

    public void init() {
        //獲取連接
        String url = "jdbc:mysql://localhost:3306/" + dbName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, userName, passWord);
            statement = connection.createStatement();
            System.out.println("資料庫初始化成功");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //連接
    public void connection() {
        release();
        init();
    }

    public boolean isConnection() {
        if (connection != null) {
            return true;
        }
        return false;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }


    //查詢資料
    public ResultSet select(String sqlStr) {
        if (connection == null) {
            return null;
        }

        try {
            return statement.executeQuery(sqlStr);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    public int execute(String sqlStr) {
        //處理結果(sql語法)
        int result = -1;

        if (connection == null) {
            return result;
        }

        try {
            result = statement.executeUpdate(sqlStr);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (result > 0) {
            System.out.println("資料更新成功!");
        } else {
            System.out.println("刪除更新失敗!");
        }

        return result;
    }

    //釋放資源
    public void release() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("資料庫關閉成功!");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
