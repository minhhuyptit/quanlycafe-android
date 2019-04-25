package model;

public class CarContract {
    String id;
    String sumDay;
    String price;


    public CarContract(String id, String sumDay, String price) {
        this.id = id;
        this.sumDay = sumDay;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSumDay() {
        return sumDay;
    }

    public void setSumDay(String sumDay) {
        this.sumDay = sumDay;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
