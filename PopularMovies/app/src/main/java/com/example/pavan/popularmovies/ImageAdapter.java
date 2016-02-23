package com.example.pavan.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

/**
 * Created by Sameera on 24-02-2016.
 */
public class ImageAdapter extends BaseAdapter {

    private Context cont;
    public String[] eatFoodyImages={
            "http://image.tmdb.org/t/p/w185//inVq3FRqcYIRl2la8iZikYYxFNR.jpg"
            ,"http://image.tmdb.org/t/p/w185//jjBgi2r5cRt36xF6iNUEhzscEcb.jpg"
            ,"http://image.tmdb.org/t/p/w185//5aGhaIHYuQbqlHWvWYqMCnj40y2.jpg"
            ,"http://image.tmdb.org/t/p/w185//hE24GYddaxB9MVZl1CaiI86M3kp.jpg"
            ,"http://image.tmdb.org/t/p/w185//kqjL17yufvn9OVLyXYpvtyrFfak.jpg"
            ,"http://image.tmdb.org/t/p/w185//5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg"
            ,"http://image.tmdb.org/t/p/w185//q0R4crx2SehcEEQEkYObktdeFy.jpg"
            ,"http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"
            ,"http://image.tmdb.org/t/p/w185//cgxEscv6TQRK6a514FwuJOjcqQ5.jpg"
            ,"http://image.tmdb.org/t/p/w185//oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg"
            ,"http://image.tmdb.org/t/p/w185//fYzpM9GmpBlIC893fNjoWCwE24H.jpg"
            ,"http://image.tmdb.org/t/p/w185//xSE4NBFDzqedwa4AIj99r1Z7ljF.jpg"
            ,"http://image.tmdb.org/t/p/w185//b9uYMMbm87IBFOq59pppvkkkgNg.jpg"
             ,"http://image.tmdb.org/t/p/w185//6vzNCyVNPkcITEMKl3oXnWm8QMs.jpg"
            ,"http://image.tmdb.org/t/p/w185//36zbHT728v00HbbUBmrsOMu7Hde.jpg"
            ,"http://image.tmdb.org/t/p/w185//rtQmqGnO0q1IdwdjFL6WdunQU6i.jpg"
             ,"http://image.tmdb.org/t/p/w185//aAmfIX3TT40zUHGcCKrlOZRKC7u.jpg"
            ,"http://image.tmdb.org/t/p/w185//eqFckcHuFCT1FrzLOAvXBb4jHwq.jpg"
            ,"http://image.tmdb.org/t/p/w185//cWERd8rgbw7bCMZlwP207HUXxym.jpg",
            "http://image.tmdb.org/t/p/w185//t5tGykRvvlLBULIPsAJEzGg1ylm.jpg"

};


    public ImageAdapter(Context context) {
        //this.eatFoodyImages = mys;
        this.cont = context;
    }

    public void setEatFoodyImages(String[] eatFoodyImages) {
        this.eatFoodyImages = eatFoodyImages;
    }


    @Override
    public int getCount() {
        return eatFoodyImages.length;
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
        ImageView view = (ImageView) convertView;
        if (view == null) {
            view = new ImageView(cont);
            //view.setLayoutParams(new GridView.LayoutParams(85, 85));
            //view.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else {
            view = (ImageView) convertView;
        }
        //String url = (String) getItem(position);
        Picasso.with(cont).setLoggingEnabled(true);
        //Picasso.with(cont)
         //       .load(eatFoodyImages[position])
         //       .into(view);
        Picasso.with(cont).load(eatFoodyImages[position]).into(view);
        //Picasso.with(cont).load(url).into(view);

        return view;
    }
}
