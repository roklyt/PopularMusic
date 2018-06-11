package com.example.android.popularmusic;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.popularmusic.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMovieData();
    }

    private void loadMovieData(){
        new FetchMoviesTask().execute(new String[]{"popular", ""});

    }

    public class FetchMoviesTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String searchFor = params[0];
            String apiKey = params[1];
            URL movieRequestUrl = NetworkUtils.buildUrl(apiKey,searchFor);

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);


                return jsonMovieResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String movieData) {
            if (movieData != null) {
                TextView textView = findViewById(R.id.text_view);
                textView.setText(movieData);
            } else {
            }
        }
    }

}
