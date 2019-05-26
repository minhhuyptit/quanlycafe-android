package Classes;

public class ProductDetailStatistic {
    String id_product;
    String name;
    float price;
    int quantity;

    public ProductDetailStatistic() {
    }

    public ProductDetailStatistic(String id_product, String name, float price, int quatity) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        this.quantity = quatity;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quatity) {
        this.quantity = quatity;
    }
}
