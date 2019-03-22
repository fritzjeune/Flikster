package com.example.flikster.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class Movie {
    private String posterPath;
    private String title;
    private String overview;
    private String backDropPath;

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

    public Movie(JSONObject object) throws JSONException {
        posterPath = object.getString("poster_path");
        title = object.getString("title");
        overview = object.getString("overview");
        backDropPath = object.getString("backdrop_path");
    }

}
