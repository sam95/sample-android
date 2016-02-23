package com.example.pavan.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    int totlen;
    String[] imgurl;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        container = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        GridView gv = (GridView) container.findViewById(R.id.gridid);
        gv.setAdapter(new ImageAdapter(getActivity().getApplicationContext()));
        return container;
    }

    @Override
    public void onStart(){
        super.onStart();
        startme();
    }

    private void startme() {
        FetchTask ft = new FetchTask();
        ft.execute();
    }

    public class FetchTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            //url = https://api.themoviedb.org/3/discover/movie?api_key=mykey&sort_by=popularity.desc

            Uri.Builder build = new Uri.Builder();
            String key = "mykey";
            String myresponse="";

            build.scheme("http").authority("api.themoviedb.org").appendPath("3").appendPath("discover").appendPath("movie")
                    .appendQueryParameter("api_key",key).appendQueryParameter("sort_by","popularity.desc");

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Log.e("the url ", build.toString());
            try
            {
                URL url = new URL(build.toString());
                Log.e("reached","finally");
                urlConnection = (HttpURLConnection) url.openConnection();
                Log.e("reached","finally");
                urlConnection.setRequestMethod("GET");
                Log.e("reached", "finally");
                urlConnection.connect();
                Log.e("reached","finally");
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    myresponse = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    myresponse = null;
                }
                myresponse = buffer.toString();
                Log.e("the json",myresponse);
                nowparse(myresponse);

            }
            catch (java.io.IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void nowparse(String myresponse) {
        try {
            JSONObject js = new JSONObject(myresponse);
            JSONArray ja = js.getJSONArray("results");
            String imgul="http://image.tmdb.org/t/p/w185/";
            totlen=ja.length();
            imgurl = new String[ja.length()];
            for(int i=0;i<totlen;i++){
                JSONObject obj = new JSONObject(ja.getString(i));
                imgurl[i] = imgul + obj.getString("poster_path");
                Log.e(String.valueOf(i),imgurl[i]);
                //Picasso.with(getActivity()).load(imgurl).into(R.id.gridid);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
