package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import Adapter.OrderDetailRecyclerViewAdapter;
import Api.KitchenAPI;
import Classes.OrderDetail;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityOrderDetailBinding;

public class OrderDetailActivity extends AppCompatActivity implements KitchenAPI.Callback {

    ActivityOrderDetailBinding binding;
    int bill_id;
    KitchenAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);

        Intent intent = getIntent();
        bill_id = intent.getIntExtra("order_id", -1);
        Toast.makeText(getApplicationContext(), String.valueOf(bill_id), Toast.LENGTH_SHORT).show();

        api = new KitchenAPI(this, getApplicationContext());
        api.getBillDetailResponse(bill_id);

        binding.btnDoneOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.putDetailResponse(bill_id);
            }
        });
    }

    @Override
    public void onGetBillDetailResponse(String response) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<OrderDetail>>() {
        }.getType();
        List<OrderDetail> orderDetails = gson.fromJson(response, collectionType);

        RecyclerView recyclerView = binding.rvOrderDetail;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        OrderDetailRecyclerViewAdapter adapter = new OrderDetailRecyclerViewAdapter(orderDetails);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPutBillDetailResponse(String response) {
        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
