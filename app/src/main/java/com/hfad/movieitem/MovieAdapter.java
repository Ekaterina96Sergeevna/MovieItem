package com.hfad.movieitem;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<com.hfad.movieitem.MovieViewHolder> {
    private List<com.hfad.movieitem.MovieItem> items;
    private LayoutInflater inflater;

    public MovieAdapter(List<com.hfad.movieitem.MovieItem> items, LayoutInflater inflater) {
        this.items = items;
        this.inflater = inflater;
    }

    public com.hfad.movieitem.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new com.hfad.movieitem.MovieViewHolder(inflater.inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull com.hfad.movieitem.MovieViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    public int getItemCount(){
        return items.size();
    }
}
