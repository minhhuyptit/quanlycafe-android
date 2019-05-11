package Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Adapter.CategoryAdminAdapter;
import Adapter.CategoryRecyclerViewAdapter;
import Api.CommonAPI;
import Api.MenuAPI;
import Classes.Category;
import Classes.Table;
import advance_control.MovableFloatingActionButton;
import xyz.khang.quanlyquancafe.R;

public class CategoryAdminActivity extends AppCompatActivity implements MenuAPI.Callback{

    ListView lv_category;
    MovableFloatingActionButton fab;
    CategoryAdminAdapter adapter;
    ArrayList<Category> listCategory;
    MenuAPI api;
    int position = 0;
    boolean isTrue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_admin);
        mapping();
        listCategory = new ArrayList<>();
        api = new MenuAPI(this, getApplicationContext());
        api.get_category();
        lv_category.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final CharSequence[] items = {"Delete" };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                position = i;
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            AlertDialog alertDialog = new AlertDialog.Builder(CategoryAdminActivity.this).create();
                            alertDialog.setTitle("Confirm delete");
                            alertDialog.setMessage("Are you sure?");
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            delete_category(listCategory.get(position).id);
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
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
        lv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialog_2 = new Dialog(CategoryAdminActivity.this);
                dialog_2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_2.setCancelable(false);
                dialog_2.setContentView(R.layout.add_edit_category_dialog);
                position = i;

                Button btnConfirm = dialog_2.findViewById(R.id.btnConfirm);
                Button btnCancle = dialog_2.findViewById(R.id.btnCancle);
                final EditText edtID = dialog_2.findViewById(R.id.edtID);
                final EditText edtName = dialog_2.findViewById(R.id.edtName);
                final TextView tvTitle = dialog_2.findViewById(R.id.TitleCategory);

                edtID.setText(listCategory.get(position).id);
                edtName.setText(listCategory.get(position).name);
                tvTitle.setText("Edit Category");
                edtID.setEnabled(false);
                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_2.dismiss();
                    }
                });
                position = i;
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String cate_id = listCategory.get(position).id;
                        String cate_name = edtName.getText().toString();
                        put_category(cate_id,cate_name, dialog_2);
                    }
                });
                dialog_2.show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog_1 = new Dialog(CategoryAdminActivity.this);
                dialog_1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_1.setCancelable(false);
                dialog_1.setContentView(R.layout.add_edit_category_dialog);

                Button btnConfirm = dialog_1.findViewById(R.id.btnConfirm);
                Button btnCancle = dialog_1.findViewById(R.id.btnCancle);
                final EditText edtID = dialog_1.findViewById(R.id.edtID);
                final EditText edtName = dialog_1.findViewById(R.id.edtName);
                final TextView tvTitle = dialog_1.findViewById(R.id.TitleCategory);

                tvTitle.setText("Add Category");
                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_1.dismiss();
                    }
                });
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String cate_id = edtID.getText().toString();
                        String cate_name = edtName.getText().toString();
                        add_category(cate_id,cate_name,dialog_1);
                    }
                });
                dialog_1.show();
            }
        });
    }
    private void mapping(){
        lv_category = (ListView) findViewById(R.id.lv_category);
        fab = (MovableFloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void onGetTableCartResponse(String response) {

    }

    @Override
    public void onGetCategoryResponse(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Category>>() {
            }.getType();
            listCategory = gson.fromJson(response, collectionType);
            adapter = new CategoryAdminAdapter(this, R.layout.category_admin_item, listCategory);
            lv_category.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetProductResponse(String response) {

    }

    @Override
    public void onPostOrder(String response) {

    }

    @Override
    public void onPutCheck(String response) {

    }

    public void delete_category(final String cate_id){
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/category/" + cate_id;
        StringRequest requestString = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("CATEGORY_NOT_FOUND")){
                    Toast.makeText(CategoryAdminActivity.this, "Không tìm thấy ID Category", Toast.LENGTH_SHORT).show();
                }else if(response.equals("FK_EXIST_ANOTHER_TABLE")){
                    Toast.makeText(CategoryAdminActivity.this, "Category đã được sử dụng ở bảng khác", Toast.LENGTH_SHORT).show();
                }else if(response.equals("SUCCESS")){
                    listCategory.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(CategoryAdminActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CategoryAdminActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(requestString);
    }

    public void add_category(final String cate_id, final String cate_name, final Dialog dialog){
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/category";
        StringRequest requestString = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("EMPTY_ID")){
                    Toast.makeText(CategoryAdminActivity.this, "ID Category không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("IDENTICAL_ID")){
                    Toast.makeText(CategoryAdminActivity.this, "ID Category đã tồn tại", Toast.LENGTH_SHORT).show();
                }else if(response.equals("EMPTY_NAME")){
                    Toast.makeText(CategoryAdminActivity.this, "Tên Category không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("IDENTICAL_NAME")){
                    Toast.makeText(CategoryAdminActivity.this, "Tên Category đã tồn tại", Toast.LENGTH_SHORT).show();
                }else if(response.equals("SUCCESS")){
                    Category category = new Category();
                    category.id = cate_id;
                    category.name = cate_name;
                    listCategory.add(category);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(CategoryAdminActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(CategoryAdminActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", cate_id);
                params.put("name", cate_name);
                return params;
            }
        };
        requestQueue.add(requestString);
    }

    public void put_category(final String cate_id, final String cate_name, final Dialog dialog){
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/category/" + cate_id;
        StringRequest requestString = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("EMPTY_NAME")){
                    Toast.makeText(CategoryAdminActivity.this, "Tên Category không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("IDENTICAL_NAME")){
                    Toast.makeText(CategoryAdminActivity.this, "Tên Category đã tồn tại", Toast.LENGTH_SHORT).show();
                }else if(response.equals("CATEGORY_NOT_FOUND")){
                    Toast.makeText(CategoryAdminActivity.this, "Không tìm thấy ID Category", Toast.LENGTH_SHORT).show();
                }else if(response.equals("SUCCESS")){
                    listCategory.get(position).name = cate_name;
                    adapter.notifyDataSetChanged();
                    Toast.makeText(CategoryAdminActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(CategoryAdminActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", cate_name);
                return params;
            }
        };
        requestQueue.add(requestString);
    }
}
