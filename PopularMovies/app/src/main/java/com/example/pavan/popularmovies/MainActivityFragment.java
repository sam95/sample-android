package com.example.pavan.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    int totlen;
    GridView gv;
    String thedata[];
    String[] thename;
    String[] theurl;
    ImageAdapter ia;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //startme();
        container = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        gv = (GridView) container.findViewById(R.id.gridid);

        ia = new ImageAdapter(getActivity());
        gv.setAdapter(ia);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iu = new Intent(view.getContext(),ScrollingActivity.class);
                iu.putExtra("data",thedata[position]);
                iu.putExtra("url",theurl[position]);
                iu.putExtra("name",thename[position]);
                startActivity(iu);
                Toast.makeText(getActivity(),String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        return container;
    }

    @Override
    public void onStart(){
        super.onStart();
        startme();
    }



    private void startme() {
        FetchTask ft = new FetchTask();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String choice = prefs.getString(getString(R.string.examplelist),"1");
        Log.e("choice",choice);
        ft.execute(choice);
    }

    public class FetchTask extends AsyncTask<String, Void, String[]> {
        String[] str;

        @Override
        protected void onPostExecute(String[] strings) {
            if (strings != null) {
                ia.clear();
                for(String s : strings){
                    Log.e("str",s);
                    ia.add(s);
                }
                gv.setAdapter(ia);
            }
        }

        @Override
        protected String[] doInBackground(String... params) {
            //url = https://api.themoviedb.org/3/movie/popular?api_key=mykey
            //url = https://api.themoviedb.org/3/movie/top_rated?api_key=mykey
            Uri.Builder build = new Uri.Builder();
            String key = "mykey";
            String myresponse = "";

            if(params[0].equals("1")){

                build.scheme("http").authority("api.themoviedb.org").appendPath("3").appendPath("movie").appendPath("popular")
                        .appendQueryParameter("api_key", key);
            }
            else
            {

                build.scheme("http").authority("api.themoviedb.org").appendPath("3").appendPath("movie").appendPath("top_rated")
                        .appendQueryParameter("api_key", key);
            }

            //build.scheme("http").authority("api.themoviedb.org").appendPath("3").appendPath("discover").appendPath("movie")
              //      .appendQueryParameter("api_key", key).appendQueryParameter("sort_by", keyword);

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Log.e("the url ", build.toString());
            try {
                URL url = new URL(build.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
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
                myresponse = buffer.toString();
                return nowparse(myresponse);

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        private String[] nowparse(String myresponse) {
            try {
                String[] str1;
                JSONObject js = new JSONObject(myresponse);
                JSONArray ja = js.getJSONArray("results");
                String imgul = "http://image.tmdb.org/t/p/w342/";
                totlen = ja.length();
                thedata = new String[totlen];
                theurl = new String[totlen];
                thename = new String[totlen];
                str = new String[totlen];
                for (int i = 0; i < totlen; i++) {
                    JSONObject obj = new JSONObject(ja.getString(i));
                    thedata[i] = "\n\nOverview : "+obj.getString("overview")+"\n\nRelease date : "+obj.getString("release_date")+"\n\nAverage rating : "+obj.getString("vote_average");
                    thename[i] = obj.getString("title");
                    theurl[i] = "http://image.tmdb.org/t/p/w500/"+obj.getString("backdrop_path").substring(1);
                    str[i]=imgul + obj.getString("poster_path");
                }
                return str;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
