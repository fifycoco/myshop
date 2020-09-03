package view;


import dao.impl.CustomerDaoImpl;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import entity.*;

import java.util.List;
import java.util.Scanner;

public class OrderPage {

    private Role role;

    public OrderPage(Role role) {
        this.role = role;
        if (role instanceof Customer) {
            customerLayout(role);
        } else if (role instanceof Admin) {
            adminLayout();
        }
    }

    /**
     * 顧客介面
     */
    public void customerLayout(Role role) {
        showCustomerOrders(role);
        Scanner scanner = new Scanner(System.in);
        System.out.println(":(-1: 離開檢視)");
        scanner.next();
    }

    /**
     * 管理者介面
     */
    public void adminLayout() {
        showAllOrders();
        Scanner scanner = new Scanner(System.in);
        System.out.println(":(-1: 離開檢視)");
        scanner.next();
    }

    public Customer findOrderByCustomer(int customerId) {
        return (Customer) new CustomerDaoImpl().findById(customerId);
    }

    public Item findOrderByItem(int itemId) {
        return new ItemDaoImpl().findById(itemId);
    }


    public void showCustomerOrders(Role role) {

        Customer customer = (Customer) role;
        List<Order> orders = null;
        try {
            //取得目前品項
            orders = new OrderDaoImpl().findByCustomerId(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int count = 0;
        System.out.printf("[%s 訂單頁面]=====================================================================================\n",
                customer.getName());
        System.out.println("NO.\t\t訂單編號\t\t訂購日期\t\t商品名稱\t\t訂購單價\t\t訂購數量\t\t訂單金額");
        System.out.println("----------------------------------------------------------------------------------------------------");

        for (Order order : orders) {
            count++;
            Item item = findOrderByItem(order.getItemId());
//            String orderInfo = String.format("%3d 訂單編號:%3d 日期:%s  商品:%s 數量:%d 單價:%.2f 總金額:%.2f", count, order.getId(),
//                    order.getOrderDate(), item.getName(), order.getAmount(), item.getPrice(), order.getAmount() * item.getPrice());
            String orderInfo = String.format("%3d %8d %-20s %-20s %10.2f %10d %20.2f", count, order.getId(),
                    order.getOrderDate(), item.getName(), item.getPrice(), order.getAmount(), order.getAmount() * item.getPrice());
            System.out.println(orderInfo);
        }
        System.out.println("====================================================================================================");
    }


    public void showAllOrders() {

        //取得目前品項
        List<Order> orders = new OrderDaoImpl().findAll();
        int count = 0;
        System.out.println("[訂單頁面]=====================================================================================");
        System.out.println("訂單編號\t\t訂購日期\t\t訂購人\t\t訂購數量\t\t訂購單價\t\t訂單金額");
        System.out.println("----------------------------------------------------------------------------------------------");

        for (Order order : orders) {
            count++;
            Item item = findOrderByItem(order.getItemId());
            Customer customer = findOrderByCustomer(order.getCustomerId());

            if (item == null || customer == null) {
                continue;
            }


            String orderInfo = String.format("%3d 日期:%s 訂購人:%s  商品:%s 數量:%d 單價:%.2f 總金額:%.2f", order.getId(),
                    order.getOrderDate(), customer.getName(), item.getName(), order.getAmount(), item.getPrice(), order.getAmount() * item.getPrice());
            System.out.println(orderInfo);
        }
        System.out.println("==============================================================================================");

    }
}
