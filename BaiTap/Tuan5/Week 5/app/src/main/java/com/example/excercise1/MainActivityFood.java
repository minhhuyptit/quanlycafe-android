package com.example.excercise1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivityFood extends AppCompatActivity {
    private ListView listView;
    ArrayList<Food> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }
    private void setControl(){
        listView = findViewById(R.id.listView);
    }
    private void setEvent(){
        KhoiTao();
        FoodAdapter foodAdapter = new FoodAdapter(this, R.layout.list_item_layout_2, foods);
        listView.setAdapter(foodAdapter);
    }
    void KhoiTao(){
        foods.add(new Food("fsdfdsf",R.mipmap.vi, "sdfsdfsdsdfsdfsdaf"));
        foods.add(new Food("fsdfdsf",R.mipmap.vi, "sdfsdfsdsdfsdfsdaf"));
        foods.add(new Food("fsdfdsf",R.mipmap.vi, "sdfsdfsdsdfsdfsdaf"));
        foods.add(new Food("fsdfdsf",R.mipmap.vi, "sdfsdfsdsdfsdfsdaf"));

    }
}
