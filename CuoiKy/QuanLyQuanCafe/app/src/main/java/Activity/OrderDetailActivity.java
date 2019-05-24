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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Adapter.OrderDetailRecyclerViewAdapter;
import Api.KitchenAPI;
import Api.RootFirebase;
import Classes.OrderDetail;
import Classes.TableKitchen;
import Classes.TableOrder;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityOrderDetailBinding;

public class OrderDetailActivity extends AppCompatActivity{
    ActivityOrderDetailBinding binding;
    int position;
    List<OrderDetail> orderDetails = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        TableOrder tableOrder = null;
        try{
            tableOrder = TableKitchenActivity.tableOrders.get(position);
        } catch (Exception e){
            Toast.makeText(this, "Bàn này đã được người khác chọn", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        if(tableOrder.getChickenProduct()==null) return;
        Iterator<Map.Entry<String, Integer>> iterator = tableOrder.getChickenProduct().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            orderDetails.add(new OrderDetail(entry.getValue(), entry.getKey()));
        }
        setEvent();

        RootFirebase.rootTableKitchen.setValue("begin");
        binding.btnDoneOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RootFirebase.rootTableKitchen.setValue("end");
                OrderDetailActivity.this.finish();
            }
        });
    }

    void setEvent(){
        RecyclerView recyclerView = binding.rvOrderDetail;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        OrderDetailRecyclerViewAdapter adapter = new OrderDetailRecyclerViewAdapter(orderDetails);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RootFirebase.rootTableKitchen.setValue("order");
        //Khi quay lai phai hoi. Ban da pha che xong ban nay chua, neu chua xong thi cap nhat lai trang thai = order
    }
}
