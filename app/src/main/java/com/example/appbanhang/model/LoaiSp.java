package com.example.appbanhang.model;

public class LoaiSp {
    int id_sanpham;
    String tensanpham;
    String hinhanh;

    public LoaiSp( String tensanpham, String hinhanh) {
        this.tensanpham = tensanpham;
        this.hinhanh = hinhanh;
    }

    public void setId_sanpham(int id_sanpham) {
        this.id_sanpham = id_sanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getId_sanpham() {
        return id_sanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

}
