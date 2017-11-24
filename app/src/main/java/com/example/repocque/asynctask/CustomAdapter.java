package com.example.repocque.asynctask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CustomAdapter extends ArrayAdapter<Film> {

    CustomAdapter(@NonNull Context context, ArrayList<Film> films) {
        super(context, R.layout.main_listview_item, films);
    }

    private class ViewHolder {
        ImageView image;
        TextView title;
        TextView date;
        TextView production;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Film film = getItem(position);
        View view = convertView;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.main_listview_item, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.image = view.findViewById(R.id.main_listview_item_image);
        viewHolder.title = view.findViewById(R.id.main_listview_item_title);
        viewHolder.date = view.findViewById(R.id.main_listview_item_year);
        viewHolder.production = view.findViewById(R.id.main_listview_item_production);

        Format formatter;
        formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm aa", Locale.ENGLISH);
        Date date = film.getDate();
        String s = formatter.format(date);


        viewHolder.title.setText(film.getTitle());
        viewHolder.date.setText(s);
        viewHolder.production.setText(film.getProduction());

        DownloadImage downloadImage = new DownloadImage();
        try {
            viewHolder.image.setImageBitmap(downloadImage.downloadImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}
