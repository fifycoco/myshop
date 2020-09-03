package entity;

public class Customer extends Role {
    private String phone;
    private int money;

    public Customer() {
    }

    public Customer(String name, String password, String phone, int money) {
        super(name, password);
        this.phone = phone;
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    // input tostring...
    @Override
    public String toString() {
        return "Customer{" +
                "money=" + money +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
