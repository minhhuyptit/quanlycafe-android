package Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Classes.OrderDetail;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewItemOrderdetailBinding;

public class OrderDetailRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<OrderDetail> orderDetails;

    public OrderDetailRecyclerViewAdapter(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerviewItemOrderdetailBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.recyclerview_item_orderdetail, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RecyclerviewItemOrderdetailBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        String name = orderDetails.get(position).name_product;
        binding.txtTenMon.setText(name);
        String quantity = " x "     +orderDetails.get(position).quantity;
        binding.txtSoLuong.setText(quantity);
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }
}
