package com.example.test.ui.recycler_view_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.data.model.Dish;
import com.example.test.data.model.Order;
import com.example.test.data.model.SessionDish;
import com.example.test.data.model.SessionOrders;
import com.example.test.ui.callbacks.DishClickCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<Map.Entry<String,List<Dish>>> usersOrders;
    private DishClickCallback callback;

    public UserAdapter(SessionDish orders, DishClickCallback callback){
        usersOrders = copy(orders);
        this.callback = callback;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_dishes, parent, false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(usersOrders.get(position),callback);
    }

    @Override
    public int getItemCount() {
        return usersOrders.size();
    }

    public void updateRecyclerView(SessionDish orders){
        usersOrders = copy(orders); //TODO: подумать над diffUtils
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private RecyclerView rvDishes;
        private OrdersAdapter adapter;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            rvDishes = itemView.findViewById(R.id.rvDishes);
            rvDishes.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        }

        public void bind(Map.Entry<String,List<Dish>> userDish,DishClickCallback callback) {
            tvUserName.setText(userDish.getKey());
            adapter = new OrdersAdapter(userDish.getValue(),callback);
            rvDishes.setAdapter(adapter);
        }
    }

    private List<Map.Entry<String,List<Dish>>> copy(SessionDish sessionDish){
        List<Map.Entry<String,List<Dish>>> list = new ArrayList<>(sessionDish.getAllDishes().entrySet());
        for (int i = 0; i < list.size();i++){
            List<Dish> copy = list.get(i).getValue();
            list.get(i).setValue(deepCopyList(copy)); // делаем глубокую копию, но ключи не копируем
        }
        return list;
    }

    private List<Dish> deepCopyList(List<Dish> list){
        List<Dish> newList = new ArrayList<>();
        for(Dish item: list){
            newList.add(new Dish(item));
        }
        return newList;
    }
}
