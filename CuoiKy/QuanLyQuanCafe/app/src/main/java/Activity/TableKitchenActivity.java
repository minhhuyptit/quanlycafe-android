package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Adapter.TableKitchenRecyclerViewAdapter;
import Api.CommonAPI;
import Classes.TableKitchen;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityTableKitchenBinding;

public class TableKitchenActivity extends AppCompatActivity implements CommonAPI.Callback, TableKitchenRecyclerViewAdapter.Callback {
    public static final int E = Log.e("a", "asdf");
    ActivityTableKitchenBinding binding;
    String current_response = null;
    CommonAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_table_kitchen);
        api = new CommonAPI(this);
        api.get_table_kitchen(getApplicationContext());
//        handler.post(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        try {
//            Log.e("onResume", "onResume" );
//
//            handler.post(runnable);
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

    }

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            api.get_table_kitchen(getApplicationContext());
            Log.e("a", "a" );

            try{
                Log.e("try", "try" );
                handler.post(runnable);
            } catch (Exception e) {
                Log.e("catch", e.getMessage() );

//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onResponse(String response) {
//        if(response==null)return;
//        if (current_response != null) {
//            if (response.equals(current_response)) {
//                Log.e("b", "b" );
//
//            } else {
//                Log.e("c", "c" );

                try {
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<Collection<TableKitchen>>() {
                    }.getType();

                    List<TableKitchen> tableKitchenList = gson.fromJson(response, collectionType);

                    if(tableKitchenList==null) {
                        Thread.sleep(4000);
                        Log.e("e", tableKitchenList.size()+"");
                        api.get_table_kitchen(getApplicationContext());
                        return;
                    }
                    RecyclerView recyclerView = binding.rvTable;
                    GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
                    recyclerView.setLayoutManager(manager);
                    TableKitchenRecyclerViewAdapter adapter = new TableKitchenRecyclerViewAdapter(this, tableKitchenList);
                    recyclerView.setAdapter(adapter);
                    Thread.sleep(4000);
                    Log.e("i", tableKitchenList.size()+"");
                    api.get_table_kitchen(getApplicationContext());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
//            }
//        }
//        current_response = response;
    }

    @Override
    public void onSelectTable(int order_id) {
        handler.removeCallbacks(runnable);

        Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
        intent.putExtra("order_id", order_id);
        startActivity(intent);
    }
}
