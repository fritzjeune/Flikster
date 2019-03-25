package com.example.flikster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.flikster.models.Config;
import com.example.flikster.models.Movie;
import com.example.flikster.models.MovieAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

//    public String url;

    public final static String API_BASE_URL = "https://api.themoviedb.org/3";
    public final static String API_KEY_PARAM = "api_key";
    public final static String TAG = "activity_main";

    AsyncHttpClient client;

    ArrayList<Movie> movies;

    RecyclerView rvMovie;

    MovieAdapter adapter;

    Config config;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new AsyncHttpClient();

        movies = new ArrayList<>();

        getNowPlaying();

        getConfiguration();

        adapter = new MovieAdapter(movies);

        rvMovie = (RecyclerView) findViewById(R.id.rvMovie);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        rvMovie.setAdapter(adapter);

        rvMovie.addOnScrollListener(onScrollListener);

    }

    public RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        boolean hideToolBar = false;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (hideToolBar) {
                getSupportActionBar().hide();
            } else {
                getSupportActionBar().show();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 20) {
                hideToolBar = true;

            } else if (dy < -5) {
                hideToolBar = false;
            }
        }
    };

    private void getNowPlaying() {
        String url = API_BASE_URL + "/movie/now_playing";
        RequestParams params = new RequestParams();
        params.put(API_KEY_PARAM, getString(R.string.api_key));

        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("results");

                    for(int i = 0 ; i < result.length(); i++) {
                        Movie movie = new Movie(result.getJSONObject(i));
                        movies.add(movie);
                        adapter.notifyItemInserted(movies.size() - 1 );
                    }
                    Log.i(TAG, String.format("Loaded %s movies",result.length()));
                } catch (JSONException e) {
                    logError("error loading arrays",e ,true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("failed to get data from the now playing end-point", throwable, true);
            }
        });
    }

    public void getConfiguration() {
        String url = API_BASE_URL + "/configuration";
        RequestParams params = new RequestParams();
        params.put(API_KEY_PARAM, getString(R.string.api_key));

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    config = new Config(response);
                    Log.i(TAG, String.format("loading configuracion with posterBaseUrl %s and posterSize %s",
                            config.getPosterBaseUrl(),
                            config.getPosterSize()));
                    adapter.setConfig(config);

                    getNowPlaying();
                } catch (JSONException e) {
                    logError("failed parsing config", e , true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("failed getting config", throwable ,true);
            }
        } );
    }

//    error log message


    private void logError(String message , Throwable error, boolean alertUser){
        Log.e(TAG,message,error);

        if (alertUser) {
            Toast.makeText(getApplicationContext(),message , Toast.LENGTH_LONG).show();
        }
    }


}
