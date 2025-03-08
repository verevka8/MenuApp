package com.example.test.data.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionDish {
    private Map<String, List<Dish>> allDishes;

    public SessionDish(Map<String, List<Dish>> allDishes) {
        this.allDishes = allDishes;
    }
    public SessionDish(){
        allDishes = new HashMap<>();
    }

    public Map<String, List<Dish>> getAllDishes() {
        return allDishes;
    }


    public void addDish(Dish dish){
        if (allDishes.containsKey(dish.getUser()))
        {
            allDishes.get(dish.getUser()).add(dish);
        }
        else{
            allDishes.put(dish.getUser(), new ArrayList<Dish>(){
                    {
                        add(dish);
                    }
                }
            );
        }
    }
}
