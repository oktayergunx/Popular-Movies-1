package com.example.oktay.popularmovies1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oktay.popularmovies1.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView mMovieListTextView;
    String query = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieListTextView = (TextView) findViewById(R.id.tv_movie_names);
        makeTheMovieDbQuery();
    }

    private void makeTheMovieDbQuery(){
        String theMovieDbQueryType = query;
        URL theMovieDbQueryUrl = NetworkUtils.buildUrl(theMovieDbQueryType);
        mMovieListTextView.setText(theMovieDbQueryUrl.toString());
        String theMovieDbQueryResults = null;
        try{
            theMovieDbQueryResults = NetworkUtils.getResponseFromHttpUrl(theMovieDbQueryUrl);
            mMovieListTextView.setText(theMovieDbQueryResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.popular) {
            Context context = MainActivity.this;
            String textToShow = "Popular Clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
