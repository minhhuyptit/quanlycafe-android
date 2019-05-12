package activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import xyz.khang.quanlythuexedulich.R;
import xyz.khang.quanlythuexedulich.activity_contracts;
import xyz.khang.quanlythuexedulich.activity_list_car;
import xyz.khang.quanlythuexedulich.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static DatabaseReference root;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        root = FirebaseDatabase.getInstance().getReference();
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
        Intent intent = new Intent(this, activities.CarActivity.class);
        startActivity(intent);
    }

    private void onCustomerClick(){

        Toast.makeText(getApplicationContext(),"Customer",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,activity.CustomerActivity.class));
    }

    private void onContractClick(){
        Toast.makeText(getApplicationContext(),"Contract",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, activity_contracts.class);
        startActivity(intent);
    }
}
