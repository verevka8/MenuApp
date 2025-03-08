package com.example.test.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.data.model.SessionDish;
import com.example.test.databinding.FragmentTableSessionBinding;
import com.example.test.ui.recycler_view_adapter.UserAdapter;
import com.example.test.ui.viewmodel.TablesSessionViewModel;

public class TableSessionFragment extends Fragment {

    private FragmentTableSessionBinding binding;
    private UserAdapter userAdapter;
    private TablesSessionViewModel viewModel;
    private static final String SESSION_ID = "sessionId";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getArguments() == null){
            throw new IllegalArgumentException();
        }
        viewModel = new TablesSessionViewModel(getArguments().getString(SESSION_ID));
        binding = FragmentTableSessionBinding.inflate(inflater,container,false);

        binding.rvUsers.setLayoutManager(new LinearLayoutManager(getContext()));
        userAdapter = new UserAdapter(viewModel.getDishes().getValue());


        binding.rvUsers.setAdapter(userAdapter);

        binding.btnSelectDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuFragment fragment = new MenuFragment(viewModel);
                fragment.show(getChildFragmentManager(),fragment.getTag());
            }
        });

        viewModel.getDishes().observe(getViewLifecycleOwner(), sessionDish -> {
           // обновление рекуклера
        });


        return binding.getRoot();
    }
}
