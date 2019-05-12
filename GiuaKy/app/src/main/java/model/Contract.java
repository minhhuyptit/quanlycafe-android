package model;

import java.util.HashMap;

public class Contract {
    String NGAYHD;
    String MAKH;
    boolean STATUS;
    public HashMap<String, ContractDetail> contractDetail;

    public Contract() {
    }

    public Contract(String NGAYHD, String MAKH) {
        this.NGAYHD = NGAYHD;
        this.MAKH = MAKH;
        this.STATUS = true;
        contractDetail = new HashMap<>();
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

    public HashMap<String, ContractDetail> getContractDetail() {
        return contractDetail;
    }

    public void setContractDetail(HashMap<String, ContractDetail> contractDetailHashMap) {
        this.contractDetail = contractDetailHashMap;
    }

    public boolean isSTATUS() {
        return STATUS;
    }

    public void setSTATUS(boolean STATUS) {
        this.STATUS = STATUS;
    }
}
