package Api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class KitchenAPI {

    private Context context;
    private Callback callback;

    public KitchenAPI(Callback callback,Context context) {
        this.context = context;
        this.callback = callback;
    }

    public interface Callback {
        void onGetBillDetailResponse(String response);

        void onPutBillDetailResponse(String response);
    }

    public void getBillDetailResponse(int order_id) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/bill/" + (order_id);
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onGetBillDetailResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onGetBillDetailResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);
    }

    public void putDetailResponse(int order_id) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/bill/madeMenu/" + (order_id);
        StringRequest requestString = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onPutBillDetailResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onPutBillDetailResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);
    }
}
