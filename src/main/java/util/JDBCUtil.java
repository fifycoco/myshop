package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    private static String url = "jdbc:mysql://localhost:3308/myshop?characterEncoding=UTF8";
    private static String user = "root";
    private static String password = "s3291923";


    static {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnect() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;


    }

    public static Connection getConnect(String dbName) {

        url = "jdbc:mysql://localhost:3308/" + dbName + "?characterEncoding=UTF8";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
