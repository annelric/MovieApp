package com.example.movieapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.adapter.WatchlistAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WatchlistActivity extends AppCompatActivity {

    RecyclerView watchlistRecyclerView;
    WatchlistAdapter watchlistAdapter;
    List<String> watchlistMovieNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist); //XML ARA YÜZÜ BAĞLANIR

        //RECYCLER VİEW BİLEŞENİNİ BULUR
        watchlistRecyclerView = findViewById(R.id.watchlistRecyclerView);

        // SharedPreferences'tan izleme listesini alırız
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();

        watchlistMovieNames = new ArrayList<>(); //BOŞ LİSTE OLUŞTURURZ

        //TÜM VERİLERİ DOLAŞIRIZ
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            //EĞER FİLM WATCH LİSTE VARSA İZLEME LİSTESİNE EKLERİZ
            if (key.endsWith("_watchlist") && value instanceof Boolean && (Boolean) value) {
                String movieId = key.replace("_watchlist", "");
                String movieName = prefs.getString(movieId + "_name", null);
                if (movieName != null) {
                    watchlistMovieNames.add(movieName);
                }
            }
        }

        //OLUŞTURULAN ADAPTER'A LİSTE VERİLİR
        //RECYCLER VİEW'E SIRALAMA ATANIR
        //ADAPTER, RECYCLER VİEW İLE BAĞLANIR
        watchlistAdapter = new WatchlistAdapter(watchlistMovieNames);
        watchlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        watchlistRecyclerView.setAdapter(watchlistAdapter);
    }
}
