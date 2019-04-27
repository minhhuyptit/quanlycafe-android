package xyz.khang.baitap_06042019;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.khang.baitap_06042019.databinding.RecyclerviewItemBinding;

public class RecyclerViewAdapter extends RecyclerView.Adapter {


    private class MyViewHolder extends RecyclerView.ViewHolder {
        private MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        RecyclerviewItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.recyclerview_item, parent, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RecyclerviewItemBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        Film film = Films.list.get(position);
        binding.txtName.setText(film.name);
        binding.txtSub.setText(film.vietSub);
        binding.rtbRate.setRating(film.rate);
    }

    @Override
    public int getItemCount() {
        return Films.list.size();
    }
}
