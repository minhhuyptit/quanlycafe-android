package Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Adapter.CartRecyclerViewAdapter;
import Adapter.CategoryRecyclerViewAdapter;
import Adapter.ProductRecyclerViewAdapter;
import Api.CommonAPI;
import Api.MenuAPI;
import Classes.Cart;
import Classes.Category;
import Classes.Product;
import Classes.Table;
import Classes.User;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity
        implements MenuAPI.Callback, CategoryRecyclerViewAdapter.Callback, ProductRecyclerViewAdapter.Callback, CartRecyclerViewAdapter.Callback {

    ActivityMenuBinding binding;
    MenuAPI api;
    List<Category> categories;
    List<Product> products;
    Cart cart;
    boolean product_flag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);

        api = new MenuAPI(this, getApplicationContext());
        api.get_table_cart(Table.current_id);
        api.get_product();
        api.get_category();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRVCategory();
            }
        });

        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order();
            }
        });

        binding.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.put_check(String.valueOf(User.logged_id),
                        String.valueOf(Table.current_id));
            }
        });
    }

    @Override
    public void onGetTableCartResponse(String response) {
        try {
            Gson gson = new Gson();
            cart = gson.fromJson(response, Cart.class);
            setRVCart();
        } catch (Exception e) {
            cart = new Cart();
        }

    }

    @Override
    public void onGetCategoryResponse(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Category>>() {
            }.getType();
            categories = gson.fromJson(response, collectionType);
            setRVCategory();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetProductResponse(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Product>>() {
            }.getType();
            products = gson.fromJson(response, collectionType);
            product_flag = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPostOrder(String response) {
        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPutCheck(String response) {
        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryClick(String category_id) {
        if (product_flag) {
            setRVProduct(category_id);
        }
    }

    @Override
    public void onMoreClick(String id) {
        //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
        cart.more(findByID(id));
        setRVCart();
    }

    @Override
    public void onLessClick(String id) {
        //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
        cart.less(id);
        setRVCart();
    }

    @Override
    public void onMoreCartClick(String product_id) {
        cart.more(findByID(product_id));
        setRVCart();
    }

    @Override
    public void onLessCartClick(String product_id) {
        cart.less(product_id);
        setRVCart();
    }

    private void setRVProduct(String category_id) {
        RecyclerView recyclerView = binding.rvCategory;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        //CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(this, categories, products, 1);
        List<Product> temp = new ArrayList<>();
        for (Product p : products) {
            if (p.idCategory.equals(category_id)) {
                temp.add(p);
            }
        }
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(this, temp);
        recyclerView.setAdapter(adapter);
    }

    private void setRVCategory() {
        RecyclerView recyclerView = binding.rvCategory;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(this, categories);
        //ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(products);
        recyclerView.setAdapter(adapter);
    }

    private void setRVCart() {
        String s = String.format("%,d", (int) (cart.sum()));
        binding.txtOrdered.setText(s);
        RecyclerView recyclerView = binding.rvCart;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        CartRecyclerViewAdapter adapter = new CartRecyclerViewAdapter(this, cart);
        recyclerView.setAdapter(adapter);
    }

    private Product findByID(String id) {
        for (Product p : products) {
            if (p.id.equals(id)) {
                return p;
            }
        }
        return null;
    }

    private void order() {
        Gson gson = new Gson();
        String cartJson = gson.toJson(cart);
        api.post_order(String.valueOf(Table.current_id),
                String.valueOf(User.logged_id),
                cartJson);
    }
}
