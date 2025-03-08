package com.example.test.data.model;


public class Order {
    private String idOfFood;
    private String user;

    public Order(String idOfFood, String dish) {
        this.idOfFood = idOfFood;
        this.user = dish;
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
