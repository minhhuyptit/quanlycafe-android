package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.Query;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Adapter.CartRecyclerViewAdapter;
import Adapter.CategoryRecyclerViewAdapter;
import Adapter.ProductRecyclerViewAdapter;
import Api.CommonAPI;
import Api.MenuAPI;
import Api.RootFirebase;
import Classes.Area;
import Classes.Cart;
import Classes.Category;
import Classes.Item;
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
    Cart cartTemp;  //Chua ca cac mon da goi truoc do cong them voi cac mon vua goi them
    Cart cartMain;           //Chua san phan neu ban nay goi them mon
    boolean product_flag = false, newOrder_flag=false, firtOrder=true;
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
//                Query query = RootFirebase.rootKitchen.child(RootFirebase.getDay()).orderByChild()
//                .child().setValue("empty");
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
            //Neu ban nay da ton tai (cart.size != 0) thi khoi tao cartNew
            if(cart!=null){
                firtOrder=false;
                Toast.makeText(this, "Bàn đã được order, gọi thêm nước", Toast.LENGTH_SHORT).show();
                cartMain = gson.fromJson(gson.toJson(cart),Cart.class);
                cartTemp = gson.fromJson(gson.toJson(cart),Cart.class);
            }
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
//            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
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
        //Neu ban duoc order truoc do thi tao ra danh sach moi
        if(cartMain!=null && newOrder_flag==false){
            cart.cartItem.clear();
            newOrder_flag=true;
        }
        cart.more(findByID(id));
        if(!firtOrder) cartTemp.more(findByID(id));
        setRVCart();
    }

    @Override
    public void onLessClick(String id) {
        //Bo neu dat nut tru o card
        if(cart.cartItem.get(id)==null) return;
        //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
        if(!firtOrder && !newOrder_flag){
            Toast.makeText(this, "Không thể thay đổi các món đã order", Toast.LENGTH_SHORT).show();
            return;
        }
        if(cart.cartItem.size()==1 && cart.cartItem.get(id).quantity==1 && cartMain!=null){
            Gson gson = new Gson();
            cart=gson.fromJson(gson.toJson(cartMain), Cart.class);
            newOrder_flag=false;
            setRVCart();
            return;
        }
        cart.less(id);
        if(!firtOrder) cartTemp.less(id);
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
        try {
            Toast.makeText(getApplicationContext(),String.valueOf(products.size()),Toast.LENGTH_SHORT).show();
            RecyclerView recyclerView = binding.rvCategory;
            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerView.setLayoutManager(manager);
            //CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(this, categories, products, 1);
            List<Product> temp = new ArrayList<>();
            for (Product p : products) {
                if (p.idCategory == null) continue;
                if (p.idCategory.equals(category_id)) {
                    temp.add(p);
                }
            }
            ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(this, temp);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

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
        //Neu khong phai order lan dau, ma khong co goi them mon nao thi dung lai
        if(!firtOrder && cart.cartItem.size()==0) return;
        Date date = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

        //Cap nhat cac mon vua them len firebase cho bep
        //Note status:
        //order: vua moi duoc order
        //begin: bat dau co nguoi click vao => bat dau lam nuoc
        //end: nuoc da lam xong => thong bao cho nhan vien
        //empty: sau khi nhan vien da doc thong bao va nhan vao OK
        ChickenCart chickenCart = new ChickenCart(String.valueOf(Table.current_name), Area.curent_name, User.logged_name, "order", formatTime.format(date));
        Iterator<Map.Entry<String, Item>> iteratorCart = cart.cartItem.entrySet().iterator();
        Map.Entry<String, Item> entryCart;
        while (iteratorCart.hasNext()){
            entryCart = iteratorCart.next();
            chickenCart.chickenProduct.put(entryCart.getValue().product.name, entryCart.getValue().quantity);
        }
        RootFirebase.rootKitchen.child(formatDate.format(date)).push().setValue(chickenCart);

        //Cap nhat vao database
        Gson gson = new Gson();
        String cartJson;
        if(firtOrder){  //Neu order lan dau thi thuc hien binh thuong
            cartJson = gson.toJson(cart);
        }else{          // <> !firtOrder && cart.cartItem.size()!=0
            cartJson = gson.toJson(cartTemp);   //Neu khong phai order lan dau thi cartTemp chua ca cu va moi
        }
        api.post_order(String.valueOf(Table.current_id),
                String.valueOf(User.logged_id),
                cartJson);
        this.finish();
    }
}

class ChickenCart{
    String table;
    String area;
    String loginname;
    String status;
    String timein;
    HashMap<String, Integer> chickenProduct = new HashMap<>();

    public ChickenCart(String table, String area, String loginname, String status, String timein) {
        this.table = table;
        this.area = area;
        this.loginname = loginname;
        this.status = status;
        this.timein = timein;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimein() {
        return timein;
    }

    public void setTimein(String timein) {
        this.timein = timein;
    }

    public HashMap<String, Integer> getChickenProduct() {
        return chickenProduct;
    }

    public void setChickenProduct(HashMap<String, Integer> chickenProduct) {
        this.chickenProduct = chickenProduct;
    }
}

