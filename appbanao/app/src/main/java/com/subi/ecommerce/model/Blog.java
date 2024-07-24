package com.subi.ecommerce.model;


import com.subi.ecommerce.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Blog implements Serializable {
    public int getId() {
        return id;
    }

    public Blog(int id, String tieude, String motangan, String image, String ngaydang, String motachitiet) {
        this.id = id;
        this.tieude = tieude;
        this.motangan = motangan;
        this.image = image;
        this.ngaydang = ngaydang;
        this.motachitiet = motachitiet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getMotangan() {
        return motangan;
    }

    public void setMotangan(String motangan) {
        this.motangan = motangan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getMotachitiet() {
        return motachitiet;
    }

    public void setMotachitiet(String motachitiet) {
        this.motachitiet = motachitiet;
    }

    private int id;
    private String tieude , motangan , image , ngaydang, motachitiet;
}
