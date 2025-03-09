package com.example.test.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;
import com.example.test.data.model.Dish;
import com.example.test.databinding.FragmentDishDetailsBinding;
import com.example.test.databinding.FragmentMenuBinding;
import com.example.test.ui.callbacks.DishClickCallback;
import com.example.test.ui.recycler_view_adapter.DishCompositionAdapter;
import com.example.test.ui.recycler_view_adapter.MenuAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

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

        binding.tvDishName.setText(dish.getName());
        binding.tvDishDescription.setText("Описание");
        binding.tvDishPortion.setText("208г");
        binding.tvDishEnergy.setText("534 ккал, 2239 кДж");
        binding.tvDishFat.setText("26 г (37%)");
        binding.tvDishCarbs.setText("44 г (17%)");
        binding.tvDishProtein.setText("31 г (61%)");
        binding.tvDishPrice.setText(MessageFormat.format("{0} руб.", dish.getPrice()));

        binding.rvDishComposition.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DishCompositionAdapter(List.of("Огурцы","Соус","Огурцы","Соус","Огурцы","Соус"));
        binding.rvDishComposition.setAdapter(adapter);

        Picasso.get().load(dish.getUrlOfDishImage()).into(binding.ivDishImage);

        binding.btnAdd.setOnClickListener(view -> {
            callback.dishClicked(dish);
            dismiss();
        });
        return binding.getRoot();
    }
}