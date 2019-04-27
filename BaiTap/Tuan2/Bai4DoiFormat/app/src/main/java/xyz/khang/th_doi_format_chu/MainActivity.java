package xyz.khang.th_doi_format_chu;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import java.util.Random;

import xyz.khang.th_doi_format_chu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_result();
            }
        });
    }

    private void show_result(){
        // Checkbox Background
        if(binding.chbBackground.isChecked()){
            binding.txtResult.setBackgroundColor(Color.parseColor("#FFFF00"));
        }
        else {
            binding.txtResult.setBackgroundColor(Color.parseColor("#EEEEEE"));
        }

        // Checkbox Textcolor
        if(binding.chbTextColor.isChecked()){
            binding.txtResult.setTextColor(Color.parseColor("#FF0000"));
        }
        else {
            binding.txtResult.setTextColor(Color.parseColor("#000000"));
        }

        // Checkbox Center
        if(binding.chbCenter.isChecked()){
            binding.txtResult.setGravity(Gravity.CENTER);
        }
        else {
            binding.txtResult.setGravity(Gravity.LEFT);
        }

        if(binding.rdBoth.isChecked()){
            binding.txtResult.setText(String.valueOf(random()));
        }

        if(binding.rdEven.isChecked()){
            int i = random();
            binding.txtResult.setText(String.valueOf(i-(i%2)));
        }

        if(binding.rdOdd.isChecked()){
            int i = random();
            binding.txtResult.setText(String.valueOf(i-(1-(i%2))));
        }
    }

    private int random(){
        Random r = new Random();
        int i1 = r.nextInt(100);
        return  i1;
    }
}
