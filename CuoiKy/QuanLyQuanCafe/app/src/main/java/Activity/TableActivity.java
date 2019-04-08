package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import Adapter.TableRecyclerViewAdapter;
import Api.CommonAPI;
import Classes.Table;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityTableBinding;

public class TableActivity extends AppCompatActivity implements CommonAPI.Callback, TableRecyclerViewAdapter.Callback {
    ActivityTableBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_table);

        Intent intent = getIntent();
        new CommonAPI(this).get_table(getApplicationContext(), intent.getIntExtra("area_id", 0));
    }

    @Override
    public void onResponse(String response) {
        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Table>>() {
            }.getType();

            List<Table> tables = gson.fromJson(response, collectionType);

            RecyclerView recyclerView = binding.rvTable;
            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
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
}
