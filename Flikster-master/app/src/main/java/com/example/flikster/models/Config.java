package com.example.flikster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Config {

    String posterBaseUrl;

    String posterSize;

    String backDropSize;

    public Config (JSONObject object) throws JSONException {

        JSONObject image = object.getJSONObject("images");
        posterBaseUrl = image.getString("secure_base_url");
        JSONArray posterSizeOption = image.getJSONArray("poster_sizes");
        posterSize = posterSizeOption.optString(3,"w342");
        JSONArray backDropSizeOptions = image.getJSONArray("backdrop_sizes");
        backDropSize = backDropSizeOptions.optString(1, "w780");
    }

    public String getImageUrl (String size , String path) {
        return String.format("%s%s%s" , posterBaseUrl, size , path);
    }

    public String getPosterBaseUrl() {
        return posterBaseUrl;
    }

    public String getPosterSize() {
        return posterSize;
    }

    public String getBackDropSize() {
        return backDropSize;
    }
}
