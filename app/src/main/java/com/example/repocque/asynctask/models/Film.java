package com.example.repocque.asynctask.models;

import android.graphics.Bitmap;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

public class Film extends SugarRecord {

    @Ignore
    private Bitmap image;
    private byte[] imageByte;
    private String title;
    private Date date;
    private String production;

    public Film() {
    }

    public Film(Bitmap image, String title, Date date, String production) {
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

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
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
