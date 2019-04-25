package model;

public class Car {
    String id;
    String name;
    String origin;
    boolean hasChoose;
    public CarContract contract = null;

    public Car(String id, String name, String origin, boolean hasChoose) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.hasChoose = hasChoose;
        contract = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isHasChoose() {
        return hasChoose;
    }

    public void setHasChoose(boolean hasChoose) {
        this.hasChoose = hasChoose;
    }

    public CarContract getContract() {
        return contract;
    }

    public void setContract(CarContract contract) {
        this.contract = contract;
    }
}
