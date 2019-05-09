package model;

public class ContractModel {
    String MaHD;
    String MaKH;
    String NgayHD;

    public ContractModel() {
    }

    public ContractModel(String maHD, String maKH, String ngayHD) {
        MaHD = maHD;
        MaKH = maKH;
        NgayHD = ngayHD;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getNgayHD() {
        return NgayHD;
    }

    public void setNgayHD(String ngayHD) {
        NgayHD = ngayHD;
    }

    @Override
    public String toString() {
        return getMaHD() + "-" + getMaKH() + "-" + getNgayHD();
    }
}
