package Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Classes.TableKitchen;
import Classes.TableOrder;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewItemTablekitchenBinding;

public class TableKitchenRecyclerViewAdapter extends RecyclerView.Adapter {

    private Callback callback;

    public interface Callback {
        void onSelectTable(int position);
    }

    private List<TableOrder> tableOrders;

    public TableKitchenRecyclerViewAdapter(Callback callback, List<TableOrder> tableOrders) {
        this.callback = callback;
        this.tableOrders = tableOrders;
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

        RecyclerviewItemTablekitchenBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.recyclerview_item_tablekitchen, parent, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final RecyclerviewItemTablekitchenBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;

        String line1 = "Tên bàn:" + tableOrders.get(position).getTable();
        binding.txtTable.setText(line1);

        String line2 = "Khu vực: " + tableOrders.get(position).getArea();
        binding.txtArea.setText(line2);

        String line3 = "Time Order: " + tableOrders.get(position).getTimein();
        binding.txtTime.setText(line3);

        String line4 = "Nhân viên order: " + tableOrders.get(position).getLoginname();
        binding.txtName.setText(line4);
        binding.btnSelectTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("po", position+" " + tableOrders.size());
                callback.onSelectTable(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableOrders.size();
    }
}
