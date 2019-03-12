package com.example.flikster.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flikster.R;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>  {
    ArrayList<Movie> movies;



    public MovieAdapter (ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPosterImage;
        TextView tvTitle , tvOverview;

        public ViewHolder (View itemView) {
            super(itemView);

            ivPosterImage = itemView.findViewById(R.id.ivMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);



        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.movie_item, parent,false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());


//    TODO - image by glide


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
