package com.example.test.data.network.restApi;

import com.example.test.data.model.Dish;
import com.example.test.data.model.SessionOrders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRequests {

    @GET("orders/{sessionId}")
    Call<SessionOrders> getRequest(@Path("sessionId") String sessionId);

    @GET("menu")
    Call<List<Dish>> getRequest();
}
