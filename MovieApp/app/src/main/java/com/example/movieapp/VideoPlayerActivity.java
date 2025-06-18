package com.example.movieapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private ExoPlayer exoPlayer;

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TAM EKRAN GÖRÜNÜMÜ İÇİN
        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
                android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //XML TASARIMI BAĞLANIR
        setContentView(R.layout.activity_video_player);

        playerView = findViewById(R.id.playerView);
        playerView.setUseController(true);
        playerView.setControllerShowTimeoutMs(0);
        playerView.showController();

        //MOVİE DETAİLSDEN GELEN URL ALINIR
        String url = getIntent().getStringExtra("url");

        //URL BOŞSA HATA MESAJI VERİR
        if (url == null || url.isEmpty()) {
            Toast.makeText(this, "Video URL bulunamadı!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        //LOGCAT'DE URL YAZDIRILI
        android.util.Log.d("VideoPlayerActivity", "Oynatılan video URL: " + url);

        exoPlayer = new ExoPlayer.Builder(this).build();

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(url));

        exoPlayer.setMediaItem(mediaItem);
        playerView.setPlayer(exoPlayer); //PLAYER İLE EXO PLAYER BAĞLANTISI YAPILIR
        exoPlayer.prepare(); //OYNATMAYA HAZIRLANIR
        exoPlayer.setPlayWhenReady(true); //HAZIR OLUR OLMAZ OTOMATİK OYNATIR.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
