package activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import xyz.khang.quanlythuexedulich.R;
import xyz.khang.quanlythuexedulich.activity_list_car;
import xyz.khang.quanlythuexedulich.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCarClick();
            }
        });
        binding.btnContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContractClick();
            }
        });
        binding.btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCustomerClick();
            }
        });
    }

    private void onCarClick(){
        Toast.makeText(getApplicationContext(),"Car",Toast.LENGTH_SHORT).show();
    }

    private void onCustomerClick(){
        Toast.makeText(getApplicationContext(),"CustomerActivity",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,CustomerActivity.class);
        this.startActivity(intent);
    }

    private void onContractClick(){
        Toast.makeText(getApplicationContext(),"Contract",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, activity_list_car.class);
        startActivity(intent);
    }
}
