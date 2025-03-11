package com.example.test.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.data.model.Dish;
import com.example.test.databinding.FragmentMenuBinding;
import com.example.test.ui.recycler_view_adapter.MenuAdapter;
import com.example.test.ui.viewmodel.TablesSessionViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class MenuFragment extends BottomSheetDialogFragment {

    private FragmentMenuBinding binding;
    private MenuAdapter menuAdapter;
    private TablesSessionViewModel viewModel;

    public MenuFragment(TablesSessionViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMenuBinding.inflate(inflater,container,false);
        binding.rvMenuItems.setLayoutManager(new GridLayoutManager(getContext(),2));
        menuAdapter = new MenuAdapter(viewModel.getMenu(),this::AddDish, this::displayDishDetails);
        binding.rvMenuItems.setAdapter(menuAdapter);

        return binding.getRoot();
    }

    public void AddDish(Dish dish){
        viewModel.AddDish(dish);
        dismiss();
    }
    public void displayDishDetails(Dish dish){
        DishDetailsFragment fragment = new DishDetailsFragment(dish,this::AddDish);
        fragment.show(getChildFragmentManager(), fragment.getTag());
    }
}