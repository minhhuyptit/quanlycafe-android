package Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("id_bill")
    @Expose
    public Integer idBill;
    @SerializedName("id_product")
    @Expose
    public String idProduct;
    @SerializedName("quantity")
    @Expose
    public Integer quantity;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("discount")
    @Expose
    public Integer discount;
}
