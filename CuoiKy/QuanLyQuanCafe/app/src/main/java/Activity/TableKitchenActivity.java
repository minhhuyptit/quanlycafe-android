package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import Adapter.TableKitchenRecyclerViewAdapter;
import Api.CommonAPI;
import Api.RootFirebase;
import Classes.TableKitchen;
import Classes.TableOrder;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityTableKitchenBinding;

public class TableKitchenActivity extends AppCompatActivity implements TableKitchenRecyclerViewAdapter.Callback {
    ActivityTableKitchenBinding binding;
    public static List<TableOrder> tableOrders;
    TableKitchenRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_table_kitchen);
        tableOrders = new ArrayList<>();
        setEvent();
        Query queryChickenCart = RootFirebase.rootKitchen.child(RootFirebase.getDay()).orderByChild("status").equalTo("order");
        queryChickenCart.addChildEventListener(eventListener);
    }

    void setEvent() {
        RecyclerView recyclerView = binding.rvTable;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        adapter = new TableKitchenRecyclerViewAdapter(this, tableOrders);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSelectTable(int position) {
        RootFirebase.rootTableKitchen = RootFirebase.rootKitchen.child(RootFirebase.getDay()).child(tableOrders.get(position).getKey()).child("status");
        Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    ChildEventListener eventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.e("onChildAdded", "--");

            for(TableOrder tableOrder: tableOrders)
                if(dataSnapshot.getKey().equals(tableOrder.getKey())) return;
            TableOrder tableOrder = dataSnapshot.getValue(TableOrder.class);
            tableOrder.setKey(dataSnapshot.getKey());
            Toast.makeText(TableKitchenActivity.this, tableOrder.getTable() + ": Vừa order", Toast.LENGTH_SHORT).show();
            tableOrders.add(tableOrder);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Toast.makeText(TableKitchenActivity.this, "Changed " + dataSnapshot.toString(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            TableOrder tableOrder = dataSnapshot.getValue(TableOrder.class);
            for(TableOrder t: tableOrders){
                if(t.getKey().equals(dataSnapshot.getKey())){
                    tableOrders.remove(t);
                    return;
                }
            }
            Toast.makeText(TableKitchenActivity.this, tableOrder.getTable() + ": Bắt đầu được làm nước", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
