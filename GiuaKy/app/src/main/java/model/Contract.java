package model;

public class Contrat {
    String SOHD;
    String NGAYHD;
    String MAKH;

    public Contrat() {
    }

    public Contrat(String SOHD, String NGAYHD, String MAKH) {
        this.SOHD = SOHD;
        this.NGAYHD = NGAYHD;
        this.MAKH = MAKH;
    }

    public String getSOHD() {
        return SOHD;
    }

    public void setSOHD(String SOHD) {
        this.SOHD = SOHD;
    }

    public String getNGAYHD() {
        return NGAYHD;
    }

    public void setNGAYHD(String NGAYHD) {
        this.NGAYHD = NGAYHD;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }
}
