package com.example.test.data.mapper;

import com.example.test.data.model.Dish;
import com.example.test.data.model.Order;
import com.example.test.data.model.SessionDish;
import com.example.test.data.model.SessionOrders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishMapper {
    private final List<Dish> menu;

    public DishMapper(List<Dish> menu){
        this.menu = menu;
    }

    public SessionDish convertOrdersToDish(SessionOrders sessionOrders){
        Map<String,List<Order>> orders = sessionOrders.getAllOrders();
        Map<String,List<Dish>> dishes = new HashMap<>();

        for(Map.Entry<String, List<Order>> ordersOfUser: orders.entrySet()){
            ArrayList<Dish> dishesOfUser = new ArrayList<>();

            for (Order item: ordersOfUser.getValue()){
                Dish dish = findDishById(item.getIdOfFood());
                dish.setUser(ordersOfUser.getKey());
                dishesOfUser.add(dish);
            }
            dishes.put(ordersOfUser.getKey(),dishesOfUser);
        }
        return new SessionDish(dishes);
    }

    public Dish convertOrderToDish(Order order){
        Dish dish = findDishById(order.getIdOfFood());
        dish.setUser(order.getUser());
        return dish;
    }

    private Dish findDishById(String id){
        for (int i = 0; i < menu.size();i++){
            if (menu.get(i).getIdDishes().equals(id)){
                return menu.get(i);
            }
        }
        throw new IllegalArgumentException("Блюдо с таким айди не найдено"); //TODO: проработать исключение
    }
}
