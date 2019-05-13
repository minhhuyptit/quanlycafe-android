package Api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;

import Classes.Product;

public class CategoryAPI implements Callback {
    static String Huy_ip = CommonAPI.Huy_ip;
    private Context context;
    private Callback callback;

    public interface Callback {
        void errorCategoryResponse(String response);
        void getCategoryResponse(String response);
        void postCategoryResponse(String response);
        void deleteCategoryResponse(String response);
    }

    public CategoryAPI(Context context, Callback callback) {
        this.callback = callback;
        this.context = context;
    }

    public void get_category() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/category";
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "on get_category " + response);
                //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                callback.getCategoryResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.errorCategoryResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);
    }
}
