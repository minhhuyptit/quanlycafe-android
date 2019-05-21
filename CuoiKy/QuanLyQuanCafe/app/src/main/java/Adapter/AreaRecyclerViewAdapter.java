package Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Classes.Area;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewItemAreaBinding;

public class AreaRecyclerViewAdapter extends RecyclerView.Adapter{

    private Callback callback;

    public interface Callback {
        void onSelectArea(int area_id, String area_name);
    }

    private List<Area> areas;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public AreaRecyclerViewAdapter(List<Area> Areas, Callback callback) {
        this.areas = Areas;
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        RecyclerviewItemAreaBinding binding = DataBindingUtil.inflate(inflater, R.layout.recyclerview_item_area, parent, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final RecyclerviewItemAreaBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        binding.txtAreaName.setText(areas.get(position).name);
        binding.btnSelectArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSelectArea(areas.get(holder.getAdapterPosition()).id, areas.get(holder.getAdapterPosition()).name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }
}
