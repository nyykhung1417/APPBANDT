package com.subi.ecommerce.model;


import java.io.Serializable;

public class Cart implements Serializable {
    private int id,taikhoan_id,product_id;

    public Cart(int id, int taikhoan_id, int product_id, String tenSanPham, String giaSanPham, String soluong, String tonggia) {
        this.id = id;
        this.taikhoan_id = taikhoan_id;
        this.product_id = product_id;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soluong = soluong;
        this.tonggia = tonggia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaikhoan_id() {
        return taikhoan_id;
    }

    public void setTaikhoan_id(int taikhoan_id) {
        this.taikhoan_id = taikhoan_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getTonggia() {
        return tonggia;
    }

    public void setTonggia(String tonggia) {
        this.tonggia = tonggia;
    }

    private String tenSanPham,giaSanPham,soluong,tonggia;

}
