package com.example.repocque.asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.repocque.asynctask.models.Film;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {

    // If reference on main activity is null, task reference will be null
    private WeakReference<CustomAdapter> adapter;
    private WeakReference<Film> film;
    private WeakReference<Activity> activity;

    DownloadImageTask(Activity activity, CustomAdapter adapter, Film film) {
        this.activity = new WeakReference<>(activity);
        this.adapter = new WeakReference<>(adapter);
        this.film = new WeakReference<>(film);
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        if (this.activity.get() != null && film.get() != null && adapter.get() != null) {
            film.get().setImage(image);

            ByteArrayOutputStream stream =new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG,90, stream);
            byte[] image_byte = stream.toByteArray();
            film.get().setImageByte(image_byte);
            film.get().save();
            adapter.get().notifyDataSetChanged();
        }
    }
}
