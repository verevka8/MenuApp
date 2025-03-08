package com.example.test.ui.recycler_view_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.data.model.Order;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private List<Order> orders;

    public OrdersAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
//        Order order = orders.getAllOrders().
//        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void updateRecyclerView(List<Order> newOrderList){
//        int countOfOrdersBeforeUpdate = getItemCount();
//        orders = new ArrayList<>(newOrderList);
//        notifyItemRangeInserted(countOfOrdersBeforeUpdate ,newOrderList.size() - countOfOrdersBeforeUpdate);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDishName;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDishName = itemView.findViewById(R.id.tvDishName);

        }

        public void bind(Order order) {
//            Picasso.get().load(dish.getUrlOfDishImage()).into(ivDishImage);
        }
    }
}
