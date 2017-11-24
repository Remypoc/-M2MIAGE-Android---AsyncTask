package com.example.repocque.asynctask;

import android.graphics.Bitmap;

import java.util.Date;

public class Film {

    private Bitmap image;
    private String title;
    private Date date;
    private String production;

    Film(Bitmap image, String title, Date date, String production) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.production = production;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "Film{" +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", production='" + production + '\'' +
                '}';
    }
}
