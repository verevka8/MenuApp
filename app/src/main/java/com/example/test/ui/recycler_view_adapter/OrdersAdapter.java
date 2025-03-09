package com.example.test.ui.recycler_view_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.data.model.Dish;
import com.example.test.data.model.Order;
import com.example.test.ui.callbacks.DishClickCallback;
import com.example.test.ui.fragments.DishDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.IdentityHashMap;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private List<Dish> dishes;
    private DishClickCallback callback;
    public OrdersAdapter(List<Dish> dishes, DishClickCallback callback) {
        this.dishes = dishes;
        this.callback = callback;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dish, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.bind(dish,callback);
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public void updateRecyclerView(List<Dish> newOrderList){
        int countOfOrdersBeforeUpdate = dishes.size();
        dishes.addAll(newOrderList);
        notifyItemRangeInserted(countOfOrdersBeforeUpdate ,newOrderList.size() - countOfOrdersBeforeUpdate);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDishName;
        private ImageView ivDishImage;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDishName = itemView.findViewById(R.id.tvDishName);
            ivDishImage = itemView.findViewById(R.id.ivDishImage);
        }

        public void bind(Dish dish, DishClickCallback callback) {
            tvDishName.setText(dish.getName());
            Picasso.get().load(dish.getUrlOfDishImage()).into(ivDishImage);
            ivDishImage.setOnClickListener(view -> {
                callback.dishClicked(dish);
            });
        }
    }
}
