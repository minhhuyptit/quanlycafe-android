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

import javax.security.auth.callback.Callback;

public class CommonAPI implements Callback {
    static String Huy_ip = "192.168.43.196:7777";
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
                Map<String, String> MyData = new HashMap<>();
                MyData.put("username", username);
                MyData.put("password", password);
                return MyData;
            }
        };
        requestQueue.add(requestString);

//        String fake_result = "{\n" +
//                "    \"id\": 1,\n" +
//                "    \"username\": \"admin1\",\n" +
//                "    \"fullname\": \"Nguyễn Hà Minh Huy\",\n" +
//                "    \"phone\": \"0523939339\",\n" +
//                "    \"image\": \"image_1.png\",\n" +
//                "    \"salary\": 15000000,\n" +
//                "    \"access_level\": 1\n" +
//                "}";
//        callback.onResponse(fake_result);
    }

    public void get_area(final Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/area";
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
}
