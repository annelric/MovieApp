package com.example.movieapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<String> favoriteMovieNames; //İSİMLERİ TUTAN LİSTE

    public FavoritesAdapter(List<String> favoriteMovieNames) {
        this.favoriteMovieNames = favoriteMovieNames;
    }


    //TASARIM
    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new FavoriteViewHolder(view);
    }

    //SATIRA KARŞILIK GELEN FİLM YAZILIR. MESELA 2. FİLM FAVORİLERE EKLENİRSE O İLK
    //SIRAYI ALIR ESKİSİ BİR ALTA KAYAR. POZİSYON VE RENK AYARLAR YANİ
    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.textView.setText(favoriteMovieNames.get(position));
        holder.textView.setTextColor(Color.WHITE);  // Yazı rengini beyaz yaparız
    }

    @Override
    public int getItemCount() {
        return favoriteMovieNames.size(); //ÖĞE SAYISI KADAR SATIR
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
