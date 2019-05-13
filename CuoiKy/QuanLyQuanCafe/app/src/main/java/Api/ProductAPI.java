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

public class ProductAPI implements Callback {
    static String Huy_ip = CommonAPI.Huy_ip;
    private Callback callback;

    public interface Callback {
        void errorProductResponse(String response);
        void getProductResponse(String response);
        void deleteProductResponse(String response);
        void postProductResponse(String response);
        void updateProductResponse(String response);
    }

    public ProductAPI(Callback callback) {
        this.callback = callback;
    }

    public void get_product(final Context context, String idCategory) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/category/" + idCategory;
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "on get_product " + response);
                callback.getProductResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.errorProductResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);
    }

    public void delete_product (final Context context, String id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/product/" + id;
        StringRequest requestString = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "remove_product " + response);
                callback.deleteProductResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.errorProductResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);
    }

    public void post_product (final Context context, final Product product){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/product";
        StringRequest requestString = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "post_product  " + response);
                callback.postProductResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.errorProductResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", product.id);
                params.put("name", product.name);
                params.put("price", String.valueOf(product.price));
                params.put("description", product.description);
                params.put("id_category", product.idCategory);
                return params;
            }
        };
        requestQueue.add(requestString);
    }

    public void update_product (final Context context, final Product product){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + Huy_ip + "/quanlycafe/public/api/product/" + product.id;
        StringRequest requestString = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "update_product  " + response);
                callback.updateProductResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.errorProductResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", product.name);
                params.put("price", String.valueOf(product.price));
                params.put("description", product.description);
                return params;
            }
        };
        requestQueue.add(requestString);
    }
}
