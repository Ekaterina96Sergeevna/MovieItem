package com.hfad.movieitem;

import android.graphics.BlurMaskFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;


public class MovieViewHolder extends RecyclerView.ViewHolder {
   private TextView titleTv;
   private ImageView imageViewPicasso, imageViewGlide;


    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        titleTv = itemView.findViewById(R.id.titleTv);
        imageViewPicasso = itemView.findViewById(R.id.imageIv);
        imageViewGlide = itemView.findViewById(R.id.imageIvGlide);


    }

    public void bind (com.hfad.movieitem.MovieItem movieItem){
        this.titleTv.setText(movieItem.title);

        Picasso.get()
                .load(movieItem.imageUrl)
                .fit()
                .centerCrop() //растянуть на весь контейнер
                .placeholder(R.drawable.ic_baseline_ac_unit_24) //заставка изображения пока грузится
                .error(R.drawable.ic_baseline_error_outline_24) //заставка если есть ошибка
                .into(imageViewPicasso);
        //transform - трансформации

        Glide.with(itemView.getContext())
                .load(movieItem.imageUrl)
                .centerCrop() //растянуть на весь контейнер
                //.apply(RequestOptions.bitmapTransform(new BlurTransformation(10, 1)))
                .placeholder(R.drawable.ic_baseline_ac_unit_24) //заставка изображения пока грузится
                .error(R.drawable.ic_baseline_error_outline_24) //заставка если есть ошибка
                .into(imageViewGlide);
    }




}
