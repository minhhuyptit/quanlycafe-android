package Classes;

import java.util.HashMap;

public class TableOrder {
    String area;
    String loginname;
    HashMap<String, Integer> chickenProduct;
    String table;
    String timein;
    String status;
    String key;

    public TableOrder() {
    }

    public TableOrder(String area, String loginname, String status, String table, String timein, HashMap<String, Integer> chickenProduct) {
        this.area = area;
        this.loginname = loginname;
        this.status = status;
        this.table = table;
        this.timein = timein;
        this.chickenProduct = chickenProduct;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTimein() {
        return timein;
    }

    public void setTimein(String timein) {
        this.timein = timein;
    }

    public HashMap<String, Integer> getChickenProduct() {
        return chickenProduct;
    }

    public void setChickenProduct(HashMap<String, Integer> chickenProduct) {
        this.chickenProduct = chickenProduct;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return timein + " " + table + " " + chickenProduct.size() + " " + key;
    }
}
