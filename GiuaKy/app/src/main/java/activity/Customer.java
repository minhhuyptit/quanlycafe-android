package activity;

public class Customer {
    private String maKH;
    private String tenKH;
    private String diachiKH;

    public Customer(){
    }

    public Customer(String maKH, String tenKH, String diachiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diachiKH = diachiKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiachiKH() {
        return diachiKH;
    }

    public void setDiachiKH(String diachiKH) {
        this.diachiKH = diachiKH;
    }
}
