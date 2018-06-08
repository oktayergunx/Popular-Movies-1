package com.example.oktay.popularmovies1.utilities;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TheMovieDbJsonUtils {

    public static String[] getMovieInformationsFromJson(Context context, String json) throws JSONException {

        final String TMDB_BASE_URL = "https://image.tmdb.org/t/p/";
        final String TMDB_POSTER_SIZE = "w500";

        // You guys recommended me to use key strings in my last code review, so here it is :)
        final String TMDB_RESULTS = "results";
        final String TMDB_POSTER_PATH = "poster_path";
        final String TMDB_TITLE = "title";
        final String TMDB_VOTE = "vote_average";
        //hmm
        final String TMDB_OVERVIEW = "overview";
        final String TMDB_RELEASE_DATE = "release_date";

        String[] parsedMovieData = null;
        //I've got some help from: https://www.codevoila.com/post/65/java-json-tutorial-and-example-json-java-orgjson#toc_5
        //and once again the amazing sunshine app.

        JSONObject movieJson = new JSONObject(json);

        JSONArray movieArray = movieJson.getJSONArray(TMDB_RESULTS);

        parsedMovieData = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++){
            String poster_path, title, vote_average, overview, release_date;

            JSONObject movieResults = movieArray.getJSONObject(i);
            poster_path = movieResults.optString(TMDB_POSTER_PATH);
            title = movieResults.optString(TMDB_TITLE);
            vote_average = movieResults.optString(TMDB_VOTE);
            overview = movieResults.optString(TMDB_OVERVIEW);
            release_date = movieResults.optString(TMDB_RELEASE_DATE);

            parsedMovieData[i] = TMDB_BASE_URL + TMDB_POSTER_SIZE + poster_path;
        }
        return parsedMovieData;
    }
}
