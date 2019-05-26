package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Adapter.StatisticDetailRecyclerViewAdapter;
import Api.StatisticDetailAPI;
import Classes.ProductDetailStatistic;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityStaticsticDetailBinding;

public class StatisticDetailActivity extends AppCompatActivity implements StatisticDetailAPI.Callback{
    ActivityStaticsticDetailBinding binding;
    int year, month;
    List<ProductDetailStatistic> statisticDetails = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_staticstic_detail);
        Intent intent = getIntent();
        year = intent.getIntExtra("year", -1);
        month = intent.getIntExtra("month",-1);

        binding.tvMonth.setText("DOANH THU THÁNG " + month);
        new StatisticDetailAPI(this).get_statistic(getApplicationContext(), month, year);
    }

    void setEvent(){
        RecyclerView recyclerView = binding.rvStatisticDetail;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        StatisticDetailRecyclerViewAdapter adapter = new StatisticDetailRecyclerViewAdapter(statisticDetails);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void getResponse(String response) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ProductDetailStatistic>>() {}.getType();
        try{
            statisticDetails = gson.fromJson(response, collectionType);
            float sum = 0;
            for(ProductDetailStatistic p: statisticDetails) sum+=p.getPrice()*p.getQuantity();
            setEvent();
            String s = String.format("%,d", (int) (sum));
            binding.tvSum.setText("TỔNG: " + s + " Đ");
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
