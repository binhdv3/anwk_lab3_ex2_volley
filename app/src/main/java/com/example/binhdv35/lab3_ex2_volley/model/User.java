package com.example.binhdv35.lab3_ex2_volley.model;

public class User {

    private String name, email, home, phone;

    public User(String name, String email, String home, String phone) {
        this.name = name;
        this.email = email;
        this.home = home;
        this.phone = phone;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
