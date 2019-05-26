package Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Classes.ProductDetailStatistic;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewItemStatisticdetailBinding;

public class StatisticDetailRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<ProductDetailStatistic> statisticDetails;

    public StatisticDetailRecyclerViewAdapter(List<ProductDetailStatistic> statisticDetails) {
        this.statisticDetails = statisticDetails;
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
        RecyclerviewItemStatisticdetailBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.recyclerview_item_statisticdetail, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RecyclerviewItemStatisticdetailBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        String name = statisticDetails.get(position).getName();
        binding.txtTenMon.setText(name);
        String quantity = " x "     +statisticDetails.get(position).getQuantity();
        binding.txtSoLuong.setText(quantity);
        String price = String.format("%,d", (int) (statisticDetails.get(position).getPrice()));
        binding.txtGia.setText(price);
    }

    @Override
    public int getItemCount() {
        return statisticDetails.size();
    }
}
