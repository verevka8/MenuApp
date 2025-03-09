package com.example.test.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Dish {
    private String id;
    private String user;
    private String name;
    private String urlOfDishImage;
    private int price;


    public Dish(String id, String user, String name, String urlOfDishImage, int price) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.urlOfDishImage = urlOfDishImage;
        this.price = price;
    }

    public Dish(Dish dish){
        this.id = dish.id;
        this.user = dish.user;
        this.name = dish.name;
        this.urlOfDishImage = dish.urlOfDishImage;
        this.price = dish.price;
    }

    public String getName() {
        return name;
    }

    public String getUrlOfDishImage() {
        return urlOfDishImage;
    }

    public int getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
