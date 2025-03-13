package com.example.test.ui.recycler_view_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test.R;

import java.util.List;

public class DishCompositionAdapter extends RecyclerView.Adapter<DishCompositionAdapter.DishCompositionHolder> {

    private final List<String> composition;
    public DishCompositionAdapter(List<String> composition){
        this.composition = composition;
    }

    @NonNull
    @Override
    public DishCompositionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new DishCompositionAdapter.DishCompositionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishCompositionHolder holder, int position) {
        holder.bind(composition.get(position));
    }

    @Override
    public int getItemCount() {
        return composition.size();
    }

    public static class DishCompositionHolder extends RecyclerView.ViewHolder {
        private final TextView tvIngredient;
        public DishCompositionHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredient = itemView.findViewById(R.id.tvIngredient);
        }

        public void bind(String text) {
            tvIngredient.setText(text);
        }
    }
}
