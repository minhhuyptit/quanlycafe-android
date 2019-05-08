package model;

public class ContractDetail {
    int SONGAYTHUE;
    double GIATHUE;

    public ContractDetail() {
    }

    public ContractDetail(int SONGAYTHUE, double GIATHUE) {
        this.SONGAYTHUE = SONGAYTHUE;
        this.GIATHUE = GIATHUE;
    }

    public int getSONGAYTHUE() {
        return SONGAYTHUE;
    }

    public void setSONGAYTHUE(int SONGAYTHUE) {
        this.SONGAYTHUE = SONGAYTHUE;
    }

    public double getGIATHUE() {
        return GIATHUE;
    }

    public void setGIATHUE(double GIATHUE) {
        this.GIATHUE = GIATHUE;
    }
}
