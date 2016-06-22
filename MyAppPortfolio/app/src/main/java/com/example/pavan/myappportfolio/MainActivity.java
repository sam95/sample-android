package com.example.pavan.myappportfolio;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button spotify = (Button) findViewById(R.id.app1);
        Button scores = (Button) findViewById(R.id.app2);
        Button lib = (Button) findViewById(R.id.app3);
        Button bib = (Button) findViewById(R.id.app4);
        Button xyz = (Button) findViewById(R.id.app5);
        Button cap = (Button) findViewById(R.id.app6);;

        //Effective way of using multiple buttons
        View.OnClickListener myhandler = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.app1:
                        Toast.makeText(getApplicationContext(),"This button will launch my spotify streamer app!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app2:
                        Toast.makeText(getApplicationContext(),"This button will launch my scores app!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app3:
                        Toast.makeText(getApplicationContext(),"This button will launch my library app!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app4:
                        Toast.makeText(getApplicationContext(),"This button will launch my build it bigger app!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app5:
                        Toast.makeText(getApplicationContext(),"This button will launch my xyz reader app!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app6:
                        Toast.makeText(getApplicationContext(),"This button will launch my capstone app!",Toast.LENGTH_SHORT).show();
                }
            }
        };

        spotify.setOnClickListener(myhandler);
        scores.setOnClickListener(myhandler);
        lib.setOnClickListener(myhandler);
        bib.setOnClickListener(myhandler);
        xyz.setOnClickListener(myhandler);
        cap.setOnClickListener(myhandler);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
