package com.subi.ecommerce.model;

public class Taikhoan {
    int id;
    String hoten;
    String sdt;
    String diachi;
    String email;

    public Taikhoan(int id, String hoten, String sdt, String diachi, String email, String matkhau) {
        this.id = id;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.email = email;
        this.matkhau = matkhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    String matkhau;
}
