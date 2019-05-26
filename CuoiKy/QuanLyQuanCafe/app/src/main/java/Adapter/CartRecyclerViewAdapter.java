package Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Classes.Cart;
import Classes.Item;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.RecyclerviewItemCartBinding;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter {

    Cart cart;
    List<String> keys = new ArrayList<>();
    Callback callback;

    public interface Callback {
//        void onMoreCartClick(String product_id);

        void onLessCartClick(String product_id);
    }

    public CartRecyclerViewAdapter(Callback callback, Cart cart) {
        this.callback = callback;
        this.cart = cart;
        for (String s : cart.cartItem.keySet()) {
            keys.add(s);
        }
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
        RecyclerviewItemCartBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.recyclerview_item_cart, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RecyclerviewItemCartBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        final Item i = cart.cartItem.get(keys.get(position));
        binding.txtProductName.setText(i.product.name);

        String s = String.format("%,d", (int) (i.quantity * i.product.price));

        binding.txtPrice.setText(s);
        binding.txtQuantity.setText(String.valueOf(i.quantity));
        binding.btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onLessCartClick(i.product.id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cart.cartItem.size();
    }
}
