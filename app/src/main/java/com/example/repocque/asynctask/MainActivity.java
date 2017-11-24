package com.example.repocque.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int i = 0;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Film> films = new ArrayList<>();
        adapter = new CustomAdapter(this, films);

        ListView lv = findViewById(R.id.main_listview);
        lv.setAdapter(adapter);

        Button button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Film film = new Film(null, "film" + i++, new Date(), "Blabla");
                System.out.println("ADD FILM" + film);
                films.add(film);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
