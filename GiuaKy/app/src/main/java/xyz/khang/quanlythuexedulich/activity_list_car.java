package xyz.khang.quanlythuexedulich;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import activities.MainActivity;
import activity.CustomerActivity;
import model.Car;
import model.CarContract;
import model.Car_adapter;
import model.Contract;
import model.ContractDetail;

public class activity_list_car extends AppCompatActivity {
    private ListView listView;
    public static Car_adapter adapter;
    private Button btnTiepTuc;
    private CheckBox cbHasChoose;
    public static ArrayList<Car> data = new ArrayList<>();
    DatabaseReference rootCar = MainActivity.root.child("cars");
    DatabaseReference rootContrats = MainActivity.root.child("contracts");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car);
        setControl();
        setEvent();

        //Click tiep tuc
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTiepTucClick();
            }
        });
        //Click back

        // Chọn listview item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = data.get(position);
                int index = position;

                Toast.makeText(getApplicationContext(),index + "",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity_list_car.this, activity_car_detail.class);
                intent.putExtra("id", car.getId());
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });

        //rootCar.addValueEventListener(valueEventListener);
        rootCar.addChildEventListener(childEventListener);
        //Query
        Query queryContracts = rootContrats.orderByChild("status").equalTo(true);
        queryContracts.addValueEventListener(valueEventListenerContracts);

    }
    public void onBackPressed(){
        Intent intent = new Intent(activity_list_car.this, CustomerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private  void setControl(){
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        listView = findViewById(R.id.listView);
        cbHasChoose = findViewById(R.id.cbHasChoose);
    }

    private  void setEvent(){
        //KhoiTao();
        adapter = new Car_adapter(this, R.layout.car_itemrow,data);
        listView.setAdapter(adapter);
    }
    void KhoiTao(){
        if(data.size()>0) return;
        data.add(new Car("1", "a", "Japan", false));
        data.add(new Car("2", "d", "Japan", false));
        data.add(new Car("3", "d", "Japan", false));
        data.add(new Car("4", "c", "Japan", false));
        data.add(new Car("5", "e", "Japan", false));
        data.add(new Car("6", "c", "Japan", false));
        data.add(new Car("7", "g", "Japan", false));
        data.add(new Car("8", "e", "Japan", false));
        data.add(new Car("9", "n", "Japan", false));
        data.add(new Car("10", "f", "Japan", false));
    }

    void onTiepTucClick(){  //Chuyen sang man hinh Hop Dong

        for(Car c: data){
            if(c.isHasChoose()){
                Intent intent = new Intent(activity_list_car.this, activity_car_contract.class);
                startActivity(intent);
                return;
            }
        }
        Toast.makeText(this, "Chưa có xe nào được chọn!", Toast.LENGTH_SHORT).show();
    }
    boolean existCar(String MAXE){
        for(Car c: data)
            if(c.getId().equals(MAXE)) return true;
        return false;
    }

    //ValueEventListener---------------------------------------------------
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                data.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    classes.Car c = snapshot.getValue(classes.Car.class);
                    Car car = new Car(c.MAXE, c.TENXE, c.XUATXU, false);
                    data.add(car);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    //
    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            if(dataSnapshot.exists()){
                classes.Car c = dataSnapshot.getValue(classes.Car.class);
                if(!existCar(c.MAXE)){
                    Car car = new Car(c.MAXE, c.TENXE, c.XUATXU, false);
                    data.add(car);
                    adapter.notifyDataSetChanged();
                }
            }else{
                Toast.makeText(activity_list_car.this, "Tất cả các xe đã được đặt", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    //ValueEvenListenner
    ValueEventListener valueEventListenerContracts = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                boolean check;
                int sumCars;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){         //duyet moi hop dong
                    Log.e("Hop dong", snapshot.getKey()+"------");
                    Contract contract = snapshot.getValue(Contract.class);
                    sumCars = contract.contractDetail.size();
                    Iterator<Map.Entry<String, ContractDetail>> iterator = contract.contractDetail.entrySet().iterator();
                    while (iterator.hasNext()) {                                //duyet cac xe trong hop dong
                        final Map.Entry<String, ContractDetail> entry = iterator.next();
                        System.out.println(entry.getKey() + " " +entry.getValue().getSONGAYTHUE());
                        check = checkDate(contract.getNGAYHD(), entry.getValue().getSONGAYTHUE());
                        Log.e("check", check + "--");
                        if(check==true){                                        //neu xe dang duoc su dung thi loai bo ra
                            for(Car c: data){                                   //xe dang duoc thue => xoa phan tu trong data
                                if(c.getId().equals(entry.getKey())){
                                    data.remove(c);
                                    break;
                                }
                            }
                        }else sumCars--;
                    }
                    //Cap nhat status cua hop dong
                    if(sumCars==0)      //hop dong het hieu luc
                        rootContrats.child(snapshot.getKey()).child("status").setValue(false);
                }

                //cap nhat adapter xe
                Log.e("Cap nhat adapter", "-----------");
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    boolean checkDate(String  timeStart, int sumDay){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(timeStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar start = Calendar.getInstance();
        Calendar now = Calendar.getInstance();

        start.setTime(date);
        now.setTime(new Date());

        start.add(Calendar.DATE, sumDay);

        if (start.after(now)) return true;
        return false;
    }
}
