package xyz.khang.quanlyquancafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import Activity.OrderDetailActivity;
import Activity.StatisticDetailActivity;
import Api.StatisticAPI;
import Classes.Category;

public class RevenueChart extends AppCompatActivity implements StatisticAPI.Callback{
    BarChart barChart;
    String startDay, endDay;
    Button btnNex, btnPre;
    int yearStatistic;
    TextView tvTitle;
    List<SumRevenue> sumRevenues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue_chart);
        setControl();

        Calendar calendar = Calendar.getInstance();
        final int year = yearStatistic = calendar.get(Calendar.YEAR);


        new StatisticAPI(this).get_statistic(getApplicationContext());

        btnNex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yearStatistic<year){
                    yearStatistic++;
                    createChart(yearStatistic);
                }
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yearStatistic>2016){
                    yearStatistic--;
                    createChart(yearStatistic);
                }
            }
        });

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if(sumRevenues.size()<=e.getXIndex()) return;
                Intent intent = new Intent(getApplicationContext(), StatisticDetailActivity.class);
                intent.putExtra("year", yearStatistic);
                intent.putExtra("month", sumRevenues.get(e.getXIndex()).month);
                startActivity(intent);
                Toast.makeText(RevenueChart.this, "  Index: " + e.getXIndex(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    void createChart(int year){
        tvTitle.setText("DOANH THU NĂM " + year);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> theDates = new ArrayList<>();
        int i=0;
        for(SumRevenue sumRevenue: sumRevenues){
            if(sumRevenue.year==year){
                float val = Math.round(sumRevenue.doanhthu/100000)/10;
                barEntries.add(new BarEntry(val, i));
                theDates.add("" + sumRevenue.month);
                i++;
            }
        }
        //-----------------------Cho gia tri random
        if(barEntries.size()==0){
            for(i=0; i<12; i++){
                float val = (float) (Math.random())*100000000;
                val = Math.round(val/100000)/10;
                barEntries.add(new BarEntry(val, i));
                theDates.add("" + i);
            }
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Doanh thu năm " + year);
        barDataSet.setValueTextSize(10f);

        BarData barData = new BarData(theDates, barDataSet);
        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        barChart.invalidate();
    }

    void setControl(){
        barChart = findViewById(R.id.barChart);
        btnNex = findViewById(R.id.btnNex);
        btnPre = findViewById(R.id.btnPre);
        tvTitle = findViewById(R.id.tvTitle);
    }

    @Override
    public void getResponse(String response) {
//        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<SumRevenue>>() {}.getType();
        try{
            sumRevenues = gson.fromJson(response, collectionType);
            createChart(yearStatistic);
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
class SumRevenue{
    int month;
    int year;
    double doanhthu;

    public SumRevenue() {
    }

    public SumRevenue(int month, int year, double doanhthu) {
        this.month = month;
        this.year = year;
        this.doanhthu = doanhthu;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(double doanhthu) {
        this.doanhthu = doanhthu;
    }
}
