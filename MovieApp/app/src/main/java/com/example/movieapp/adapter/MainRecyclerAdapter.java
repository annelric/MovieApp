package com.example.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.CategoryItem;

import java.util.List;

//KATEGORİLERİ OLUŞTURUR
//BU KATEGORİLERE AİT YATAY KAYAN FİLM LİSTESİNİ ALIR
//BU YATAY KAYAN FİMLERİ GÖRÜNTÜLEMEK İÇİN ITEM RECYCLER ADAPTER SINIFINI KULLANILIR
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    Context context;
    List<AllCategory> allCategoryList;

    public MainRecyclerAdapter(List<AllCategory> allCategoryList, Context context) {
        this.allCategoryList = allCategoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false));
    }

    //KATEGORİ BAŞLIĞI YAZDIRILIR
    //ALT KATEGORİLER İÇİN YATAY RECYLER VİEW AYARLANIR
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.categoryName.setText(allCategoryList.get(position).getCategoryTitle());
        setItemRecycler(holder.itemRecycler, allCategoryList.get(position).getCategoryItemList());

    }

    //TOPLAM KATEGORİ SAYISI KADAR SATIR
    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName; //KATEGORİ BAŞLIĞI
        RecyclerView itemRecycler; //BU BAŞLIĞA GÖRE YATAY FİLM LİSTESİ
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.item_category);
            itemRecycler = itemView.findViewById(R.id.item_recycler);
        }
    }

    //ALT İÇERİKLER İÇİN ITEM RECYCLER KULLANILIR
    //YATAY(HORİZONTAL) GÖRÜNÜM AYARLANIR
    private void setItemRecycler(RecyclerView recyclerView, List<CategoryItem> categoryItemList) {

        ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter(context, categoryItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);
    }
}
