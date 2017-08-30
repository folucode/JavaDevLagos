package com.example.android.javadevlagos;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.javadevlagos.R.id.profile;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    /**
     * Url for the developers data from Github */
    private static final String GITHUB_API_URL =
            "https://api.github.com/search/users?q=language:java+location:lagos";

    /** adapter for the list of developers*/
    private DevelopersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developers_activity);

        /** Find the reference to the list {@link ListView} in the layout */
        ListView devListView = (ListView) findViewById(R.id.list);

        /** Create a new adapter that takes an empty list of developers as input*/
        mAdapter = new DevelopersAdapter(this, new ArrayList<Developers>());

        /** set the adapter on the @link ListView so the list can be populated
         * in the user interface
         */

        devListView.setAdapter(mAdapter);


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        devListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Developers currentDeveloper = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                String developerImg = null;
                if (currentDeveloper != null) {
                    developerImg = (currentDeveloper.getmImageUrl());
                }
                String developerUsername = null;
                if (currentDeveloper != null) {
                    developerUsername = currentDeveloper.getmUsername();
                }
                String developerUrl = null;
                if (currentDeveloper != null) {
                    developerUrl = currentDeveloper.getmProfileUrl();
                }

                // Create a new intent to send the parameters to the next activity
                Intent profileIntent = new Intent(MainActivity.this, Profile.class);
                profileIntent.putExtra("developerImg", developerImg);
                profileIntent.putExtra("developerUsername", developerUsername);
                profileIntent.putExtra("developerUrl", developerUrl);

                // Send the intent to launch a new activity
                startActivity(profileIntent);
            }
        });

        DevelopersAsyncTask task = new DevelopersAsyncTask();
        task.execute(GITHUB_API_URL);
    }

    private class DevelopersAsyncTask extends AsyncTask<String, Void, List<Developers>> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param urls The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected List<Developers> doInBackground(String... urls) {
            if(urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Developers> result = QueryUtils.fetchDevelopersData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Developers> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
