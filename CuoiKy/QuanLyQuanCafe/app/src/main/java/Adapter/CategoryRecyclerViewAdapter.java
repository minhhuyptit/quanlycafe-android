package Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Classes.Category;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewItemCategoryBinding;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter {
    private Callback callback;
    private List<Category> categories;

    public interface Callback {
        void onCategoryClick(String category_id);
    }

    public CategoryRecyclerViewAdapter(Callback callback, List<Category> categories) {
        this.callback = callback;
        this.categories = categories;
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
        RecyclerviewItemCategoryBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.recyclerview_item_category, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final RecyclerviewItemCategoryBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        binding.txtCategoryName.setText(categories.get(position).name);
        binding.btnSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onCategoryClick(categories.get(holder.getAdapterPosition()).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
