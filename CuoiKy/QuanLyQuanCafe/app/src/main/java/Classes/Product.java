package Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("price")
    @Expose
    public double price;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("id_category")
    @Expose
    public String idCategory;
}
