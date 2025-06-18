package com.example.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.movieapp.MovieDetails;
import com.example.movieapp.R;
import com.example.movieapp.model.BannerMovies;

import java.util.List;

//VERİ İLE ARA YÜZ ARASINDAKİ BAĞLANTIYI SAĞLAR
//ELİMDE Kİ VERİ LİSTESİNDEN VERİLERİ ALIR, EKRANDA GÖSTERİLECEK HALE GETİRİR
public class BannerMoviesPagerAdapter extends PagerAdapter {
    Context context;
    List<BannerMovies> bannerMoviesList;

    public BannerMoviesPagerAdapter(Context context, List<BannerMovies> bannerMoviesList) {
        this.context = context;
        this.bannerMoviesList = bannerMoviesList;
    }

    @Override
    public int getCount() {
        return bannerMoviesList.size(); //TOPLAM KAÇ BANNER VARSA ONU DÖNDÜRÜR
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //XML TASARIMI BAĞLANIR
        View view = LayoutInflater.from(context).inflate(R.layout.banner_movie_layout, null);
        //YÜKLENECEK RESİM ATANIR
        ImageView bannerImage = view.findViewById(R.id.banner_image);

        //KÜTÜPHANE İLE RESİM YÜKLENİR
        Glide.with(context).load(bannerMoviesList.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);

        //GÖRSELE TIKLANIRSA MOVİE DETAİLS SAYFASINA AKTAR
        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MovieDetails.class);
                i.putExtra("movieId", bannerMoviesList.get(position).getId());
                i.putExtra("movieName", bannerMoviesList.get(position).getMovieName());
                i.putExtra("movieImageUrl", bannerMoviesList.get(position).getImageUrl());
                i.putExtra("movieFile", bannerMoviesList.get(position).getFileUrl());
                context.startActivity(i); //YENİ EKRANI BAŞLATIR


            }
        });

        return view;
    }
}
