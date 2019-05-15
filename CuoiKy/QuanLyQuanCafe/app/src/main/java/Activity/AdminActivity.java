package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin);

        binding.btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AreaAdminActivity.class));
            }
        });

        binding.btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdminTableActivity.class));
            }
        });

        binding.btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CategoryAdminActivity.class));
            }
        });

        binding.btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdminProductActivity.class));
            }
        });
    }
}
