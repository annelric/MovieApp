package com.example.movieapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.adapter.FavoritesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView favoriteRecyclerView;
    FavoritesAdapter favoritesAdapter;
    List<String> favoriteMovieNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // RECYCLER VİEW''İ BULUR
        favoriteRecyclerView = findViewById(R.id.favoritesRecyclerView);

        // FAVORİ FİLM LİSTESİ OLUŞTURUR
        favoriteMovieNames = new ArrayList<>();

        // SHARED PREFERNCES TÜM KAYITLARI ALIR
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();

        //ALINAN KAYITLARA GÖRE DÖNGÜ BAŞLATIR
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey(); //TEK TEK DOLANIR KAYITLARI
            Object value = entry.getValue();

            //EĞER KAYIT FAVORİYSE, FAVORİLERE EKLENİR
            if (key.endsWith("_favorite") && value instanceof Boolean && (Boolean) value) {
                String movieId = key.replace("_favorite", "");
                String movieName = prefs.getString(movieId + "_name", null);
                if (movieName != null) {
                    favoriteMovieNames.add(movieName);
                }
            }
        }

        //OLUŞTURULAN ADAPTER'A LİSTE VERİLİR
        //RECYCLER VİEW'E SIRALAMA ATANIR
        //ADAPTER, RECYCLER VİEW İLE BAĞLANIR
        favoritesAdapter = new FavoritesAdapter(favoriteMovieNames);
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoriteRecyclerView.setAdapter(favoritesAdapter);

        favoritesAdapter.notifyDataSetChanged();
    }
}
