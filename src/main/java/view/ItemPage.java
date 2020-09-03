package view;

import dao.*;
import dao.impl.CustomerDaoImpl;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import entity.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/***
 * 商店頁面
 */
public class ItemPage {
    private Role role;

    public ItemPage(Role role) {
        this.role = role;
        if (role instanceof Customer) {
            customerLayout();
        } else if (role instanceof Admin) {
            adminLayout();
        }
    }

    /**
     * 商品介面
     */
    public void customerLayout() {
        Customer customer = (Customer) role;
        while (true) {
            int select = 0;
            int count = 0;
            Scanner scanner = new Scanner(System.in);
            List<Item> items = showItems();
            System.out.println("[購買商品]===============================================");

            System.out.print(customer.getName() + "(餘額:" + customer.getMoney() + "元)" +
                    "\n請問要購買哪個品項呢(-1:離開)?");
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("選項輸入錯誤，請重新輸入....");
                continue;
            }
            if (select == -1) {
                System.out.println("離開商品頁面.");
                break;
            }
            if (select < 1 || select > items.size()) {
                System.out.println("選項輸入錯誤，請重新輸入....");
                continue;
            }
            Item item = items.get(select - 1);

            if (item.getQty() <= 0) {
                System.out.println(item.getName() + " 商品已無庫存");
                continue;
            }

            System.out.print("請問要購買數量?");
            int amount = 0;
            try {
                amount = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("數量輸入錯誤，請重新輸入....");
                continue;
            }

            if (amount > item.getQty() || amount <= 0) {
                System.out.println("數量輸入錯誤，請重新輸入....");
                continue;
            }

            float total = item.getPrice() * amount;

            if (customer.getMoney() - total < 0) {
                System.out.println("金額不夠");
                continue;
            }

            //減去產品數量
            item.setQty(item.getQty() - amount);

            //進行購買更新庫存動作
            if (new ItemDaoImpl().update(item)) {
                //增加到訂單頁面
                new OrderDaoImpl().add(new Order(null, item.getId(),
                        customer.getId(), amount));
                //更新顧客資料
                customer.setMoney((int) (customer.getMoney() - total));
                //更新顧客資料
                new CustomerDaoImpl().update(customer);

                System.out.println("商品訂購成功:" + item.getName());
                return;
            }

            System.out.println("購買商品失敗..");
        }
    }


    /**
     * 回傳產品
     *
     * @return
     */
    public List<Item> showItems() {
        ItemDaoImpl itemDao = new ItemDaoImpl();
        //取得目前品項
        List<Item> items = itemDao.findAll();
        int count = 0;


        System.out.println("[產品頁面]===============================================");
        for (Item item : items) {
            count++;
            String itemInfo = String.format("%3d %s ==>價格:%.2f 產品數量:%d",
                    count, item.getName(), item.getPrice(), item.getQty());
            System.out.println(itemInfo);
        }
        System.out.println("========================================================");

        return items;
    }

    /**
     * 增加商品畫面
     */
    public void adminLayout() {
        Admin admin = (Admin) role;
        while (true) {
            showItems();
            System.out.println("[新增商品]===============================================");
            Scanner scanner = new Scanner(System.in);
            System.out.println("商品名稱:(-1:exit)");
            String name = scanner.nextLine();
            if (name.equals("-1")) {
                return;
            }

            Item item = null;
            try {
                System.out.println("商品價格:");
                float price = scanner.nextFloat();
                System.out.println("商品數量:");
                int qty = scanner.nextInt();
                System.out.println("商品備註:");
                String info = scanner.next();
                item = new Item(name, price, qty, null, info);

            } catch (InputMismatchException ex) {
                System.out.println("輸入資料錯誤，請重新輸入");
                continue;
            }

            ItemDao itemDao = new ItemDaoImpl();

            if (itemDao.check(item)) {
                System.out.println("商品重複，請重新輸入");
                continue;
            }

            itemDao.add(item);
            System.out.println("商品新增成功!");
        }
    }


}
