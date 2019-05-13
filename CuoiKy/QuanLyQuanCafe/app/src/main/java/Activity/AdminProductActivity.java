package Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Adapter.ProductRecyclerViewAdapter;
import Api.CategoryAPI;
import Api.CommonAPI;
import Api.MenuAPI;
import Api.ProductAPI;
import Classes.Category;
import Classes.Product;
import advance_control.MovableFloatingActionButton;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityMenuBinding;

public class AdminProductActivity extends AppCompatActivity implements ProductRecyclerViewAdapter.Callback, CategoryAPI.Callback, ProductAPI.Callback {
    List<Category> categories;
    List<Product> products;
    ArrayList<String> arr;
    ArrayAdapter<String> adapterCategory;
    ActivityMenuBinding binding;
    Spinner spinnerCategory;
    MovableFloatingActionButton fab;
    ProductRecyclerViewAdapter adapter;
//    List<Product> temp;
    String idCategory;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_product);
        setControl();
        setEvent();
    }

    @Override
    public void onMoreClick(final String id) {
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();

        final Dialog dialog = new Dialog(AdminProductActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.edit_product_dialog);
        Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
        Button btnCancle = dialog.findViewById(R.id.btnCancle);
        final EditText txtTenMon = dialog.findViewById(R.id.txtTenMon);
        final EditText txtGia = dialog.findViewById(R.id.txtGia);
        final EditText txtMoTa = dialog.findViewById(R.id.txtMoTa);

        for(Product p: products){
            if(p.id.equals(id)){
                txtTenMon.setText(p.name);
                txtGia.setText(p.price+"");
                txtMoTa.setText(p.description);
                break;
            }
        }

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( txtTenMon.getText().toString().equals("")||
                        txtGia.getText().toString().equals("")){
                    Toast.makeText(AdminProductActivity.this, "Bạn chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                 product = new Product(id, txtTenMon.getText().toString(),
                        Double.parseDouble(txtGia.getText().toString()), txtMoTa.getText().toString(), idCategory);
                new ProductAPI(AdminProductActivity.this).update_product(getApplicationContext(), product);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onLessClick(final String id) {
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Confirm delete");
            alertDialog.setMessage("Are you sure?");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            removeProduct(id);
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cancle",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorCategoryResponse(String response) {
        Toast.makeText(this, "Lỗi kết nối: " + response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCategoryResponse(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Category>>() {
            }.getType();
            categories = gson.fromJson(response, collectionType);
            //Hien thi tra Spinner
            for(Category c: categories) arr.add(c.name);
            spinnerCategory.setAdapter(adapterCategory);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void postCategoryResponse(String response) {

    }

    @Override
    public void deleteCategoryResponse(String response) {

    }

    @Override
    public void errorProductResponse(String response) {
        Toast.makeText(this, "Lỗi kết nối: " + response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getProductResponse(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Product>>() {
            }.getType();
            products = gson.fromJson(response, collectionType);
            if(products==null){
                Toast.makeText(this, "Không có sản phẩm nào trong mục này", Toast.LENGTH_SHORT).show();
                return;
            }
            RecyclerView recyclerView = findViewById(R.id.rvProduct1);
            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerView.setLayoutManager(manager);
            //CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(this, categories, products, 1);
            adapter = new ProductRecyclerViewAdapter(this, products);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void postProductResponse(String response) {
        if(response.equals("SUCCESS")){
            products.add(product);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Lỗi thêm sản phẩm: " + response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateProductResponse(String response) {
        if(response.equals("SUCCESS")){
            for(int i=0; i<products.size(); i++){
                if(products.get(i).id.equals(product.id)){
                    products.set(i, product);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        } else Toast.makeText(this, "Lỗi update: " + response, Toast.LENGTH_SHORT).show();
}

    @Override
    public void deleteProductResponse(String response) {
        if(response.equals("SUCCESS")){
            Toast.makeText(this, "Đã xóa sản phẩm thành công!", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        } else Toast.makeText(this, "Lỗi xóa sản phẩm: " + response, Toast.LENGTH_SHORT).show();
    }

    void setControl(){
        arr = new ArrayList<>();
        fab = findViewById(R.id.fab);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        adapterCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    void setEvent(){
        new CategoryAPI(getApplicationContext(),this).get_category();
        spinnerCategory.setOnItemSelectedListener(new MyProcessEvent());
        fab.setOnClickListener(addButton);
    }

    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Log.e("on select spinner: ", arg2+"");
            if(categories!=null){
                Log.e("category", " is != null");
                if(categories!=null){
                    idCategory = categories.get(arg2).id;
                    new ProductAPI(AdminProductActivity.this).get_product(getApplicationContext(), idCategory);
                }
            }
        }

        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            //selection.setText("");
        }
    }

    void removeProduct(String id){
        //Xoa product ra khoi database
        new ProductAPI(this).delete_product(getApplicationContext(), id);
        for (Product p: products)
            if(p.id.equals(id)) {
                products.remove(p);
                break;
            }
    }

    void addProduct(Product product){
        //cap nhat database
        new ProductAPI(this).post_product(getApplicationContext(), product);
    }

    View.OnClickListener addButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(AdminProductActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.add_product_dialog);
            Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
            Button btnCancle = dialog.findViewById(R.id.btnCancle);
            final EditText txtMaMon = dialog.findViewById(R.id.txtMaMon);
            final EditText txtTenMon = dialog.findViewById(R.id.txtTenMon);
            final EditText txtGia = dialog.findViewById(R.id.txtGia);
            final EditText txtMoTa = dialog.findViewById(R.id.txtMoTa);

            btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(txtMaMon.getText().toString().equals("")||
                            txtTenMon.getText().toString().equals("")||
                            txtGia.getText().toString().equals("")){
                        Toast.makeText(AdminProductActivity.this, "Bạn chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    product = new Product(txtMaMon.getText().toString(), txtTenMon.getText().toString(),
                            Double.parseDouble(txtGia.getText().toString()), txtMoTa.getText().toString(), idCategory);
                    addProduct(product);
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    };

}
