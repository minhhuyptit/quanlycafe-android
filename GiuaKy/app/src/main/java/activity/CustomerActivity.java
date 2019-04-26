package activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import advance_control.MovableFloatingActionButton;
import xyz.khang.quanlythuexedulich.R;

public class CustomerActivity extends AppCompatActivity {

    SearchView sv_khachhang;
    ListView lv_khachhang;
    MovableFloatingActionButton fab;

    ArrayList<Customer> listRootKH, listKH;
    CustomerAdapter adapter;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        mapping();
        dumpData();

        adapter = new CustomerAdapter(this, R.layout.per_line_customer, listKH);
        lv_khachhang.setAdapter(adapter);

        lv_khachhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CustomerActivity.this, "Click " + i, Toast.LENGTH_SHORT).show();
            }
        });
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_customer_dialog);

                Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
                Button btnCancle = dialog.findViewById(R.id.btnCancle);
                final EditText txtMaKH = dialog.findViewById(R.id.txtMaKH);
                final EditText txtTenKH = dialog.findViewById(R.id.txtTenKH);
                final EditText txtDiaChiKH = dialog.findViewById(R.id.txtDiaChiKH);

                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(txtMaKH.equals("")){
                            Toast.makeText(CustomerActivity.this, "Mã Khách Hàng không được rỗng!", Toast.LENGTH_SHORT).show();
                            return;
                        }else if(txtTenKH.equals("")){
                            Toast.makeText(CustomerActivity.this, "Tên Khách Hàng không được rỗng!", Toast.LENGTH_SHORT).show();
                            return;
                        }else if(checkExistMaKH(txtMaKH.getText().toString())){
                            Toast.makeText(CustomerActivity.this, "Mã Khách Hàng đã tồn tại!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(CustomerActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });

        lv_khachhang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final CharSequence[] items = {"Edit", "Delete" };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Select Options");
                builder.setIcon(R.drawable.options);
                position = i;
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Toast.makeText(CustomerActivity.this, "Edit KH " + listKH.get(position).getTenKH(), Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                //Delete element at listRootKH
                                listRootKH.remove(listKH.get(position));
                                //Delete element at listKH
                                listKH.remove(position);
                                adapter.notifyDataSetChanged();
                                break;
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });



        sv_khachhang.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String str) {
                listKH.clear();
                for (Customer customer: listRootKH) {
                    boolean b1 = customer.getMaKH().toLowerCase().contains(str.toLowerCase());
                    boolean b2 = customer.getTenKH().toLowerCase().contains(str.toLowerCase());
                    if(b1 || b2){
                        listKH.add(customer);
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    private void dumpData(){
        //listRootKH lấy dữ liệu từ database vê
        listRootKH = new ArrayList<>();
        Customer customer_1 = new Customer("KH01", "Nguyễn Tấn Luông", "Bến Tre");
        listRootKH.add(customer_1);
        Customer customer_2 = new Customer("KH02", "Nguyễn Hà Minh Huy", "Long An");
        listRootKH.add(customer_2);
        Customer customer_3 = new Customer("KH03", "Dương Đình Hạnh", "Đak Lak");
        listRootKH.add(customer_3);
        Customer customer_4 = new Customer("KH04", "Nguyễn Hữu Thắng", "Lâm Đồng");
        listRootKH.add(customer_4);
        Customer customer_5 = new Customer("KH05", "Đặng Hoàng Khang", "TP.Hồ Chí Minh");
        listRootKH.add(customer_5);
        Customer customer_6 = new Customer("KH06", "Vũ Đức Tài", "Nghệ An");
        listRootKH.add(customer_6);
        Customer customer_7 = new Customer("KH07", "Trần Hữu Thế", "Nghệ An");
        listRootKH.add(customer_7);
        Customer customer_8 = new Customer("KH07", "Lưu Hoàng Trung", "Quảng Bình");
        listRootKH.add(customer_8);

        //Sử dụng cho filter
//        listKH = new ArrayList<>();
        listKH = (ArrayList<Customer>) listRootKH.clone();
//        Collections.copy(listKH, listRootKH);
    }

    private boolean checkExistMaKH(String maKH){
        for (Customer customer: listRootKH) {
            if(customer.getMaKH().equalsIgnoreCase(maKH)) return true;
        }
        return false;
    }

    private void mapping(){
        sv_khachhang = (SearchView) findViewById(R.id.sv_khachhang);
        lv_khachhang = (ListView) findViewById(R.id.lv_khachhang);
        fab = (MovableFloatingActionButton) findViewById(R.id.fab);
    }
}
