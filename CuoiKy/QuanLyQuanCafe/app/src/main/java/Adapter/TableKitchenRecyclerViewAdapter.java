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
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewItemTablekitchenBinding;

public class TableKitchenRecyclerViewAdapter extends RecyclerView.Adapter {

    private Callback callback;

    public interface Callback {
        void onSelectTable(int order_id);
    }

    private List<TableKitchen> tableKitchens;

    public TableKitchenRecyclerViewAdapter(Callback callback, List<TableKitchen> tableKitchens) {
        this.callback = callback;
        this.tableKitchens = tableKitchens;
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

        String line1 = "Id bàn:" + tableKitchens.get(position).idTable;
        binding.txtId.setText(line1);

        String line2 = "Giờ vào: " + tableKitchens.get(position).timeIn;
        binding.txtTimeIn.setText(line2);

        String line3 = "Nhân viên order: " + tableKitchens.get(position).idUserIn;
        binding.txtIdUser.setText(line3);
        binding.btnSelectTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("po", position+" " + tableKitchens.size());
                callback.onSelectTable(tableKitchens.get(position).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableKitchens.size();
    }
}
