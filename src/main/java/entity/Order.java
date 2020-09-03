package entity;

import java.sql.Date;

/***
 * 訂單類別
 */
public class Order {
    private int id;
    private Date orderDate;
    private int itemId;
    private int customerId;
    private int amount;

    public Order() {
    }

    public Order(Date orderDate, int itemId, int customerId, int amount) {
        this.orderDate = orderDate;
        this.itemId = itemId;
        this.customerId = customerId;
        this.amount = amount;
    }

    public Order(int id, Date orderDate, int itemId, int customerId, int amount) {
        this(orderDate, itemId, customerId, amount);
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", itemId=" + itemId +
                ", customerId=" + customerId +
                '}';
    }
}
