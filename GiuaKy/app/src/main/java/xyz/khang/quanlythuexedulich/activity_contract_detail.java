package xyz.khang.quanlythuexedulich;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import activities.MainActivity;
import model.CarContract;
import model.CarContract_adapter;

public class activity_contract_detail extends AppCompatActivity {
    private ListView listView;
    TextView tvMaHD, tvMaKH, tvNgayHD;
    CarContract_adapter adapter;
    ArrayList<CarContract> data = new ArrayList<>();
    DatabaseReference rootContrats = MainActivity.root.child("contracts");
    String maHD, maKH, ngayHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_detail);
        setControl();
        setEvent();

        Intent intent = getIntent();
        maHD = intent.getStringExtra("maHD");
        maKH = intent.getStringExtra("maKH");
        ngayHD = intent.getStringExtra("ngayHD");
        rootContrats.child(maHD).child("contractDetail").addValueEventListener(valueEventListener);

        tvMaHD.setText(maHD);
        tvMaKH.setText(maKH);
        tvNgayHD.setText(ngayHD);

    }
    private  void setControl(){
        listView = findViewById(R.id.listView);
        tvMaHD = findViewById(R.id.tvMaHD);
        tvMaKH = findViewById(R.id.tvMaKH);
        tvNgayHD = findViewById(R.id.tvNgayHD);
    }
    private  void setEvent(){

        adapter = new CarContract_adapter(this, R.layout.car_contract_itemrow,data);
        listView.setAdapter(adapter);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                data.add(new CarContract(snapshot.getKey(),
                        snapshot.child("giathue").getValue().toString(), snapshot.child("songaythue").getValue().toString()));
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
