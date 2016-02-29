package com.example.pavan.popularmovies;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv = (TextView) findViewById(R.id.txtvi);
        String data = this.getIntent().getExtras().getString("data");
        String url = this.getIntent().getExtras().getString("url");
        String name = this.getIntent().getExtras().getString("name");
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(name);

        tv.setText(data);
        ImageView toolbarImage = (ImageView) findViewById(R.id.image_stretch_detail);
        Picasso.with(this)
                .load(url)
                .into(toolbarImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You like this movie!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
