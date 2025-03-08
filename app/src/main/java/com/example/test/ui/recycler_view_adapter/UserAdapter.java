package com.example.test.ui.recycler_view_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.data.model.Dish;
import com.example.test.data.model.Order;
import com.example.test.data.model.SessionDish;
import com.example.test.data.model.SessionOrders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<Map.Entry<String,List<Dish>>> usersOrders;

    public UserAdapter(SessionDish orders){
        usersOrders = new ArrayList<>(orders.getAllDishes().entrySet());
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(usersOrders.get(position));
    }

    @Override
    public int getItemCount() {
        return usersOrders.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
        }

        public void bind(Map.Entry<String,List<Dish>> userOrder) {
            tvUserName.setText(userOrder.getKey());

        }
    }
}
