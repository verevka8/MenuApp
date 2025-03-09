package com.example.test.ui.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.data.model.Dish;
import com.example.test.data.model.Order;
import com.example.test.data.model.SessionDish;
import com.example.test.data.repository.DataCallback;
import com.example.test.data.repository.SessionRepository;

import java.util.ArrayList;
import java.util.List;

public class TablesSessionViewModel extends ViewModel {
    private final MutableLiveData<SessionDish> dishList = new MutableLiveData<>(new SessionDish());
    private List<Dish> menu = new ArrayList<>();

    private final SessionRepository sessionRepository;

    public TablesSessionViewModel(String sessionId){
        sessionRepository = new SessionRepository(sessionId,this::sessionUpdates);
        InitializeData();
    }

    public LiveData<SessionDish> getDishes() {
        return dishList;
    }

    public ArrayList<Dish> getMenu(){
        return new ArrayList<>(menu);
    }

    public void AddDish(Dish dish){
        Order newOrder = new Order(dish.getId(),dish.getUser());
        sessionRepository.sendOrder(newOrder); // преобразуем в более легковестный обьект TODO: мб сделать на уровне репозитория
    }

    private void sessionUpdates(Dish dish) {
        SessionDish currentDishes = dishList.getValue();
        currentDishes.addDish(dish);
        dishList.postValue(currentDishes);
    }

    private void getAllSessionDishes(){
        sessionRepository.getSessionDishes(new DataCallback<SessionDish>() {
            @Override
            public void onReceiveData(@NonNull SessionDish data) {
                dishList.postValue(data);
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO: обработка
            }
        });
    }

    private void InitializeData(){
        sessionRepository.getMenu(new DataCallback<List<Dish>>() {

            @Override
            public void onReceiveData(@NonNull List<Dish> data) {
                menu = data;
                getAllSessionDishes();
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO: обработка ошибок
            }
        });
    }
}
