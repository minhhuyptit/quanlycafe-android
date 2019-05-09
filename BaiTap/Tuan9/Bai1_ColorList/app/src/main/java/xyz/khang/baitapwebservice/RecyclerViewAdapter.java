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

        final RecyclerviewItemBinding binding = DataBindingUtil.findBinding(viewHolder.itemView);
        assert binding != null;

        int color = Color.parseColor(formatValue(objectList.get(i).getValue()));
        int textColor = Color.rgb(255-Color.red(color),
                255-Color.green(color),
                255-Color.blue(color));


        binding.txtCode.setText(objectList.get(i).getValue());
        binding.txtCode.setTextColor(textColor);

        binding.txtColor.setText(objectList.get(i).getColor());
        binding.txtColor.setTextColor(textColor);

        try {
            binding.background.setBackgroundColor(color);
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }

    private String formatValue(String value){
        String str_result = "#";
        for (char c : value.toCharArray()) {
            if (!(c == '#')) {
                str_result = str_result.concat(String.valueOf(c));
                str_result = str_result.concat(String.valueOf(c));
            }
        }
        return str_result;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
