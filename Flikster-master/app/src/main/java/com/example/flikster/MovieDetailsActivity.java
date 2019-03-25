package com.example.flikster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flikster.models.Config;
import com.example.flikster.models.GlideApp;
import com.example.flikster.models.Movie;

import org.json.JSONObject;
import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {
    Movie movie;
    Toolbar toolbar;
    TextView tvTitle, tvOverview;
    RatingBar rtMovie;
    ImageView ivMovie;

    Config config;


    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        toolbar = findViewById(R.id.tbMovie);
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rtMovie = findViewById(R.id.rtMovie);
        ivMovie = findViewById(R.id.ivCollapsing);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Movie Description");




        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        String  posterUrl = String.format("https://image.tmdb.org/t/p/w342/%s",movie.getBackDropPath());
//        String url = config.getImageUrl(config.getBackDropSize(), movie.getBackDropPath());

        int margin = 10;
        int radius = 10;
        GlideApp.with(this)
                .load(posterUrl)
                .transform(new RoundedCornersTransformation(radius, margin))
                .into(ivMovie);





        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

//        vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = (float) movie.getVoteAverage();
        rtMovie.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);



    }


}
