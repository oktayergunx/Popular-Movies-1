package com.example.oktay.popularmovies1.utilities;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TheMovieDbJsonUtils {

    public static String[] getMovieInformationsFromJson(Context context, String json) throws JSONException {
        // You guys recommended me to use key strings in my last code review, so here it is :)

        final String TMDB_RESULTS = "results";
        final String TMDB_TITLE = "title";

        String[] parsedMovieData = null;
        //I've got the following codes from: https://www.codevoila.com/post/65/java-json-tutorial-and-example-json-java-orgjson#toc_5
        //created a new movie class, I've also seen this from: https://www.youtube.com/watch?v=KSX4zIhiZlM

        JSONObject movieJson = new JSONObject(json);

        JSONArray movieArray = movieJson.getJSONArray(TMDB_RESULTS);

        parsedMovieData = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++){
            String title;
            JSONObject movieResults = movieArray.getJSONObject(i);
            title = movieResults.optString(TMDB_TITLE);

            parsedMovieData[i] = title;
        }
        return parsedMovieData;
    }
}
