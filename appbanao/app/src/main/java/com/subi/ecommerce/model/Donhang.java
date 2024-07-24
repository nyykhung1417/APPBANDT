package com.subi.ecommerce.model;

public class Donhang {
    int id;
    String tenSanPham,soLuong,giaSanPham,tonggia,image;

    public Donhang(int id, String tenSanPham, String soLuong, String giaSanPham, String tonggia, String image) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaSanPham = giaSanPham;
        this.tonggia = tonggia;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getTonggia() {
        return tonggia;
    }

    public void setTonggia(String tonggia) {
        this.tonggia = tonggia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
