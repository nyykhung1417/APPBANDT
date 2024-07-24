package com.subi.ecommerce.model;

public class GioHang {
    public GioHang(int id, int idSanPham, String tenSanPham, String moTa, String giaSanPham, String loaiSanPham, String tonggia, String image, int soLuong, int taikhoan_id) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaSanPham = giaSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tonggia = tonggia;
        this.image = image;
        this.soLuong = soLuong;
        this.taikhoan_id = taikhoan_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int idSanPham;
    private String tenSanPham;
    private String moTa;
    private String giaSanPham;
    private String loaiSanPham;


    public GioHang(int idSanPham, String tenSanPham, String moTa, String giaSanPham, String loaiSanPham, String tonggia, String image, int soLuong, int taikhoan_id) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaSanPham = giaSanPham;
        this.loaiSanPham = loaiSanPham;
        this.tonggia = tonggia;
        this.image = image;
        this.soLuong = soLuong;
        this.taikhoan_id = taikhoan_id;
    }

    public String getTonggia() {
        return tonggia;
    }

    public void setTonggia(String tonggia) {
        this.tonggia = tonggia;
    }

    private String tonggia;
    private String image;
    private int soLuong;


    public int getTaikhoan_id() {
        return taikhoan_id;
    }

    public void setTaikhoan_id(int taikhoan_id) {
        this.taikhoan_id = taikhoan_id;
    }

    private int taikhoan_id;


    public GioHang() {
    }

    public GioHang(int idSanPham, String tenSanPham, String moTa, String giaSanPham, String loaiSanPham, String image, int soLuong) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaSanPham = giaSanPham;
        this.loaiSanPham = loaiSanPham;
        this.image = image;
        this.soLuong = soLuong;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "idSanPham=" + idSanPham +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", moTa='" + moTa + '\'' +
                ", giaSanPham='" + giaSanPham + '\'' +
                ", loaiSanPham='" + loaiSanPham + '\'' +
                ", image=" + image +
                ", soLuong=" + soLuong +
                '}';
    }
}
