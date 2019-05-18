package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Adapter.TableKitchenRecyclerViewAdapter;
import Api.CommonAPI;
import Classes.TableKitchen;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityTableKitchenBinding;

public class TableKitchenActivity extends AppCompatActivity implements CommonAPI.Callback, TableKitchenRecyclerViewAdapter.Callback {
    ActivityTableKitchenBinding binding;
    CommonAPI api;
    List<TableKitchen> tableKitchenList;
    TableKitchenRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    int size;
    Thread t1;
    boolean stopRunbale=false, exit=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_table_kitchen);
        tableKitchenList = new ArrayList<>();
        api = new CommonAPI(this);
        setEvent();
        api.get_table_kitchen(getApplicationContext());
    }

    void setEvent() {
        recyclerView = binding.rvTable;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        adapter = new TableKitchenRecyclerViewAdapter(this, tableKitchenList);
        recyclerView.setAdapter(adapter);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(!stopRunbale){
                stopRunbale=true;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                api.get_table_kitchen(getApplicationContext());
            }
        }
    };

    @Override
    public void onResponse(String response) {
        stopRunbale=false;
        if(response==null){
            t1 = new Thread(runnable);
            t1.start();
            return;
        }
        Log.e("Receive response", response);
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<TableKitchen>>() {
        }.getType();
        try {
            tableKitchenList = gson.fromJson(response, collectionType);
            //Kiem tra neu co su thay doi du lieu moi load lai man hinh
            if (tableKitchenList.size() != size) {
                Log.e("i", tableKitchenList.size() + "");
                size = tableKitchenList.size();
//                adapter.notifyDataSetChanged();
                setEvent();
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
        if(!exit){
            t1 = new Thread(runnable);
            t1.start();
        }
    }

    @Override
    public void onSelectTable(int order_id) {
        Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
        intent.putExtra("order_id", order_id);
        startActivity(intent);
        stopRunbale=exit=true;
        Log.e("onSelectTable", "----------------");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopRunbale=exit=true;
        Log.e("onBack", "----------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopRunbale = exit = false;
        Log.e("onResume", "----------------");
        api.get_table_kitchen(getApplicationContext());
    }
}
