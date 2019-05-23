package Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.security.auth.callback.Callback;

import Classes.Table;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewItemTableBinding;

public class TableRecyclerViewAdapter extends RecyclerView.Adapter implements Callback {
    private List<Table> tables;
    private Callback callback;

    public interface Callback {
        void onSelectTable(int table_id, String table_name);
    }

    public TableRecyclerViewAdapter(List<Table> tables, Callback callback) {
        this.callback = callback;
        this.tables = tables;
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

        RecyclerviewItemTableBinding binding = DataBindingUtil.inflate(inflater, R.layout.recyclerview_item_table, parent, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final RecyclerviewItemTableBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        binding.txtAreaName.setText(tables.get(position).name);
        if (tables.get(position).status == 1) {
            binding.btnSelectArea.setBackgroundResource(R.drawable.recyclerview_item_table_used);
        }
        binding.btnSelectArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyển menu thức ăn
                callback.onSelectTable(tables.get(holder.getAdapterPosition()).id, tables.get(holder.getAdapterPosition()).name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }
}
