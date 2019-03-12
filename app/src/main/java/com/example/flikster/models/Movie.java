package com.example.flikster.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private String posterPath;
    private String title;
    private String overview;

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Movie(JSONObject object) throws JSONException {
        posterPath = object.getString("poster_path");
        title = object.getString("title");
        overview = object.getString("overview");

    }


// add some more option.

//    on more comment

}
