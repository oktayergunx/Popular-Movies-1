package com.example.oktay.popularmovies1.utilities;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TheMovieDbJsonUtils {

    public static Movie getMovieInformationsFromJson(String json) throws JSONException {
        //I've got the following codes from: https://www.codevoila.com/post/65/java-json-tutorial-and-example-json-java-orgjson#toc_5
        //created a new movie class, I've also seen this from: https://www.youtube.com/watch?v=KSX4zIhiZlM

        Movie movie = new Movie();

        JSONObject movieJsonObject = new JSONObject(json);
        JSONObject results = movieJsonObject.getJSONObject("results");
        String title = results.getString("title");

        //setters
        movie.setTitle(title);

        return movie;
    }
}
