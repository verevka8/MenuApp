package com.example.test.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;
import com.example.test.databinding.FragmentStartBinding;

public class StartFragment extends Fragment {
    private FragmentStartBinding binding;

    public StartFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater,container,false);

        binding.createSession.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("sessionId", String.valueOf(binding.tvIdSession.getText()));
            bundle.putString("username",String.valueOf(binding.tvUsername.getText()));
            Navigation.findNavController(requireView()).navigate(R.id.action_startFragment_to_tableSessionFragment,bundle);
        });

        return binding.getRoot();
    }
}