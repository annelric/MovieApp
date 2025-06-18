package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    ImageView movieImage;
    TextView movieName;
    View playButton;
    Button btnFavorite, btnWatchList;

    String mName, mImage, mId, mFileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //XML'DEN BİLEŞENELR ALINIR
        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        playButton = findViewById(R.id.play_button);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnWatchList = findViewById(R.id.btnWatchList);

        // FİLMİN ID'Sİ ALINIR
        mId = getIntent().getStringExtra("movieId");
        if (mId == null) {
            int mIdInt = getIntent().getIntExtra("movieId", -1);
            mId = String.valueOf(mIdInt);
        }

        //ID HATALI MI KONTROL
        if (mId == null || mId.equals("-1")) {
            Toast.makeText(this, "Invalid movie ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        //ID'YE KARŞILIK GELEN DİĞER BİLEŞENLER ALINIR
        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("movieImageUrl");
        mFileUrl = getIntent().getStringExtra("movieFile");

        //AFİŞ RESMİ GLİDE İLE ALINIR
        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);

        // PLAY BUTONUNA BASINCA VİDEO PLAYER EKRANINA YÖNLENDİRİR
        playButton.setOnClickListener(v -> {
            if (mFileUrl != null && !mFileUrl.isEmpty()) {
                Intent intent = new Intent(MovieDetails.this, VideoPlayerActivity.class);
                intent.putExtra("url", mFileUrl); //VİDEO LİNKİ ALINIR
                startActivity(intent); //BAŞLATILIR
            } else {
                Toast.makeText(MovieDetails.this, "Video URL bulunamadı", Toast.LENGTH_SHORT).show();
            }
        });

        //VERİLER KALICI OLARAK SAKLANIR, VERİLERİN BULUNDUĞU DEPOYU GETİRİR
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        //FAVORİLERE EKLE BUTONUNA BASILIRSA EKLEME İŞLEMİ YAPILIR
        btnFavorite.setOnClickListener(v -> {
            editor.putBoolean(mId + "_favorite", true); //işaretlenir
            editor.putString(mId + "_name", mName);//ismi ile kaydedilir
            editor.apply();//değişiklikleri alır
            Toast.makeText(this, "Added to Favorites ❤️", Toast.LENGTH_SHORT).show(); //bildirim
        });

        //WATCH LİSTE EKLE BUTONUNA BASILIRSA EKLEME İŞLEMİ YAPILIR
        btnWatchList.setOnClickListener(v -> {
            editor.putBoolean(mId + "_watchlist", true);
            editor.putString(mId + "_name", mName);
            editor.apply();
            Toast.makeText(this, "Added to Watchlist 📃", Toast.LENGTH_SHORT).show();
        });
    }
}
