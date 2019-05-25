package xyz.khang.quanlyquancafe;

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


import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Api.StatisticAPI;

public class RevenueChart extends AppCompatActivity implements StatisticAPI.Callback{
    BarChart barChart;
    String startDay, endDay;
    Button btnNex, btnPre;
    int yearStatistic;
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue_chart);
        setControl();

        Calendar calendar = Calendar.getInstance();
        final int year = yearStatistic = calendar.get(Calendar.YEAR);
        createChart(yearStatistic);

//        startDay = year + "0101";
//        endDay = year + "1231";
//        new StatisticAPI(this).post_statistic(getApplicationContext(), startDay, endDay);

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
        for(int i=0; i<12; i++){
            float val = (float) (Math.random())*100000000;
            val = Math.round(val/100000)/10;
            barEntries.add(new BarEntry(val, i));
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Doanh thu năm " + year);

        ArrayList<String> theDates = new ArrayList<>();
        for(int i=1; i<=12; i++)
            theDates.add("" + i);

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
        Gson gson = new Gson();

    }
}
