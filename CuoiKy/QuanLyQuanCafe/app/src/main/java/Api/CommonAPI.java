package Api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;

import Classes.Product;
public class CommonAPI implements Callback {
    public static DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    public static String Huy_ip = "192.168.1.76:7777";
    public static String Network_error = "Connection fail";
    private Callback callback;

    public interface Callback {
        void onResponse(String response);
    }

    public CommonAPI(Callback callback) {
        this.callback = callback;
    }

    public void attempt_login(final String username, final String password, final Context context) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/login";
        StringRequest requestString = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("username", username);
                MyData.put("password", password);
                return MyData;
            }
        };
        requestQueue.add(requestString);
    }

    public void get_area(final Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/area";
        Log.d("IP", url);
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);
//        String fake_result = "[\n" +
//                "    {\n" +
//                "        \"id\": 1,\n" +
//                "        \"name\": \"Khu A\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 2,\n" +
//                "        \"name\": \"Khu B\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 3,\n" +
//                "        \"name\": \"Khu C\"\n" +
//                "    }\n" +
//                "]";
//        callback.onResponse(fake_result);
    }

    public void get_table(final Context context, int area) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/area/" + String.valueOf(area);
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                callback.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);

//        String fake_result = "[\n" +
//                "    {\n" +
//                "        \"id\": 1,\n" +
//                "        \"name\": \"Bàn A1\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 1,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 2,\n" +
//                "        \"name\": \"Bàn A2\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 1,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 3,\n" +
//                "        \"name\": \"Bàn A3\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 4,\n" +
//                "        \"name\": \"Bàn A4\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 5,\n" +
//                "        \"name\": \"Bàn A5\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 6,\n" +
//                "        \"name\": \"Bàn A6\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 7,\n" +
//                "        \"name\": \"Bàn A7\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 8,\n" +
//                "        \"name\": \"Bàn A8\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 9,\n" +
//                "        \"name\": \"Bàn A9\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 10,\n" +
//                "        \"name\": \"Bàn A10\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 11,\n" +
//                "        \"name\": \"Bàn A11\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 12,\n" +
//                "        \"name\": \"Bàn A12\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 13,\n" +
//                "        \"name\": \"Bàn A13\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 14,\n" +
//                "        \"name\": \"Bàn A14\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 15,\n" +
//                "        \"name\": \"Bàn A15\",\n" +
//                "        \"seat\": 10,\n" +
//                "        \"status\": 0,\n" +
//                "        \"id_area\": 1\n" +
//                "    }\n" +
//                "]";
//        callback.onResponse(fake_result);
    }

    public void get_table_kitchen(final Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/statistic/menuOrder";
        Log.d("IP", url);
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);

//        String fake_result = "[\n" +
//                "    {\n" +
//                "        \"id\": 12,\n" +
//                "        \"time_in\": \"2019-05-09 11:15:55\",\n" +
//                "        \"time_out\": \"2019-05-09 11:15:55\",\n" +
//                "        \"status\": 0,\n" +
//                "        \"total\": 769000,\n" +
//                "        \"id_user_in\": 2,\n" +
//                "        \"id_user_out\": 1,\n" +
//                "        \"id_table\": 12\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"id\": 11,\n" +
//                "        \"time_in\": \"2019-05-09 11:25:55\",\n" +
//                "        \"time_out\": \"2019-02-18 10:14:35\",\n" +
//                "        \"status\": 0,\n" +
//                "        \"total\": 409000,\n" +
//                "        \"id_user_in\": 3,\n" +
//                "        \"id_user_out\": 2,\n" +
//                "        \"id_table\": 11\n" +
//                "    }\n" +
//                "]";
//        callback.onResponse(fake_result);
    }
}
