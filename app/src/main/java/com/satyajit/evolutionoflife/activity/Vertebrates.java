package com.satyajit.evolutionoflife.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.Utils.ServiceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Vertebrates extends AppCompatActivity {

    Typeface Cav;
    Intent i;
    ServiceManager sm = new ServiceManager(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cav =  Typeface.createFromAsset(getAssets(), "fonts/cav.ttf");



        setContentView(R.layout.activity_vertebrates);

        i = new Intent(this,SelectionActivity.class);

        setCustomTitlebar();


        sm.isConnectionAvailable();  //Checks for Internet Connection

    }

    void setCustomTitlebar(){
        //Set Title bar with Custom Typeface
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("Vertebrates");
        tv.setTextSize(24);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTypeface(Cav);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);


        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish(); //Terminate the current Activity
        return true;
    }

    /**
     * The next functions are onClick Methods from the vertebrates xml file / layout
     * Last Updated by Satyajit Pradhan @8th March 19
     */


    public void fish(View v){

        if ( sm.isConnectionAvailable() ) {
            //Checks for Internet Connection)
            i.putExtra("name", "Fish");
            startActivity(i);
        }
    }

    public void amphibians(View v){

        if ( sm.isConnectionAvailable() ) {
            i.putExtra("name", "Amphibians");
            startActivity(i);
        }
    }

    public void premam(View v){

        if ( sm.isConnectionAvailable() ) {
            i.putExtra("name", "Pre Mammals");
            startActivity(i);
        }
    }

    public void mammals(View v){

        if ( sm.isConnectionAvailable() ) {
            i.putExtra("name", "Mammals");
            startActivity(i);
        }

    }

    public void reptiles(View v){

        if ( sm.isConnectionAvailable() ) {
            i.putExtra("name", "Reptiles");
            startActivity(i);
        }

    }

    public void birds(View v){

        if ( sm.isConnectionAvailable() ) {
            i.putExtra("name", "Birds");
            startActivity(i);
        }
    }

}
