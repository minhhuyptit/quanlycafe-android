package com.example.exemple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<MonHoc> data = new ArrayList<>();
    Button btnInsert;
    Button btnLoad;

    EditText editMa,editTenMH, editSoTiet;
    MonHocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                xulyNhap();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                xulyXuat();
            }
        });
    }

    private  void setControl(){
        listView = findViewById(R.id.listView);
        btnInsert=(Button) findViewById(R.id.btnInsert);
        btnLoad=(Button) findViewById(R.id.btnLoad);
        editMa=(EditText) findViewById(R.id.txtMaMH);
        editTenMH=(EditText) findViewById(R.id.txtTenMH);
        editSoTiet=(EditText) findViewById(R.id.txtSoTiet);
    }

    private  void setEvent(){
        KhoiTao();
        //ArrayAdapter<SocialNetwork> arrayAdapter = new ArrayAdapter<SocialNetwork>(this, android.R.layout.simple_list_item_1, data);
        adapter = new MonHocAdapter(this, R.layout.listview_item_row,data);
        listView.setAdapter(adapter);
    }
    void KhoiTao(){
        data.add(new MonHoc("sdfd", "Facebook", 543));
        data.add(new MonHoc("sdfd", "Facebook", 65));
        data.add(new MonHoc("sdfd", "Facebook", 3));
    }

    void xulyNhap(){
        MyDatabase db = new MyDatabase(this);
        MonHoc monHoc = new MonHoc();
        monHoc.setId(editMa.getText().toString());
        monHoc.setName(editTenMH.getText().toString());
        monHoc.setSoTiet(Integer.parseInt(editSoTiet.getText().toString()));
        db.saveMonHocs(monHoc);
    }
    void xulyXuat(){
        MyDatabase db = new MyDatabase(this);
        data.clear();
        db.getMonHocS(data);
        adapter.notifyDataSetChanged();
    }

}

