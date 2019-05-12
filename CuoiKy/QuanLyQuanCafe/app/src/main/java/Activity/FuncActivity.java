package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import Classes.Table;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityFuncBinding;

public class FuncActivity extends AppCompatActivity {
    ActivityFuncBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_func);

        binding.btnDatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AreaActivity.class);
                startActivity(intent);
            }
        });

        binding.btnBep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TableKitchenActivity.class);
                startActivity(intent);
            }
        });
    }
}
