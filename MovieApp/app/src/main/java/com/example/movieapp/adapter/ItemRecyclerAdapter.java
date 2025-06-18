package com.example.movieapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.MovieDetails;
import com.example.movieapp.R;
import com.example.movieapp.model.CategoryItem;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

//BİRDEN FAZLA OLAN VERİYİ/ÖĞEYİ EKRANDA GÖSTERMEK İÇİN KULLANILIR
//YATAY KAYDIRMALI OLAN FİLMLERİ GÖRÜNTÜLEMEMİZİ SAĞLAR
//MAİN RECYCLER'İN ALT LİSTEYİ GÖSTERMESİ İÇİN BU CLASS'I KULLANMASI GEREKİR.
public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    Context context;
    List<CategoryItem> categoryItemList;

    public ItemRecyclerAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList; //LİSTE ATANIR
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_recycler_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //RESİM EKLENİR GLİDE KÜTÜPHANESİ İLE
        Glide.with(context).load(categoryItemList.get(position).getImageUrl()).into(holder.itemImage);

        //RESME TIKLANINCA MOVİE DETAİLS EKRANINA ATAR
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MovieDetails.class);
                i.putExtra("movieId", String.valueOf(categoryItemList.get(position).getId()));
                i.putExtra("movieName", categoryItemList.get(position).getMovieName());
                i.putExtra("movieImageUrl", categoryItemList.get(position).getImageUrl());
                i.putExtra("movieFile", categoryItemList.get(position).getFileUrl());
                context.startActivity(i);      //YENİ EKRANA GEÇİŞ
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryItemList.size(); //KAÇ ÖĞE VARSA ONU DÖNDÜRÜR
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
        }
    }
}
