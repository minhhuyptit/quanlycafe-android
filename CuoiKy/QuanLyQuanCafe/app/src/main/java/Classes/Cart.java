package Classes;

import java.util.HashMap;

public class Cart {
    public HashMap<String, Item> cartItem = new HashMap<>();

    public void more(Product product) {
        try {
            this.cartItem.get(product.id).quantity += 1;
        } catch (NullPointerException npe) {
            Item item = new Item();
            item.product = product;
            item.quantity = 1;
            item.discount = 0;
            cartItem.put(product.id, item);
        }
    }

    public void less(String product_id) {
        try {
            if(this.cartItem.get(product_id).quantity > 1){
                this.cartItem.get(product_id).quantity -= 1;
            }
            if(this.cartItem.get(product_id).quantity == 1){
                this.cartItem.remove(product_id);
            }
        } catch (NullPointerException ignored) {

        }
    }

    public double sum(){
        double result=0;
        for(String key:cartItem.keySet()){
            Item i = cartItem.get(key);
            result += i.product.price * i.quantity;
        }
        return  result;
    }
}
