package com.example.elektronikaiszakszerviz2;

import java.util.Date;

public class Appointment {

    private Date date;
    private String type = "";
    private String user;

    private String price = "0";

    private String title;

    private int image;

    public Appointment(Date date, String type, String user, String price, String title, int image) {
        this.date = date;
        this.type = type;
        this.user = user;
        this.price = price;
        this.title = title;
        this.image = image;
    }


    public Appointment(Date date, String title, String user) {
        this.date = date;
        this.title = title;
        this.user = user;
    }

    public Appointment() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
