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

public class MenuAPI {
    private Callback callback;
    private Context context;

    public interface Callback {
        void onGetTableCartResponse(String response);

        void onGetCategoryResponse(String response);

        void onGetProductResponse(String response);

        void onPostOrder(String response);

        void onPutCheck(String response);
    }

    public MenuAPI(Callback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void get_table_cart(int table_id) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/table/" + String.valueOf(table_id);
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                callback.onGetTableCartResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onGetTableCartResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);

//        String fake_result_2 = "{  \n" +
//                "   \"cartItem\":{  \n" +
//                "      \"cm\":{  \n" +
//                "         \"product\":{  \n" +
//                "            \"id\":\"cm\",\n" +
//                "            \"name\":\"Chanh mu\\u1ed1i\",\n" +
//                "            \"price\":19000,\n" +
//                "            \"description\":\"\",\n" +
//                "            \"id_category\":\"netc\"\n" +
//                "         },\n" +
//                "         \"quantity\":1,\n" +
//                "         \"discount\":0\n" +
//                "      },\n" +
//                "      \"necr\":{  \n" +
//                "         \"product\":{  \n" +
//                "            \"id\":\"necr\",\n" +
//                "            \"name\":\"N\\u01b0\\u1edbc \\u00e9p C\\u00e0 R\\u1ed1t\",\n" +
//                "            \"price\":24000,\n" +
//                "            \"description\":\"\",\n" +
//                "            \"id_category\":\"netc\"\n" +
//                "         },\n" +
//                "         \"quantity\":3,\n" +
//                "         \"discount\":0\n" +
//                "      }\n" +
//                "   }\n" +
//                "}";
//        callback.onGetTableCartResponse(fake_result_2);
    }

    public void get_category() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/category";
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                callback.onGetCategoryResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onGetCategoryResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);

//        String fake_result = "[{\"id\":\"bc\",\"name\":\"Bia - Coke\"}," +
//                "{\"id\":\"ht\",\"name\":\"H\\u1ed3ng tr\\u00e0\"}," +
//                "{\"id\":\"ksc\",\"name\":\"Kem - S\\u1eefa chua\"}," +
//                "{\"id\":\"netc\",\"name\":\"N\\u01b0\\u1edbc \\u00e9p tr\\u00e1i c\\u00e2y\"}," +
//                "{\"id\":\"st\",\"name\":\"Sinh t\\u1ed1\"}," +
//                "{\"id\":\"ta\",\"name\":\"Th\\u1ee9c \\u0103n\"}," +
//                "{\"id\":\"tcf\",\"name\":\"Tr\\u00e0 - Cafe\"}," +
//                "{\"id\":\"ts\",\"name\":\"Tr\\u00e0 s\\u1eefa\"}]";
//        callback.onGetCategoryResponse(fake_result);
    }

    public void get_product() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/product";
        StringRequest requestString = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onGetProductResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onGetProductResponse(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }
        };
        requestQueue.add(requestString);

//        String fake_result = "[\n" +
//                "   {\n" +
//                "      \"id\":\"aqfn\",\n" +
//                "      \"name\":\"N\\u01b0\\u1edbc su\\u1ed1i Aquafina\",\n" +
//                "      \"price\":10000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"bc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"bbws\",\n" +
//                "      \"name\":\"Bia Budweiser\",\n" +
//                "      \"price\":19000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"bc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"bhnk\",\n" +
//                "      \"name\":\"Bia Heineken\",\n" +
//                "      \"price\":18000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"bc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"bn\",\n" +
//                "      \"name\":\"B\\u00e0o Ng\\u01b0\",\n" +
//                "      \"price\":400000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"btg\",\n" +
//                "      \"name\":\"Bia Tiger\",\n" +
//                "      \"price\":15000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"bc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"btt\",\n" +
//                "      \"name\":\"B\\u00e1nh Tr\\u00e1ng Tr\\u1ed9n\",\n" +
//                "      \"price\":18000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"bvc\",\n" +
//                "      \"name\":\"B\\u00f2 Vi\\u00ean Chi\\u00ean\",\n" +
//                "      \"price\":15000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"bwy\",\n" +
//                "      \"name\":\"B\\u00f2 Wagyu\",\n" +
//                "      \"price\":1250000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"cc\",\n" +
//                "      \"name\":\"Cacao\",\n" +
//                "      \"price\":20000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"tcf\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"cccl\",\n" +
//                "      \"name\":\"Coca Cola\",\n" +
//                "      \"price\":15000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"bc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"cfd\",\n" +
//                "      \"name\":\"Cafe \\u0110en\",\n" +
//                "      \"price\":15000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"tcf\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"cfs\",\n" +
//                "      \"name\":\"Cafe S\\u1eefa\",\n" +
//                "      \"price\":22000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"tcf\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"cm\",\n" +
//                "      \"name\":\"Chanh mu\\u1ed1i\",\n" +
//                "      \"price\":19000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"cpcn\",\n" +
//                "      \"name\":\"Cappuccino\",\n" +
//                "      \"price\":45000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"tcf\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"cvc\",\n" +
//                "      \"name\":\"C\\u00e1 Vi\\u00ean Chi\\u00ean\",\n" +
//                "      \"price\":15000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"dc\",\n" +
//                "      \"name\":\"\\u0110\\u00e1 chanh\",\n" +
//                "      \"price\":15000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"dm\",\n" +
//                "      \"name\":\"\\u0110\\u00e1 me\",\n" +
//                "      \"price\":18000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"eps\",\n" +
//                "      \"name\":\"Espresso\",\n" +
//                "      \"price\":35000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"tcf\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"gtx\",\n" +
//                "      \"name\":\"\\u0110\\u00f9i g\\u00e0 Texas\",\n" +
//                "      \"price\":128000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"htbh\",\n" +
//                "      \"name\":\"H\\u1ed3ng tr\\u00e0 B\\u1ea1c H\\u00e0\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ht\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"htd\",\n" +
//                "      \"name\":\"H\\u1ed3ng tr\\u00e0 D\\u00e2u\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ht\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"htdl\",\n" +
//                "      \"name\":\"H\\u1ed3ng tr\\u00e0 D\\u01b0a L\\u01b0\\u1edbi\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ht\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"hthtc\",\n" +
//                "      \"name\":\"H\\u1ed3ng tr\\u00e0 H\\u1ea1t Tr\\u00e1i C\\u00e2y\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ht\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"htlh\",\n" +
//                "      \"name\":\"H\\u1ed3ng tr\\u00e0 L\\u00f4 H\\u1ed9i\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ht\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"htmo\",\n" +
//                "      \"name\":\"H\\u1ed3ng tr\\u00e0 M\\u1eadt Ong\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ht\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"htss\",\n" +
//                "      \"name\":\"H\\u1ed3ng tr\\u00e0 S\\u01b0\\u01a1ng S\\u00e1o\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ht\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"kb\",\n" +
//                "      \"name\":\"Kimbap\",\n" +
//                "      \"price\":35000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"kc\",\n" +
//                "      \"name\":\"King Crab\",\n" +
//                "      \"price\":2800000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"kscl\",\n" +
//                "      \"name\":\"Kem Socola\",\n" +
//                "      \"price\":35000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ksc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"ksr\",\n" +
//                "      \"name\":\"Kem S\\u1ea7u Ri\\u00eang\",\n" +
//                "      \"price\":28000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ksc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"ktc\",\n" +
//                "      \"name\":\"Khoai T\\u00e2y Chi\\u00ean\",\n" +
//                "      \"price\":20000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"kvn\",\n" +
//                "      \"name\":\"Kem Vani\",\n" +
//                "      \"price\":30000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ksc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"lt\",\n" +
//                "      \"name\":\"Latte\",\n" +
//                "      \"price\":25000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"tcf\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"lv\",\n" +
//                "      \"name\":\"N\\u01b0\\u1edbc su\\u1ed1i Lavie\",\n" +
//                "      \"price\":10000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"bc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"mc\",\n" +
//                "      \"name\":\"M\\u00ec cay\",\n" +
//                "      \"price\":40000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"mch\",\n" +
//                "      \"name\":\"Mocha\",\n" +
//                "      \"price\":17000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"tcf\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"nec\",\n" +
//                "      \"name\":\"N\\u01b0\\u1edbc \\u00e9p Cam\",\n" +
//                "      \"price\":26000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"necc\",\n" +
//                "      \"name\":\"N\\u01b0\\u1edbc \\u00e9p C\\u00e0 Chua\",\n" +
//                "      \"price\":24000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"necr\",\n" +
//                "      \"name\":\"N\\u01b0\\u1edbc \\u00e9p C\\u00e0 R\\u1ed1t\",\n" +
//                "      \"price\":24000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"nedh\",\n" +
//                "      \"name\":\"N\\u01b0\\u1edbc \\u00e9p D\\u01b0a H\\u1ea5u\",\n" +
//                "      \"price\":22000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"neo\",\n" +
//                "      \"name\":\"N\\u01b0\\u1edbc \\u00e9p \\u1ed4i\",\n" +
//                "      \"price\":22000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"net\",\n" +
//                "      \"name\":\"N\\u01b0\\u1edbc \\u00e9p T\\u00e1o\",\n" +
//                "      \"price\":24000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"netc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"ps\",\n" +
//                "      \"name\":\"Pepsi\",\n" +
//                "      \"price\":15000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"bc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"sccc\",\n" +
//                "      \"name\":\"S\\u1eefa chua Ca Cao\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ksc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"schq\",\n" +
//                "      \"name\":\"S\\u1eefa chua Hoa Qu\\u1ea3\",\n" +
//                "      \"price\":24000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ksc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"scnc\",\n" +
//                "      \"name\":\"S\\u1eefa chua N\\u1ebfp C\\u1ea9m\",\n" +
//                "      \"price\":22000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ksc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"sctc\",\n" +
//                "      \"name\":\"S\\u1eefa chua Th\\u1eadp C\\u1ea9m\",\n" +
//                "      \"price\":28000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ksc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"scvq\",\n" +
//                "      \"name\":\"S\\u1eefa chua Vi\\u1ec7t Qu\\u1ea5t\",\n" +
//                "      \"price\":25000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ksc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"st\",\n" +
//                "      \"name\":\"Sting\",\n" +
//                "      \"price\":12000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"bc\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"stb\",\n" +
//                "      \"name\":\"Sinh t\\u1ed1 B\\u01a1\",\n" +
//                "      \"price\":30000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"st\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"stcc\",\n" +
//                "      \"name\":\"Sinh t\\u1ed1 C\\u00e0 Chua\",\n" +
//                "      \"price\":20000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"st\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"stcr\",\n" +
//                "      \"name\":\"Sinh t\\u1ed1 C\\u00e0 R\\u1ed1t\",\n" +
//                "      \"price\":20000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"st\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"std\",\n" +
//                "      \"name\":\"Sinh t\\u1ed1 D\\u00e2u\",\n" +
//                "      \"price\":22000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"st\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"stdu\",\n" +
//                "      \"name\":\"Sinh t\\u1ed1 D\\u1eeba\",\n" +
//                "      \"price\":20000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"st\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"stmc\",\n" +
//                "      \"name\":\"Sinh t\\u1ed1 M\\u00e3ng C\\u1ea7u\",\n" +
//                "      \"price\":25000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"st\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"stspc\",\n" +
//                "      \"name\":\"Sinh t\\u1ed1 Sapoch\\u00ea\",\n" +
//                "      \"price\":24000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"st\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"stx\",\n" +
//                "      \"name\":\"Sinh t\\u1ed1 Xo\\u00e0i\",\n" +
//                "      \"price\":22000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"st\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tlt\",\n" +
//                "      \"name\":\"Tr\\u00e0 Lipton\",\n" +
//                "      \"price\":20000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"tcf\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tsbh\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa B\\u1ea1c H\\u00e0\",\n" +
//                "      \"price\":19000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tscd\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa Chanh D\\u00e2y\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tscrm\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa Caramen\",\n" +
//                "      \"price\":19000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tsd\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa D\\u00e2u\",\n" +
//                "      \"price\":22000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tsda\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa \\u0110\\u00e0o\",\n" +
//                "      \"price\":22000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tshn\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa Honny\",\n" +
//                "      \"price\":19000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tskw\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa Kiwi\",\n" +
//                "      \"price\":21000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tsn\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa Nho\",\n" +
//                "      \"price\":23000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tsph\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa Ph\\u01b0\\u1ee3ng Ho\\u00e0ng\",\n" +
//                "      \"price\":18000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tsscl\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa Socola\",\n" +
//                "      \"price\":16000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tssr\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa S\\u1ea7u Ri\\u00eang\",\n" +
//                "      \"price\":24000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tst\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa T\\u00e1o\",\n" +
//                "      \"price\":25000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tstc\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa Tr\\u00e2n Ch\\u00e2u\",\n" +
//                "      \"price\":18000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"tsv\",\n" +
//                "      \"name\":\"Tr\\u00e0 s\\u1eefa V\\u1ea3i\",\n" +
//                "      \"price\":17000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ts\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"vc\",\n" +
//                "      \"name\":\"Vi C\\u00e1\",\n" +
//                "      \"price\":35000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   },\n" +
//                "   {\n" +
//                "      \"id\":\"xxd\",\n" +
//                "      \"name\":\"X\\u00fac x\\u00edch \\u0110\\u1ee9c\",\n" +
//                "      \"price\":24000,\n" +
//                "      \"description\":\"\",\n" +
//                "      \"id_category\":\"ta\"\n" +
//                "   }\n" +
//                "]";
//        callback.onGetProductResponse(fake_result);
    }

    public void post_order(final String table_id, final String user_id, final String cart) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/bill";
        StringRequest requestString = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onPostOrder(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onPostOrder(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_table", table_id);
                params.put("id_user", user_id);
                params.put("cart", cart);
                return params;
            }
        };
        requestQueue.add(requestString);
    }

    public void put_check(final String user_id, String table_id) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + CommonAPI.Huy_ip + "/quanlycafe/public/api/bill/" + String.valueOf(table_id);
        StringRequest requestString = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onPutCheck(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onPutCheck(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_user", user_id);
                return params;
            }
        };
        requestQueue.add(requestString);
    }
}