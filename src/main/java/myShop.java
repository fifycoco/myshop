import util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class myShop {
    public static void main(String[] args) {
        Connection connection=JDBCUtil.getConnect();

        System.out.println(connection);


        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



    }
}
