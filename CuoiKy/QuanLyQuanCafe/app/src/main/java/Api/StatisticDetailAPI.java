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

public class StatisticDetailAPI implements Callback {
    static String Huy_ip = CommonAPI.Huy_ip;

    private Callback callback;

    public interface Callback {
        void getResponse(String response);
    }

    public StatisticDetailAPI(Callback callback) {
        this.callback = callback;
    }

    public void get_statistic(final Context context, int month, int year) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/statistic/sumRevenueByMonth/" + year + "/"+month;
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("e","on get_satistic_detail " + response);
                callback.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fsd", error.getMessage()+"");
                callback.getResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);
    }
}
