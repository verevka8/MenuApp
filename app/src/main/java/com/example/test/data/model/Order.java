package com.example.test.data.model;


public class Order {
    private String idOfFood;
    private String user;

    public Order(String idOfFood, String user) {
        this.idOfFood = idOfFood;
        this.user = user;
    }

    public String getIdOfFood() {
        return idOfFood;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOfFood='" + idOfFood + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
