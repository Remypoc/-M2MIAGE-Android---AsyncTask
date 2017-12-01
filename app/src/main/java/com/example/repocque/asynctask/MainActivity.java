package com.example.repocque.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static int i = 0;
    private CustomAdapter adapter;
    /*private String URL_IMAGE = "http://lorempixel.com/400/200";*/
    private String URL_IMAGE = "https://picsum.photos/200/300/?random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new CustomAdapter(this, new ArrayList<Film>());

        ListView lv = findViewById(R.id.main_listview);
        lv.setAdapter(adapter);

        Button button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Film film = new Film(null, "film" + i++, new Date(), "Blabla");
                adapter.add(film);
                adapter.notifyDataSetChanged();
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DownloadImageTask downloadImageTask = new DownloadImageTask(MainActivity.this,
                        adapter, adapter.getItem(i));
                downloadImageTask.execute(URL_IMAGE);
            }
        });
    }
}