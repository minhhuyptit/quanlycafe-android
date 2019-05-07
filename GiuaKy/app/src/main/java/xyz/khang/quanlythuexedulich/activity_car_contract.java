package xyz.khang.quanlythuexedulich;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import activities.MainActivity;
import activity.CustomerActivity;
import model.Car;
import model.CarContract;
import model.CarContract_adapter;
import model.Contract;
import model.ContractDetail;

public class activity_car_contract extends AppCompatActivity {
    private ListView listView;
    ArrayList<CarContract> data = new ArrayList<>();
    CarContract_adapter adapter;
    EditText edtSoHD, edtMaKH, edtNgayHD;
    Button btnHoanThanh;
    DatabaseReference rootContrats = MainActivity.root.child("contracts");
    DatabaseReference rootContratDetail = MainActivity.root.child("contractdetail");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_contract);
        setControl();
        setEvent();
        edtMaKH.setText(CustomerActivity.maKH);
        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHoanThanhClick();
            }
        });
    }

    private  void setControl(){
        listView = findViewById(R.id.listView);
        edtSoHD = findViewById(R.id.edtSoHD);
        edtMaKH = findViewById(R.id.edtMaKH);
        edtNgayHD = findViewById(R.id.edtNgayHD);
        btnHoanThanh = findViewById(R.id.btnHoanThanh);
    }
    private  void setEvent(){
        edtNgayHD.setText(XuatNgayThangNam());
        getData();
        adapter = new CarContract_adapter(this, R.layout.car_contract_itemrow,data);
        listView.setAdapter(adapter);
    }
    void getData(){
        data.clear();
        for(Car car: activity_list_car.data)
            if(car.isHasChoose()==true) data.add(car.contract);
    }
    void onHoanThanhClick(){
        String soHD, maKH, ngayHD;
        soHD = edtSoHD.getText().toString();
        maKH = edtMaKH.getText().toString();
        ngayHD = edtNgayHD.getText().toString();
        if(soHD.equals("") || maKH.equals("") || ngayHD.equals("")){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
            return;
        }
        //Luu du lieu len database
        Contract contract = new Contract(ngayHD, maKH);
        for (CarContract c: data)
            contract.contractDetail.put(c.getId(), new ContractDetail(Integer.parseInt(c.getSumDay()), Double.parseDouble(c.getPrice())));
        rootContrats.child(soHD).setValue(contract);
        //Xoa du lieu
        activity_list_car.data.clear();
        //tro ve man hinh chinh
        Intent intent = new Intent(activity_car_contract.this, activity.CustomerActivity.class);
        startActivity(intent);
    }

    public static String XuatNgayThangNam()
    {
        //Tạo đối tượng date sẽ lấy date hiện tại
        Date date = new Date();
        //Muốn xuất Ngày/Tháng/Năm , ví dụ 12/04/2013
        String strDateFormat = "dd/MM/yyyy";
        //tạo đối tượng SimpleDateFormat;
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        //gọi hàm format để lấy chuỗi ngày tháng năm đúng theo yêu cầu
        return sdf.format(date);
    }
}
