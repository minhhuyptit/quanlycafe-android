package Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Classes.Product;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewIemProductBinding;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Product> products;
    private Callback callback;

    public interface Callback {
        void onMoreClick(String id);

        void onLessClick(String id);
    }

    public ProductRecyclerViewAdapter(Callback callback, List<Product> products) {
        this.products = products;
        this.callback = callback;
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
        RecyclerviewIemProductBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.recyclerview_iem_product, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final RecyclerviewIemProductBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        final Product p = products.get(position);
        binding.txtProductName.setText(p.name);
        binding.txtPrice.setText(String.valueOf(p.price));
        binding.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onMoreClick(p.id);
            }
        });

        binding.btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onLessClick(p.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
