package com.example.movieapp.model;

//FİLM İSMİ IDSİ GİBİ BİLEŞENLER GET VE SET EDİLİR Kİ DİĞER SINIFLAR TARAFINDAN KULLANILABİLSİN
public class Movie {
    private String id;
    private String name;
    private String imageUrl;

    public Movie(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
