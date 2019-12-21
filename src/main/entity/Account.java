package main.entity;

public class Account {
    private String ac_id;
    private String pwd;

    public Account() {
    }

    public Account(String ac_id, String pwd) {
        this.ac_id = ac_id;
        this.pwd = pwd;
    }

    public String getAc_id() {
        return ac_id;
    }

    public void setAc_id(String ac_id) {
        this.ac_id = ac_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
