package com.twu.biblioteca.model;

public class User {
    private  String userName;
    private  String name;
    private  String password;
    private  String phone;
    private  String email;

    public User(String name, String userName, String password, String email, String phone) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'';

    }
}
