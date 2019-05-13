package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import Classes.User;
import Api.CommonAPI;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements CommonAPI.Callback {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempt_login();
            }
        });
    }

    private void attempt_login() {
        new CommonAPI(this).attempt_login(binding.txtUsername.getText().toString().trim(),
                binding.txtPassword.getText().toString().trim(),
                getApplicationContext());
    }

    @Override
    public void onResponse(String response) {
        Log.e("e", "onResponse LoginActivity " + response);
        boolean flag_login_success = false;
        try {
            // parse json to a user
            Gson gson = new Gson();
            User user = gson.fromJson(response, User.class);
            // save id;
            User.logged_id = user.id;
            flag_login_success = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
        }

        if(flag_login_success){
            try {
                Intent intent = new Intent(this, FuncActivity.class);
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, AreaActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
