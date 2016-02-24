package com.example.pavan.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sameera on 24-02-2016.
 */
public class ImageAdapter extends BaseAdapter {

    private Context cont;
    List<String> list = new ArrayList<>();

    public ImageAdapter(Context context) {
        this.cont = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Log.e("here","there");
        ImageView view = (ImageView) convertView;
        if (view == null) {
            view = new ImageView(cont);
            //view.setLayoutParams(new GridView.LayoutParams(85, 85));
            //view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //view.setPadding(8,8,8,8);

        } else {
            view = (ImageView) convertView;
        }

        Picasso.with(cont).setLoggingEnabled(true);
        //Log.e("the url", list.get(position));
        Picasso.with(cont).load(list.get(position)).into(view);
        return view;
    }
    public void add(String s) {
        list.add(s);
        //Log.e("size",String.valueOf(list.size()));
    }

    public void clear() {
        list.clear();
    }
}
