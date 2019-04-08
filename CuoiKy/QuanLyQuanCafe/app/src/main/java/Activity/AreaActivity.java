package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import Adapter.AreaRecyclerViewAdapter;
import Api.CommonAPI;

import Classes.Area;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityAreaBinding;

public class AreaActivity extends AppCompatActivity implements CommonAPI.Callback, AreaRecyclerViewAdapter.Callback {
    ActivityAreaBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_area);
        //
        new CommonAPI(this).get_area(getApplicationContext());
    }

    @Override
    public void onResponse(String response) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Area>>() {
        }.getType();
        List<Area> areas = gson.fromJson(response, collectionType);

        RecyclerView recyclerView = binding.rvArea;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        AreaRecyclerViewAdapter adapter = new AreaRecyclerViewAdapter(areas, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSelectArea(int area_id) {
        try{
            Intent intent = new Intent(this, TableActivity.class);
            intent.putExtra("area_id", area_id);
            startActivity(intent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
