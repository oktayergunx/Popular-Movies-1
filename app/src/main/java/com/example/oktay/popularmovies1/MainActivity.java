package com.example.oktay.popularmovies1;

import android.content.Context;
import android.os.AsyncTask;
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
        new TheMovieDbQueryTask().execute(theMovieDbQueryUrl);
    }

    public class TheMovieDbQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL queryUrl = urls[0];
            String theMovieDbQueryResults = null;
            try{
                theMovieDbQueryResults = NetworkUtils.getResponseFromHttpUrl(queryUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return theMovieDbQueryResults;
        }

        @Override
        protected void onPostExecute(String theMovieDbQueryResults) {
            if (theMovieDbQueryResults != null && !theMovieDbQueryResults.equals("")){
                mMovieListTextView.setText(theMovieDbQueryResults);
            }
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
            // TODO 3 remove the toast message when you are done with it.
            query = "top_rated";
            makeTheMovieDbQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
