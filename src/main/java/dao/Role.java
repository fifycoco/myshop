package dao;

public abstract class Role {
    protected int id;
    protected String name;
    protected String password;

    public Role(){}

    // ALT+INS
    public Role(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
