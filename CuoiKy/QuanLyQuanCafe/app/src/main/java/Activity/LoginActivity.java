package Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import Api.CommonAPI;
import Classes.User;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements CommonAPI.Callback {
    ActivityLoginBinding binding;
    ImageView iv;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        iv = binding.imgLogo;
        init();
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
            Gson gson = new Gson();
            user = gson.fromJson(response, User.class);
            User.logged_id = user.id;
            User.logged_name = user.fullname;
            flag_login_success = true;
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            login_fail();
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
        }

        if (flag_login_success) {
            try {
                login_success();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void login_success() {
        binding.txtPassword.setVisibility(View.INVISIBLE);
        binding.txtUsername.setVisibility(View.INVISIBLE);
        binding.btnLogin.setVisibility(View.INVISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.login_success);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        AnimationSet as = new AnimationSet(true);
        as.setFillAfter(true);
        as.addAnimation(animation);
        as.addAnimation(animation1);
        iv.startAnimation(as);
        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        Intent intent = null;
        switch (user.accessLevel){
            case 1:
                intent = new Intent(this, FuncActivity.class);
                break;
            case 2:
                intent = new Intent(this, TableKitchenActivity.class);

                break;
            case 3:
                intent = new Intent(this, AreaActivity.class);
                break;
        }
        startActivity(intent);
    }

    private void login_fail() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.login_fail);
        iv.startAnimation(animation);
    }

    private void init() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        iv.startAnimation(animation);
    }
}
