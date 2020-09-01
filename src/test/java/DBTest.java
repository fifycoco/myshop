import org.junit.Test;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTest {

    @Test
    public void test(){

        Connection connection= JDBCUtil.getConnect();

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
