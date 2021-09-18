package com.hfad.movieitem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {
    // films - url
    @GET("films") //метод получения фильма
    Call<List<MovieJson>> getMovies();
    //@Query("sortBy") String sortBy - сортировка

    // метод для получения конкретного movies по id
    @GET("movies/{id}")
    Call<MovieJson> getMovieById(@Path("id") int id);
}
