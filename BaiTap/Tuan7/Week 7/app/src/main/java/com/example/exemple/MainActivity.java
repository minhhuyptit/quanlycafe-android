package com.example.exemple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<Employee> data = new ArrayList<>();
    Button btnNhap;
    ImageButton btnRemoveAll;
    EditText editMa,editTen;
    RadioGroup genderGroup;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
        btnNhap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                xulyNhap();
            }
        });
    }

    private  void setControl(){
        listView = findViewById(R.id.listView);

        btnNhap=(Button) findViewById(R.id.btnNhap);
        editMa=(EditText) findViewById(R.id.txtMaNV);
        editTen=(EditText) findViewById(R.id.txtTenNV);
        genderGroup=(RadioGroup) findViewById(R.id.radioGroup);
    }

    private  void setEvent(){
        KhoiTao();
        //ArrayAdapter<SocialNetwork> arrayAdapter = new ArrayAdapter<SocialNetwork>(this, android.R.layout.simple_list_item_1, data);
        adapter = new EmployeeAdapter(this, R.layout.listview_item_row,data);
        listView.setAdapter(adapter);
    }
    void KhoiTao(){
        data.add(new Employee("sdfd", "Facebook", false));
        data.add(new Employee("sdfd", "Facebook", false));
        data.add(new Employee("sdfd", "Facebook", false));


    }
    void xulyNhap(){
        String ma=editMa.getText().toString();
        String ten=editTen.getText()+"";
        boolean gioitinh=false;//Nam =false
        if(genderGroup.getCheckedRadioButtonId()==R.id.nu)
            gioitinh=true;
        //Tạo một employee
        Employee emp=new Employee(ma, ten, gioitinh);

        //Đưa vào danh sách
        data.add(emp);
        //gọi hàm cập nhật giao diện
        adapter.notifyDataSetChanged();
        //Sau khi update thì xóa trắng dữ liệu và cho editma focus
        editMa.setText("");
        editTen.setText("");
        editMa.requestFocus();
    }
}

