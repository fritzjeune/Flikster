package com.example.flikster.models;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flikster.MovieDetailsActivity;
import com.example.flikster.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>  {
    ArrayList<Movie> movies;

    Config config;

    Context context;




    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public MovieAdapter (ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivPosterImage, ivBackdropImage;
        TextView tvTitle , tvOverview;

        public ViewHolder (View itemView) {
            super(itemView);

            ivPosterImage = itemView.findViewById(R.id.ivMovie);
            ivBackdropImage = itemView.findViewById(R.id.ivBackdropImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Movie movie = movies.get(position);
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                context.startActivity(intent);
            }
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.movie_item, parent,false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

//        get the user orientation.
        boolean isPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        String posterUrl = null;

//        get the poster urls....
        if (isPortrait) {
            posterUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
        } else {
            posterUrl = config.getImageUrl(config.getBackDropSize(), movie.getBackDropPath());
        }

        int placeHolderId = isPortrait ? R.drawable.flicks_movie_placeholder : R.drawable.flicks_backdrop_placeholder;
        ImageView imageView = isPortrait ? holder.ivPosterImage : holder.ivBackdropImage;

        int radius = 10;
        int margin = 10;
        GlideApp.with(context)
                .load(posterUrl)
                .placeholder(placeHolderId)
                .error(placeHolderId)
                .transform(new RoundedCornersTransformation(radius, margin))
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
