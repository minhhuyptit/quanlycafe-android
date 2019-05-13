package Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Adapter.TableRecyclerViewAdapter;
import Api.AreaAPI;
import Api.CommonAPI;
import Api.TableAPI;
import Classes.Area;
import Classes.Table;
import advance_control.MovableFloatingActionButton;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityMenuBinding;

public class AdminTableActivity extends AppCompatActivity implements TableAPI.Callback, AreaAPI.Callback, TableRecyclerViewAdapter.Callback {
    List<Area> areas;
    List<Table> tables;
    ArrayList<String> arr;
    ArrayAdapter<String> adapterArea;
    ActivityMenuBinding binding;
    Spinner spinnerArea;
    MovableFloatingActionButton fab;
    List<Table> temp;
    int idArea;
    Table table;
    TableRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    int idTableXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_table);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_table);
        arr = new ArrayList<>();
        new AreaAPI(this).get_area(getApplicationContext());
        spinnerArea = findViewById(R.id.spinnerArea);
        adapterArea = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setOnItemSelectedListener(new MyProcessEvent());

        fab = findViewById(R.id.fab1);
        fab.setOnClickListener(addButton);
    }

    @Override
    public void errorTableResponse(String response) {
        Toast.makeText(this, "Lỗi kết nối: " + response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getTableResponse(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Table>>() {}.getType();

            tables = gson.fromJson(response, collectionType);
            if(tables==null){
                Toast.makeText(AdminTableActivity.this, "Danh sách bàn trống!", Toast.LENGTH_SHORT).show();
                return;
            }
            recyclerView = findViewById(R.id.rvTable);
            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerView.setLayoutManager(manager);
            //CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(this, categories, products, 1);
            adapter = new TableRecyclerViewAdapter(tables, this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void postTableResponse(String response) {
        if(response.equals("SUCCESS")){
            tables.add(table);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã thêm bàn thành công!", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Lỗi thêm bàn: " + response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteTableResponse(String response) {
        if(response.equals("SUCCESS")){
            adapter.notifyDataSetChanged();
            Toast.makeText(AdminTableActivity.this, "Xóa bàn thành công", Toast.LENGTH_SHORT).show();
            for(Table t: tables){
                if(t.id==idTableXoa){
                    tables.remove(t);
                    break;
                }
            }
        } else Toast.makeText(this, "Lỗi xóa bàn: " + response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getAreaResponse(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Area>>() {}.getType();
            areas = gson.fromJson(response, collectionType);
            arr.clear();
            for(Area a: areas) arr.add(a.name);
            spinnerArea.setAdapter(adapterArea);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void AreaErrorResponse(String response) {
        Toast.makeText(this, "Lỗi kết nối: " + response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSelectTable(final int table_id) {
        Toast.makeText(this, table_id + "", Toast.LENGTH_SHORT).show();

        final CharSequence[] items = {"Edit", "Delete" };
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminTableActivity.this);
        builder.setTitle("Select Options");
        builder.setIcon(R.drawable.button_shape);
//        position = i;
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:     //nhan vao edit dong 0
                        final Dialog dialog_2 = new Dialog(AdminTableActivity.this);
                        dialog_2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog_2.setCancelable(false);
                        dialog_2.setContentView(R.layout.update_table_dialog);

                        Button btnConfirm = dialog_2.findViewById(R.id.btnConfirm);
                        Button btnCancle = dialog_2.findViewById(R.id.btnCancle);
                        final EditText txtTenBan = dialog_2.findViewById(R.id.txtTenBan);
                        final EditText txtSoCho = dialog_2.findViewById(R.id.txtSoCho);

                        btnCancle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog_2.dismiss();
                            }
                        });
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(txtTenBan.getText().toString().equals("")||
                                txtSoCho.getText().toString().equals("")){
                                    Toast.makeText(AdminTableActivity.this, "Bạn chưa nhập đủ thông tin!!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                for(int position=0; position<tables.size(); position++){
                                    if(tables.get(position).id == table_id) {
                                        Table t = tables.get(position);
                                        t.name=txtTenBan.getText().toString();
                                        t.seat=Integer.parseInt(txtSoCho.getText().toString());
                                        tables.set(position, t);
                                        break;
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                Toast.makeText(AdminTableActivity.this, "Sửa bàn thành công", Toast.LENGTH_SHORT).show();
                                dialog_2.dismiss();
                            }
                        });
                        dialog_2.show();
                        break;
                    case 1:     //nhan vao delete dong 1
                        try {
                            AlertDialog alertDialog = new AlertDialog.Builder(AdminTableActivity.this).create();
                            alertDialog.setTitle("Confirm delete");
                            alertDialog.setMessage("Are you sure?");
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //xoa table
                                            new TableAPI(AdminTableActivity.this).remove_table(getApplicationContext(),table_id+"");
                                            idTableXoa = table_id;
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
                        break;
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Log.e("on select spinner: ", arg2+"");
            if(areas!=null){
                idArea = areas.get(arg2).id;
                new TableAPI(AdminTableActivity.this).get_table(getApplicationContext(), idArea);//--------------------------------------------------
            }
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            //selection.setText("");
        }
    }

    View.OnClickListener addButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(AdminTableActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.add_table_dialog);
            Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
            Button btnCancle = dialog.findViewById(R.id.btnCancle);
            final EditText txtTenBan = dialog.findViewById(R.id.txtTenBan);
            final EditText txtSoCho = dialog.findViewById(R.id.txtSoCho);
            btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( txtTenBan.getText().toString().equals("")||
                     txtSoCho.getText().toString().equals("")){
                    Toast.makeText(AdminTableActivity.this, "Bạn chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                table = new Table(1, txtTenBan.getText().toString(),Integer.parseInt(txtSoCho.getText().toString()), 0, idArea);
                new TableAPI(AdminTableActivity.this).post_table(getApplicationContext(),table);


                dialog.dismiss();
                }
            });
            dialog.show();
        }
    };
}
