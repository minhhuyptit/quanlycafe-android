package Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    public Integer quantity;
    public String name_product;

    public OrderDetail(Integer quantity, String name_product) {
        this.quantity = quantity;
        this.name_product = name_product;
    }
}
