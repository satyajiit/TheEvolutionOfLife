package com.satyajit.evolutionoflife.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.satyajit.evolutionoflife.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AboutActivity extends AppCompatActivity {

    //Declarations
    Typeface Cav ;

    CardView volley,Graph,Spots,Picasso,Droid,Scroll;
    ImageView fb,linkedIn,gitHub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_dev_activity);



        setCustomTitlebar();

        initUI();

        setClickListeners();

    }

    void setCustomTitlebar(){
        //Set Title bar with Custom Typeface
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("About Dev");
        tv.setTextSize(24);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTypeface(Cav);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //Enable the up button

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); //Terminate the current Activity
        return true;
    }

    void initUI(){

        Cav =  Typeface.createFromAsset(getAssets(), "fonts/cav.ttf");
        volley = findViewById(R.id.all);
        Graph = findViewById(R.id.graph);
        Spots = findViewById(R.id.spots);
        Picasso = findViewById(R.id.pica);
        Droid = findViewById(R.id.gif);
        fb = findViewById(R.id.fb);
        linkedIn = findViewById(R.id.linked);
        gitHub = findViewById(R.id.github);
        Scroll = findViewById(R.id.scroll);

    }

    void setClickListeners(){

        volley.setOnClickListener(v -> openURL("https://github.com/google/volley"));
        Graph.setOnClickListener(v -> openURL("https://github.com/Team-Blox/GraphView"));
        Spots.setOnClickListener(v -> openURL("https://github.com/satyajiit/TheSpotsDialog"));
        Picasso.setOnClickListener(v -> openURL("https://square.github.io/picasso/"));
        Droid.setOnClickListener(v -> openURL("https://github.com/koral--/android-gif-drawable"));
        fb.setOnClickListener(v -> openURL("http://fb.com/satyajiit"));
        linkedIn.setOnClickListener(v -> openURL("https://www.linkedin.com/in/satyajiit/"));
        gitHub.setOnClickListener(v -> openURL("https://github.com/satyajiit"));
        Scroll.setOnClickListener(v -> openURL("https://github.com/turing-tech/MaterialScrollBar"));

    }

void openURL(String url){

        //URL Opener method to access the links with the browser
    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    startActivity(browserIntent);
}


}
