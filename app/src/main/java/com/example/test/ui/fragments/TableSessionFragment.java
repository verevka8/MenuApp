package com.example.test.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.data.model.Dish;
import com.example.test.ui.recycler_view_adapter.UserAdapter;
import com.example.test.ui.viewmodel.TablesSessionViewModel;
import com.example.test.databinding.FragmentTableSessionBinding;

public class TableSessionFragment extends Fragment {

    private FragmentTableSessionBinding binding;
    private UserAdapter userAdapter;
    private TablesSessionViewModel viewModel;
    private static final String SESSION_ID = "sessionId";
    private static final String USERNAME = "username";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getArguments() == null){
            throw new IllegalArgumentException();
        }
        viewModel = new TablesSessionViewModel(getArguments().getString(SESSION_ID),getArguments().getString(USERNAME));
        binding = FragmentTableSessionBinding.inflate(inflater,container,false);

        binding.rvUsers.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        userAdapter = new UserAdapter(viewModel.getDishes().getValue(),this::dishClicked);


        binding.rvUsers.setAdapter(userAdapter);

        binding.btnSelectDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuFragment fragment = new MenuFragment(viewModel);
                fragment.show(getChildFragmentManager(),fragment.getTag());
            }
        });

        viewModel.getDishes().observe(getViewLifecycleOwner(), sessionDish -> {
            userAdapter.updateRecyclerView(sessionDish);
        });


        return binding.getRoot();
    }

    private void dishClicked(Dish dish){
        DishDetailsFragment fragment = new DishDetailsFragment(dish,viewModel::AddDish);
        fragment.show(getChildFragmentManager(),fragment.getTag());
    }
}
