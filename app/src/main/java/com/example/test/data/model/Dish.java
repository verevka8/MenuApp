package com.example.test.data.model;

import java.util.List;

public class Dish {

    private String idDishes;
    private String dishes;
    private String description;
    private List<String> dishComposition;
    private String user;
    private int weight;
    private int fats;
    private int carbohydrates;
    private int proteins;
    private int calories;
    private int price;

    public Dish(String idDishes, String dishes, String description, List<String> dishComposition, int weight, int fats, int carbohydrates, int proteins, int calories, int price) {
        this.idDishes = idDishes;
        this.dishes = dishes;
        this.description = description;
        this.dishComposition = dishComposition;
        this.weight = weight;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.calories = calories;
        this.price = price;
    }

    public Dish(Dish item){
        this.idDishes = item.idDishes;
        this.dishes = item.dishes;
        this.description = item.description;
        this.dishComposition = item.dishComposition;
        this.weight = item.weight;
        this.fats = item.fats;
        this.carbohydrates = item.carbohydrates;
        this.proteins = item.proteins;
        this.calories = item.calories;
        this.price = item.price;
    }


    public String getIdDishes() {
        return idDishes;
    }

    public void setIdDishes(String idDishes) {
        this.idDishes = idDishes;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDishComposition() {
        return dishComposition;
    }

    public void setDishComposition(List<String> dishComposition) {
        this.dishComposition = dishComposition;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "idDishes='" + idDishes + '\'' +
                ", dishes='" + dishes + '\'' +
                ", description='" + description + '\'' +
                ", dishComposition=" + dishComposition +
                ", weight=" + weight +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                ", proteins=" + proteins +
                ", calories=" + calories +
                '}';
    }
}