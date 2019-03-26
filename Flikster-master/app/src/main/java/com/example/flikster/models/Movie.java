package com.example.flikster.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.lang.reflect.Array;
@Parcel
public class Movie {
    public String posterPath;
    public String title;
    public String overview;
    public String backDropPath;
    public double voteAverage;
    public String releaseDate;
    public double popularity;

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public Movie() {}

    public Movie(JSONObject object) throws JSONException {
        posterPath = object.getString("poster_path");
        title = object.getString("title");
        overview = object.getString("overview");
        backDropPath = object.getString("backdrop_path");
        voteAverage = object.getDouble("vote_average");
        releaseDate = object.getString("release_date");
        popularity = object.getDouble("popularity");
    }

    public double getVoteAverage() {
        return voteAverage;
    }
}
