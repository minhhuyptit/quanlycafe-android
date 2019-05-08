package xyz.khang.baitapwebservice;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.khang.baitapwebservice.databinding.RecyclerviewItemBinding;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Object> objectList;

    public RecyclerViewAdapter(List<Object> objectList) {
        this.objectList = objectList;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        RecyclerviewItemBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.recyclerview_item, viewGroup, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        try {
            final RecyclerviewItemBinding binding = DataBindingUtil.findBinding(viewHolder.itemView);
            assert binding != null;
            binding.txtCode.setText(objectList.get(i).getValue());
            binding.txtColor.setText(objectList.get(i).getColor());
            binding.background.setBackgroundColor(Color.parseColor(objectList.get(i).getValue()));
        } catch (Exception e) {
            Log.d("error",e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
