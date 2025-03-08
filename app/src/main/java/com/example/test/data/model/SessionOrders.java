package com.example.test.data.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionOrders {

    private Map<String, List<Order>> allOrders = new HashMap<>();

    public SessionOrders(Map<String, List<Order>> allOrders) {
        this.allOrders = allOrders;
    }

    public Map<String, List<Order>> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(Map<String, List<Order>> allOrders) {
        this.allOrders = allOrders;
    }

    public void addOrder(Order newOrder){
        if(allOrders.containsKey(newOrder.getUser())){
            allOrders.get(newOrder.getUser()).add(newOrder);
        }else{
            List<Order> temp = new ArrayList<>();
            temp.add(newOrder);
            allOrders.put(newOrder.getUser(),temp);
        }
    }

    public void addOrders(SessionOrders orders){
        for (Map.Entry<String,List<Order>> entry: orders.allOrders.entrySet()){
            if (allOrders.containsKey(entry.getKey())){
                allOrders.get(entry.getKey()).addAll(entry.getValue()); //TODO: сделать логику избежания конфликтов версий
            }else{
                allOrders.put(entry.getKey(),entry.getValue());
            }
        }
    }
}
