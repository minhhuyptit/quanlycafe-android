package com.example.exemple;

public class MonHoc {
    private String id;
    private String name;
    private int soTiet;

    public MonHoc() {
    }

    public MonHoc(String id, String name, int soTiet) {
        this.id = id;
        this.name = name;
        this.soTiet = soTiet;
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

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }

    @Override
    public String toString() {
        return this.id+"-"+this.name;
    }
}
