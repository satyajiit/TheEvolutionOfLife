package com.satyajit.evolutionoflife.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.Utils.ServiceManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Typeface Cav ;

    ServiceManager sm = new ServiceManager(this); //Connectivity checker class

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


         sm.isConnectionAvailable();  //Checks for Internet Connection

        Cav =  Typeface.createFromAsset(getAssets(), "fonts/cav.ttf");


        setCustomTitlebar();



    }



void setCustomTitlebar(){
    //Set Title bar with Custom Typeface
    TextView tv = new TextView(getApplicationContext());
    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
    tv.setLayoutParams(lp);
    tv.setText("Evolution Of Life");
    tv.setTextSize(24);
    tv.setTextColor(Color.parseColor("#FFFFFF"));
    tv.setTypeface(Cav);
    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    getSupportActionBar().setCustomView(tv);

}

    public void startGraph(View V){

        if ( sm.isConnectionAvailable() ) { //IF Internet there

            startActivity(new Intent(this, TimeLineActivity.class));

            Toast toast = Toast.makeText(this, "OPENING GRAPHS...PLEASE WAiT...", Toast.LENGTH_LONG);
            LinearLayout toastLayout = (LinearLayout) toast.getView();
            TextView toastTV = (TextView) toastLayout.getChildAt(0);
            toastTV.setTypeface(Cav);
            toastTV.setTextSize(12);
            toast.show();
        }

    }

    public void startExplore(View V){

        if ( sm.isConnectionAvailable() )
        startActivity(new Intent(this,Vertebrates.class));

    }


    public void startViewAll(View V){

        if ( sm.isConnectionAvailable() )
            startActivity(new Intent(this,ViewAllActivity.class));

    }
    public void aboutDev(View V){

        startActivity(new Intent(this,AboutActivity.class));

    }


    }
