package xyz.khang.quanlythuexedulich;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import activities.MainActivity;
import model.CarContract;
import model.Contract;
import model.ContractModel;
import model.Contract_adapter;

public class activity_contracts extends AppCompatActivity {
    ListView listView;
    ArrayList<ContractModel> data = new ArrayList<>();
    ArrayList<CarContract> carContracts = new ArrayList<>();
    Contract_adapter adapter;
    DatabaseReference rootContrats = MainActivity.root.child("contracts");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contracts);
        setControl();
        setEvent();
        rootContrats.addValueEventListener(valueEventListener);

        // Chọn listview item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContractModel contractModel = data.get(position);
                int index = position;
                Toast.makeText(getApplicationContext(),index + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity_contracts.this, activity_contract_detail.class);
                intent.putExtra("maHD", contractModel.getMaHD());
                intent.putExtra("maKH", contractModel.getMaKH());
                intent.putExtra("ngayHD", contractModel.getNgayHD());
                startActivity(intent);
            }
        });
    }
    private  void setControl(){
        listView = findViewById(R.id.listView);
    }
    private  void setEvent(){
        //KhoiTao();
        adapter = new Contract_adapter(this, R.layout.contract_itemrow,data);
        listView.setAdapter(adapter);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                data.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Contract c = snapshot.getValue(Contract.class);
                    ContractModel contractModel = new ContractModel(snapshot.getKey(),c.getMAKH(), c.getNGAYHD());
                    Log.e("contract", contractModel.toString());
                    data.add(contractModel);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
