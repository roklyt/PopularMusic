package com.example.android.popularmusic.utilities;

import android.content.Context;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class MovieJsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the movie.
     *
     * @param movieJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static String[] getSimpleWeatherStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {

        /* All movies are objects in the array of results */
        final String MOVIE_RESULTS = "results";

        /* Title object */
        final String MOVIE_TITLE = "title";

        /* Release date object */
        final String MOVIE_RELEASE_DATE = "release_date";

        /* Path to the movie poster */
        final String MOVIE_POSTER_PATH = "poster_path";

        /* Vote average object */
        final String MOVIE_VOTE_AVERAGE = "vote_average";

        /* Plot synopsis*/
        final String MOVIE_SYNOPSIS = "overview";

        /* Status code */
        final String MOVIE_STATUS_CODE = "status_code";

        /* Status message */
        final String MOVIE_STATUS_MESSGAE = "status_message";


        /* String array to hold each day's movie String */
        String[] parsedMovieData = null;

        JSONObject movieJson = new JSONObject(movieJsonStr);

        /* Is there an error? */
        if (movieJson.has(MOVIE_STATUS_CODE)) {
            int errorCode = movieJson.getInt(MOVIE_STATUS_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    Toast.makeText(context,movieJson.getString(MOVIE_STATUS_MESSGAE), Toast.LENGTH_LONG).show();
                    return null;
            }
        }

        JSONArray movieArray = movieJson.getJSONArray(MOVIE_RESULTS);

        parsedMovieData = new String[movieArray.length()];


        for (int i = 0; i < movieArray.length(); i++) {
            String title;
            String releaseDate;
            String posterPath;
            String average;
            String plotSynopsis;


            /* Get the JSON object representing one movie */
            JSONObject movieObject = movieArray.getJSONObject(i);

            /* Get the title */
            title = movieObject.getString(MOVIE_TITLE);

            /* Get release date*/
            releaseDate = movieObject.getString(MOVIE_RELEASE_DATE);

            /* Get poster path */
            posterPath = movieObject.getString(MOVIE_POSTER_PATH);

            /* Get average */
            average = movieObject.getString(MOVIE_VOTE_AVERAGE);

            /* get plot synopsis */
            plotSynopsis = movieObject.getString(MOVIE_SYNOPSIS);

            parsedMovieData[i] = title + " - " + releaseDate + " - " + posterPath + " - " + average + " - " + plotSynopsis + "\n\n";
        }

        return parsedMovieData;
    }
}