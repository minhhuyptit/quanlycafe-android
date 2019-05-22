package Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import Adapter.CategoryAdminAdapter;
import Adapter.SalaryEmployeeAdapter;
import Adapter.UserAdminAdapter;
import Api.CommonAPI;
import Api.MenuAPI;
import Classes.Category;
import Classes.Salary;
import Classes.User;
import advance_control.MovableFloatingActionButton;
import xyz.khang.quanlyquancafe.R;

public class SalaryEmployeeActivity extends AppCompatActivity implements CommonAPI.Callback{

    ListView lv_salary_employee;
    SalaryEmployeeAdapter adapter;
    TextView tvID, tvUsername, tvFullname, tvPhone, tvSalary;
    ArrayList<Salary> listSalaryEmployee;
    int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_employee);
        mapping();
        listSalaryEmployee = new ArrayList<>();
        new CommonAPI(this).get_timeKeepingByUser(getApplicationContext(), id_user);

    }

    @Override
    public void onResponse(String response){
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Salary>>() {
            }.getType();
            listSalaryEmployee = gson.fromJson(response, collectionType);
            adapter = new SalaryEmployeeAdapter(this, R.layout.salary_employee_item, listSalaryEmployee);
            lv_salary_employee.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), CommonAPI.Network_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void mapping(){
        Bundle bundle = getIntent().getExtras();
        lv_salary_employee = (ListView) findViewById(R.id.lv_salary_employee);
        tvID = (TextView) findViewById(R.id.tvID);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvFullname = (TextView) findViewById(R.id.tvFullname);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvSalary = (TextView) findViewById(R.id.tvSalary);
        id_user = bundle.getInt("id");
        tvID.setText(String.valueOf(id_user));
        tvUsername.setText(bundle.getString("username"));
        tvFullname.setText(bundle.getString("fullname"));
        tvPhone.setText(bundle.getString("phone"));
        tvSalary.setText(String.valueOf(bundle.getInt("salary")));
    }

}
