package com.example.new2.data;

import java.util.ArrayList;

public class Map {

    private String name;
    private String Address;
    private String page;
    private String phone;
    private ArrayList<Float> latlong;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Float> getLatlong() {
        return latlong;
    }

    public void setLatlong(ArrayList<Float> latlong) {
        this.latlong = latlong;
    }
}
