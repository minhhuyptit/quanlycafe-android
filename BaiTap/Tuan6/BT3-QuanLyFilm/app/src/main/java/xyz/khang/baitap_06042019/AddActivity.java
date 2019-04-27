package xyz.khang.baitap_06042019;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xyz.khang.baitap_06042019.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Film f = new Film();
                f.name = binding.txtName.getText().toString();
                f.vietSub = binding.txtSub.getText().toString();
                f.rate = (int) binding.ratingBar.getRating();
                Films.list.add(f);
                finish();
            }
        });
    }
}
