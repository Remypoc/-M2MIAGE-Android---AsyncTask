package com.example.repocque.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

public class DownloadImage {

    private static String IMAGE_URL = "http://lorempixel.com/400/200/";

    public Bitmap downloadImage() throws IOException {
        URL url = new URL(IMAGE_URL);
        return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }
}
