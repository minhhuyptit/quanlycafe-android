package Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import Adapter.AreaAdminAdapter;
import Api.CommonAPI;
import Classes.Area;
import advance_control.MovableFloatingActionButton;
import xyz.khang.quanlyquancafe.R;

public class AreaAdminActivity extends AppCompatActivity implements CommonAPI.Callback{

    ListView lv_area;
    MovableFloatingActionButton fab1;
    AreaAdminAdapter adapter;
    ArrayList<Area> listArea;
    int position = 0;
    boolean isTrue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_admin);
        mapping();
        listArea = new ArrayList<>();
        new CommonAPI(this).get_area(getApplicationContext());
        lv_area.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final CharSequence[] items = {"Delete" };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                position = i;
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            AlertDialog alertDialog = new AlertDialog.Builder(AreaAdminActivity.this).create();
                            alertDialog.setTitle("Confirm delete");
                            alertDialog.setMessage("Are you sure?");
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            delete_area(listArea.get(position).id);
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

        lv_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialog_2 = new Dialog(AreaAdminActivity.this);
                dialog_2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_2.setCancelable(false);
                dialog_2.setContentView(R.layout.add_edit_category_dialog);
                position = i;

                Button btnConfirm = dialog_2.findViewById(R.id.btnConfirm);
                Button btnCancle = dialog_2.findViewById(R.id.btnCancle);
                final EditText edtID = dialog_2.findViewById(R.id.edtID);
                final EditText edtName = dialog_2.findViewById(R.id.edtName);
                final TextView tvTitle = dialog_2.findViewById(R.id.TitleCategory);

                edtID.setText(String.valueOf(listArea.get(position).id));
                edtName.setText(listArea.get(position).name);
                tvTitle.setText("Edit Area");
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
                        String area_id = String.valueOf(listArea.get(position).id);
                        String area_name = edtName.getText().toString();
                        put_area(area_id,area_name, dialog_2);
                    }
                });
                dialog_2.show();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog_1 = new Dialog(AreaAdminActivity.this);
                dialog_1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_1.setCancelable(false);
                dialog_1.setContentView(R.layout.add_edit_category_dialog);

                Button btnConfirm = dialog_1.findViewById(R.id.btnConfirm);
                Button btnCancle = dialog_1.findViewById(R.id.btnCancle);
                final EditText edtID = dialog_1.findViewById(R.id.edtID);
                final EditText edtName = dialog_1.findViewById(R.id.edtName);
                final TextView tvTitle = dialog_1.findViewById(R.id.TitleCategory);

                tvTitle.setText("Add Area");
                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_1.dismiss();
                    }
                });
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String area_id = edtID.getText().toString();
                        String area_name = edtName.getText().toString();
                        add_area(area_id,area_name,dialog_1);  
                    }
                });
                dialog_1.show();
            }
        });
    }

    private void mapping(){
        lv_area = (ListView) findViewById(R.id.lv_area);
        fab1 = (MovableFloatingActionButton) findViewById(R.id.fab1);
    }

    public void delete_category(final String area_id){
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/area/" + area_id;
        StringRequest requestString = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("AREA_NOT_FOUND")){
                    Toast.makeText(AreaAdminActivity.this, "Không tìm thấy ID Area", Toast.LENGTH_SHORT).show();
                }else if(response.equals("FK_EXIST_ANOTHER_TABLE")){
                    Toast.makeText(AreaAdminActivity.this, "Area đã được sử dụng ở bảng khác", Toast.LENGTH_SHORT).show();
                }else if(response.equals("SUCCESS")){
                    listArea.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AreaAdminActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AreaAdminActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(requestString);
    }

    @Override
    public void onResponse(String response){
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Area>>() {
            }.getType();
            listArea = gson.fromJson(response, collectionType);
            adapter = new AreaAdminAdapter(this, R.layout.area_admin_item, listArea);
            lv_area.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }
    public void delete_area(final int cate_id){
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/area/" + cate_id;
        StringRequest requestString = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("CATEGORY_NOT_FOUND")){
                    Toast.makeText(AreaAdminActivity.this, "Không tìm thấy ID Area", Toast.LENGTH_SHORT).show();
                }else if(response.equals("FK_EXIST_ANOTHER_TABLE")){
                    Toast.makeText(AreaAdminActivity.this, "Area đã được sử dụng ở bảng khác", Toast.LENGTH_SHORT).show();
                }else if(response.equals("SUCCESS")){
                    listArea.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AreaAdminActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AreaAdminActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(requestString);
    }

    public void add_area(final String area_id, final String area_name, final Dialog dialog){
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/area";
        StringRequest requestString = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("EMPTY_ID")){
                    Toast.makeText(AreaAdminActivity.this, "ID Area không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("IDENTICAL_ID")){
                    Toast.makeText(AreaAdminActivity.this, "ID Area đã tồn tại", Toast.LENGTH_SHORT).show();
                }else if(response.equals("EMPTY_NAME")){
                    Toast.makeText(AreaAdminActivity.this, "Tên Area không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("IDENTICAL_NAME")){
                    Toast.makeText(AreaAdminActivity.this, "Tên Area đã tồn tại", Toast.LENGTH_SHORT).show();
                }else if(response.equals("SUCCESS")){
                    Area area = new Area();
                    area.id = Integer.parseInt(area_id);
                    area.name = area_name;
                    listArea.add(area);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AreaAdminActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(AreaAdminActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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
                params.put("id", area_id);
                params.put("name", area_name);
                return params;
            }
        };
        requestQueue.add(requestString);
    }

    public void put_area(final String area_id, final String area_name, final Dialog dialog){
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/area/" + area_id;
        StringRequest requestString = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("EMPTY_NAME")){
                    Toast.makeText(AreaAdminActivity.this, "Tên Area không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("IDENTICAL_NAME")){
                    Toast.makeText(AreaAdminActivity.this, "Tên Area đã tồn tại", Toast.LENGTH_SHORT).show();
                }else if(response.equals("AREA_NOT_FOUND")){
                    Toast.makeText(AreaAdminActivity.this, "Không tìm thấy ID Area", Toast.LENGTH_SHORT).show();
                }else if(response.equals("SUCCESS")){
                    listArea.get(position).name = area_name;
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AreaAdminActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(AreaAdminActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
                params.put("name", area_name);
                return params;
            }
        };
        requestQueue.add(requestString);
    }
}
