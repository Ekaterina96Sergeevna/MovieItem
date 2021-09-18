package com.hfad.movieitem;

import com.google.gson.annotations.SerializedName;

public class MovieJson {
    public int id;
    //@SerializedName("movie_name") //перенотация поля с сервера в классе
    public String title;
    public String img;
}
