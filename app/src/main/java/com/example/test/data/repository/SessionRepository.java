package com.example.test.data.repository;

import androidx.annotation.NonNull;

import com.example.test.data.mapper.DishMapper;
import com.example.test.data.model.Dish;
import com.example.test.data.model.Order;

import com.example.test.data.model.SessionDish;
import com.example.test.data.model.SessionOrders;
import com.example.test.data.network.restApi.ApiRequests;
import com.example.test.data.network.restApi.RetrofitService;
import com.example.test.data.network.webSocketClient.WebSocketClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionRepository {
    private String sessionId;

    private WebSocketClient client;
    private final ApiRequests apiRequests;

    private Gson gson = new Gson();

    private DishMapper dishMapper;
    private final DishCallback callback;

    public SessionRepository(String sessionId, DishCallback callback){
        this.sessionId = sessionId;
        this.callback = callback;
        apiRequests = RetrofitService.getInstance().getApiService();
        client = new WebSocketClient(this::onMessageReceived);
        client.connectToSession(sessionId);
    }

    public void sendOrder(Order order){
        String json = gson.toJson(order,Order.class);
        client.sendMessage(json,sessionId,"/app/addFood");
    }

    public void getSessionDishes(DataCallback<SessionDish> callback){
        Call<SessionOrders> call = apiRequests.getRequest(sessionId);
        call.enqueue(new Callback<SessionOrders>() {
            @Override
            public void onResponse(@NonNull Call<SessionOrders> call, @NonNull Response<SessionOrders> response) {
                if (response.isSuccessful() && response.body() != null){
                    callback.onReceiveData(dishMapper.convertOrdersToDish(response.body())); //TODO: обработать исключение
                }else{
                    callback.onFailure(new Exception("Ошибка получения данных" + response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<SessionOrders> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void getMenu(DataCallback<List<Dish>> callback){
        Call<List<Dish>> call = apiRequests.getRequest();
        call.enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(@NonNull Call<List<Dish>> call, @NonNull Response<List<Dish>> response) {
                if (response.isSuccessful() && response.body() != null){
                    callback.onReceiveData(response.body());
                    dishMapper = new DishMapper(response.body());
                }else{
                    callback.onFailure(new Exception("Ошибка получения данных" + response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Dish>> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void onMessageReceived(String message) {
        Order order = gson.fromJson(message, Order.class);
        callback.onDishReceived(dishMapper.convertOrderToDish(order)); // преобразуем в более информативный обьект
    }

    public interface DishCallback {
         void onDishReceived(Dish dish);
    }
}
