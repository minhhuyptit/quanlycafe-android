package xyz.khang.quanlythuexedulich;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import model.Car;
import model.CarContract;
import model.Car_adapter;

public class activity_list_car extends AppCompatActivity {
    private ListView listView;
    public static Car_adapter adapter;
    private Button btnTiepTuc;
    private CheckBox cbHasChoose;
    public static ArrayList<Car> data = new ArrayList<>();

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
    }

    private  void setControl(){
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        listView = findViewById(R.id.listView);
        cbHasChoose = findViewById(R.id.cbHasChoose);
    }

    private  void setEvent(){
        KhoiTao();
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
        Intent intent = new Intent(activity_list_car.this, activity_car_contract.class);
        startActivity(intent);
    }
}
