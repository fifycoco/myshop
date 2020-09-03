package entity;

public class Admin extends Role {

    private int level;

    public Admin() {

    }

    public Admin(String name, String password, int level) {
        super(name, password);
        this.level = level;
    }

    public Admin(int id, String name, String password, int level) {
        super(name, password);
        this.id = id;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "level=" + level +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
