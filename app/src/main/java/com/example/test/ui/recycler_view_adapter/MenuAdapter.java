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
import com.example.test.ui.callbacks.DishClickCallback;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<Dish> menu;
    private DishClickCallback addFromMenuCallback;
    private DishClickCallback displayDetailsCallback;

    public MenuAdapter(List<Dish> menu, DishClickCallback addFromMenuCallback, DishClickCallback displayDetailsCallback) {
        this.menu = menu;
        this.addFromMenuCallback = addFromMenuCallback;
        this.displayDetailsCallback = displayDetailsCallback;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Dish dish = menu.get(position);
        holder.bind(dish,addFromMenuCallback, displayDetailsCallback);
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

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDishName = itemView.findViewById(R.id.tvDishName);
            tvDishPrice = itemView.findViewById(R.id.tvDishPrice);
            ivDishImage = itemView.findViewById(R.id.ivDishImage);
            btnAddDish = itemView.findViewById(R.id.btnAddDish);
        }

        public void bind(Dish dish, DishClickCallback addFromMenuCallback, DishClickCallback displayDetailsCallback) {
            tvDishName.setText(dish.getName());
            tvDishPrice.setText(MessageFormat.format("{0} руб.", dish.getPrice()));
            Picasso.get().load(dish.getUrlOfDishImage()).into(ivDishImage);
            btnAddDish.setOnClickListener(view -> {
                addFromMenuCallback.dishClicked(dish);
            });
            ivDishImage.setOnClickListener(view -> {
                displayDetailsCallback.dishClicked(dish);
            });
        }
    }
}
