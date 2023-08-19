package com.zjj.Beans;

public class User {
    private String name;
    private String password;
    private int id;

    public User(){}
    public  User(String name,String password){
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("{name:%s;password:%s}",name,password);
    }
}
