package Activity;

import android.app.Dialog;
import android.content.Intent;
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
import Adapter.UserAdminAdapter;
import Api.CommonAPI;
import Classes.Area;
import Classes.User;
import advance_control.MovableFloatingActionButton;
import xyz.khang.quanlyquancafe.R;

public class AdminUserActivity extends AppCompatActivity implements CommonAPI.Callback{

    ListView lv_user;
    MovableFloatingActionButton fab;
    UserAdminAdapter adapter;
    ArrayList<User> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);
        mapping();
        listUser = new ArrayList<>();
        new CommonAPI(this).get_user(getApplicationContext());

        lv_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = listUser.get(i);
                Bundle bundle = new Bundle();
                bundle.putInt("id", user.id);
                bundle.putString("username", user.username);
                bundle.putString("fullname", user.fullname);
                bundle.putString("phone", user.phone);
                bundle.putInt("salary", user.salary);
                Intent intent = new Intent(getApplicationContext(), SalaryEmployeeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog_1 = new Dialog(AdminUserActivity.this);
                dialog_1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_1.setCancelable(false);
                dialog_1.setContentView(R.layout.add_edit_user_dialog);

                Button btnConfirm = dialog_1.findViewById(R.id.btnConfirm);
                Button btnCancel = dialog_1.findViewById(R.id.btnCancel);
                final EditText edtUsername = dialog_1.findViewById(R.id.edtUsername);
                final EditText edtPassword = dialog_1.findViewById(R.id.edtPassword);
                final EditText edtFullname = dialog_1.findViewById(R.id.edtFullname);
                final EditText edtPhone = dialog_1.findViewById(R.id.edtPhone);
                final EditText edtSalary = dialog_1.findViewById(R.id.edtSalary);
                final EditText edtAccessLevel = dialog_1.findViewById(R.id.edtAccessLevel);
                final TextView tvTitleUser = dialog_1.findViewById(R.id.tvTitleUser);


                tvTitleUser.setText("Add User");
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_1.dismiss();
                    }
                });
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        User user = new User();
                        user.id = 0;
                        user.username = edtUsername.getText().toString();
                        user.fullname = edtFullname.getText().toString();
                        user.phone = edtPhone.getText().toString();
                        user.image = "picture.png";
                        user.salary = edtSalary.getText().toString().isEmpty() ? 0 : Integer.parseInt(edtSalary.getText().toString());
                        user.accessLevel = edtAccessLevel.getText().toString().isEmpty() ? 0 : Integer.parseInt(edtAccessLevel.getText().toString());
                        add_user(user, edtPassword.getText().toString(), dialog_1);
                    }
                });
                dialog_1.show();
            }
        });
    }
    @Override
    public void onResponse(String response){
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<User>>() {
            }.getType();
            listUser = gson.fromJson(response, collectionType);
            adapter = new UserAdminAdapter(this, R.layout.user_admin_item, listUser);
            lv_user.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }

    public void add_user(final User user, final String password, final Dialog dialog){
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/user";
        StringRequest requestString = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("EMPTY_USERNAME")){
                    Toast.makeText(AdminUserActivity.this, "Username không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("IDENTICAL_USERNAME")){
                    Toast.makeText(AdminUserActivity.this, "Username đã tồn tại", Toast.LENGTH_SHORT).show();
                }else if(response.equals("EMPTY_PASSWORD")){
                    Toast.makeText(AdminUserActivity.this, "Password không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("EMPTY_FULLNAME")){
                    Toast.makeText(AdminUserActivity.this, "Họ và tên không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("EMPTY_PHONE")){
                    Toast.makeText(AdminUserActivity.this, "Số điện thoại không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("EMPTY_IMAGE")){
                    Toast.makeText(AdminUserActivity.this, "Image không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("EMPTY_SALARY")){
                    Toast.makeText(AdminUserActivity.this, "Lương không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("NOT_NUMBER_SALARY")){
                    Toast.makeText(AdminUserActivity.this, "Lương phải là kiểu số", Toast.LENGTH_SHORT).show();
                }else if(response.equals("EMPTY_ACCESS_LEVEL")){
                    Toast.makeText(AdminUserActivity.this, "Access Level không được rỗng", Toast.LENGTH_SHORT).show();
                }else if(response.equals("access_level")){
                    Toast.makeText(AdminUserActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    user.id = Integer.parseInt(response);
                    listUser.add(user);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AdminUserActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
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
                params.put("username", user.username);
                params.put("password", password);
                params.put("fullname", user.fullname);
                params.put("phone", user.phone);
                params.put("image", user.image);
                params.put("salary", String.valueOf(user.salary));
                params.put("access_level", String.valueOf(user.accessLevel));
                return params;
            }
        };
        requestQueue.add(requestString);
    }

    private void mapping(){
        lv_user = (ListView) findViewById(R.id.lv_user);
        fab = (MovableFloatingActionButton) findViewById(R.id.fab2);
    }
}
