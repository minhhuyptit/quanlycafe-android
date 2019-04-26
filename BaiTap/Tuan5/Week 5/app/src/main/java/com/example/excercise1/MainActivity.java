package com.example.excercise1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<Country> countries = new ArrayList<>();

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
        CountryAdapter countryAdapter = new CountryAdapter(this, R.layout.list_item_layout, countries);
        listView.setAdapter(countryAdapter);
    }
    void KhoiTao(){
        countries.add(new Country("Viet Nam", R.mipmap.vi, 76767));
        countries.add(new Country("Russia", R.mipmap.ru, 76767));
        countries.add(new Country("United States", R.mipmap.us, 76767));
    }
}
