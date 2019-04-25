package xyz.khang.quanlythuexedulich;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.Car;
import model.CarContract;

public class activity_car_detail extends AppCompatActivity {
    Button btnOK, btnCancel;
    EditText edtMaXe, edtSoNgay, edtGiaXe;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        setControl();
        KhoiTao();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOKClick();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelClick();
            }
        });
    }
    private  void setControl(){
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCANCEL);
        edtMaXe = findViewById(R.id.edtMaXe);
        edtSoNgay = findViewById(R.id.edtSoNgay);
        edtGiaXe = findViewById(R.id.edtGiaXe);
    }
    private void onOKClick(){
        String gia, ngay, maxe;
        gia = edtGiaXe.getText().toString();
        ngay = edtSoNgay.getText().toString();
        maxe = edtMaXe.getText().toString();
        if(gia.equals("") || maxe.equals("") || ngay.equals("")){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
            return;
        }
        Car t = activity_list_car.data.get(index);
        t.setHasChoose(true);
        t.contract = new CarContract(maxe, ngay, gia);
        activity_list_car.data.set(index, t);
        Intent intent = new Intent(activity_car_detail.this, activity_list_car.class);
        startActivity(intent);
    }
    private void onCancelClick(){
        onBackPressed();
    }
    void KhoiTao(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        edtMaXe.setText(id);
        index = intent.getIntExtra("index",0);

        CarContract t = activity_list_car.data.get(index).contract;
        if(t!=null){
            edtMaXe.setText(t.getId());
            edtSoNgay.setText(t.getSumDay());
            edtGiaXe.setText(t.getPrice());
        }
    }
}

//    onBackPressed(); finish();