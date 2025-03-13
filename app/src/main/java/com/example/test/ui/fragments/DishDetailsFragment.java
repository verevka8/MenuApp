package com.example.test.ui.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.data.model.Dish;
import com.example.test.ui.callbacks.DishClickCallback;
import com.example.test.ui.recycler_view_adapter.DishCompositionAdapter;
import com.example.test.databinding.FragmentDishDetailsBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;

public class DishDetailsFragment extends BottomSheetDialogFragment {

    private FragmentDishDetailsBinding binding;
    private DishCompositionAdapter adapter;
    private Dish dish;
    private DishClickCallback callback;
    public DishDetailsFragment(Dish dish, DishClickCallback callback) {
        this.dish = dish;
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDishDetailsBinding.inflate(inflater,container,false);

        binding.tvDishName.setText(dish.getDishes());
        binding.tvDishDescription.setText(dish.getDescription());
        binding.tvDishPortion.setText(dish.getWeight() + " г");
        binding.tvDishEnergy.setText(dish.getCalories() + " ккал");
        binding.tvDishFat.setText(dish.getFats() + " г");
        binding.tvDishCarbs.setText(dish.getCarbohydrates() + " г");
        binding.tvDishProtein.setText(dish.getProteins() + " г");
        binding.tvDishPrice.setText(MessageFormat.format("{0} руб.", dish.getPrice()));

        binding.rvDishComposition.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DishCompositionAdapter(dish.getDishComposition());
        binding.rvDishComposition.setAdapter(adapter);

        Picasso.get().load("https://storage.yandexcloud.net/menu-app-image-backet/" + dish.getIdDishes() + ".jpg").into(binding.ivDishImage);

        binding.btnAdd.setOnClickListener(view -> {
            callback.dishClicked(dish);
            dismiss();
        });
        return binding.getRoot();
    }
}