package com.hfad.movieitem;

public class MovieItem {
    public String title;
    public String imageUrl;

    public MovieItem(String title){
        this.title = title;
    }

    public MovieItem(MovieJson movieJson){
        this.title = movieJson.title;
        this.imageUrl = movieJson.img;
    }
}
