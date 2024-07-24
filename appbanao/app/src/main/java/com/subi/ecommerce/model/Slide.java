package com.subi.ecommerce.model;


import java.io.Serializable;

public class Slide implements Serializable {
    private int id;
    private String image;

    public Slide(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
