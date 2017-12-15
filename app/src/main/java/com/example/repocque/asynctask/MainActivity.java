package com.example.repocque.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.repocque.asynctask.models.Film;
import com.orm.SugarContext;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int i = 0;
    private CustomAdapter adapter;
    /*private String URL_IMAGE = "http://lorempixel.com/400/200";*/
    private String URL_IMAGE = "https://picsum.photos/200/300/?random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this.deleteDatabase("films.db");
        SugarContext.init(this);

        List<Film> films = new ArrayList<>();

        for (Film film : Film.listAll(Film.class)) {

            System.out.println(film);
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(film.getImageByte());
            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
            film.setImage(bitmap);
            films.add(film);
        }

        adapter = new CustomAdapter(this, films);

        ListView lv = findViewById(R.id.main_listview);
        lv.setAdapter(adapter);

        Button button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Film film = new Film(null, "film" + i++, new Date(), "prod");
                adapter.add(film);
                adapter.notifyDataSetChanged();
                film.save();
            }
        });

        Button button_update = findViewById(R.id.button_update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < adapter.getCount(); i++) {
                    DownloadImageTask downloadImageTask = new DownloadImageTask(
                            MainActivity.this, adapter, adapter.getItem(i));
                    downloadImageTask.execute(URL_IMAGE);
                }
            }
        });

        Button button_reset = findViewById(R.id.button_reset);
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = 0;
                adapter.clear();
                Film.deleteAll(Film.class);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DownloadImageTask downloadImageTask = new DownloadImageTask(MainActivity.this,
                        adapter, adapter.getItem(i));
                downloadImageTask.execute(URL_IMAGE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }
}