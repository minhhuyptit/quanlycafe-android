package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import Adapter.TableRecyclerViewAdapter;
import Api.CommonAPI;
import Classes.Area;
import Classes.Table;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityTableBinding;

public class TableActivity extends AppCompatActivity implements CommonAPI.Callback, TableRecyclerViewAdapter.Callback {
    ActivityTableBinding binding;
//    int area_id;
    DatabaseReference rootNotify = CommonAPI.root.child("notification");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_table);

        new CommonAPI(this).get_table(getApplicationContext(), Area.curent_id);

        rootNotify.child("kitchen").addChildEventListener(eventNotify);
       // rootNotify.addValueEventListener(valueEvnetNotify);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CommonAPI(this).get_table(getApplicationContext(), Area.curent_id);
    }

    @Override
    public void onResponse(String response) {

        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Table>>() {
            }.getType();

            List<Table> tables = gson.fromJson(response, collectionType);

            RecyclerView recyclerView = binding.rvTable;
            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 4);
            recyclerView.setLayoutManager(manager);
            TableRecyclerViewAdapter adapter = new TableRecyclerViewAdapter(tables, this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSelectTable(int table_id) {
        try {
            Table.current_id = table_id;
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    ChildEventListener eventNotify = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Toast.makeText(TableActivity.this, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
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
    ValueEventListener valueEvnetNotify = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Toast.makeText(TableActivity.this, dataSnapshot.child("kitchen").toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}

