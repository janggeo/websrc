package com.example.new2.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Data {
    private int id;
    private String category;
    private String name;
    private ArrayList<String> img;
    private ArrayList<Array> product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImg() {
        return img;
    }

    public void setImg(ArrayList<String> img) {
        this.img = img;
    }

    public ArrayList<Array> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Array> product) {
        this.product = product;
    }
}
