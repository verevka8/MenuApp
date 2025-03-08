package com.example.test.ui.recycler_view_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.data.model.Dish;
import com.example.test.ui.callbacks.AddedDishCallback;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<Dish> menu;
    private AddedDishCallback callback;

    public MenuAdapter(List<Dish> menu, AddedDishCallback callback) {
        this.menu = menu;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuAdapter.MenuViewHolder(view,callback);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Dish dish = menu.get(position);
        holder.bind(dish);
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }


    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDishName;
        private ImageView ivDishImage;
        private TextView tvDishPrice;
        private ImageButton btnAddDish;
        private AddedDishCallback callback;

        public MenuViewHolder(@NonNull View itemView, AddedDishCallback callback) {
            super(itemView);
            tvDishName = itemView.findViewById(R.id.tvDishName);
            tvDishPrice = itemView.findViewById(R.id.tvDishPrice);
            ivDishImage = itemView.findViewById(R.id.ivDishImage);
            btnAddDish = itemView.findViewById(R.id.btnAddDish);
            this.callback = callback;
        }

        public void bind(Dish dish) {
            tvDishName.setText(dish.getName());
            tvDishPrice.setText(MessageFormat.format("{0} руб.", dish.getPrice()));
            Picasso.get().load(dish.getUrlOfDishImage()).into(ivDishImage);
            btnAddDish.setOnClickListener(view -> {
                callback.AddDish(dish);
            });
        }
    }
}
