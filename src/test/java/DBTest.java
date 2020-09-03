import dao.impl.CustomerDaoImpl;
import entity.Customer;
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

    @Test
    public void test1(){
        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        // Login
        //Customer customer = customerDao.login("mary","mary");
        //System.out.println(customer);

        // Register
        //boolean bln = customerDao.register(new Customer("Kelly","123","0918552653",500));
        //System.out.println(bln);
    }
}
