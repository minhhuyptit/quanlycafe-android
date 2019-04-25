package activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import xyz.khang.quanlythuexedulich.R;
import xyz.khang.quanlythuexedulich.databinding.ActivityCarBinding;

public class CarActivity extends AppCompatActivity {

    ActivityCarBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"alo",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
