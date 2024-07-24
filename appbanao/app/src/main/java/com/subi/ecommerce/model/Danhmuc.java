package com.subi.ecommerce.model;

import java.io.Serializable;

public class Danhmuc implements Serializable {
    int id;
    String tendanhmuc;

    public int getId() {
        return id;
    }

    public Danhmuc(int id, String tendanhmuc) {
        this.id = id;
        this.tendanhmuc = tendanhmuc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }
}
