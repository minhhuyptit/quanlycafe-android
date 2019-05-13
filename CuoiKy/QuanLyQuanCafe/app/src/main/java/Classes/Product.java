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

    public Product(String id, String name, double price, String description, String idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.idCategory = idCategory;
    }
}
