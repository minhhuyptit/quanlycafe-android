package xyz.khang.baitapwebservice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import xyz.khang.baitapwebservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String url = "http://cdn.crunchify.com/wp-content/uploads/code/jsonArray.txt";
            StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    SetRV(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                protected Map<String, String> getParams() {
                    return null;
                }
            };
            requestQueue.add(requestString);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void SetRV(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Object>>() {
            }.getType();
            List<Object> objectList = gson.fromJson(response, collectionType);

            RecyclerView recyclerView = binding.rvMain;
            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerView.setLayoutManager(manager);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(objectList);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
