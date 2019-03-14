package com.satyajit.evolutionoflife.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.satyajit.evolutionoflife.GetterSetter.Items;
import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.Utils.AutoFitGridLayoutManager;
import com.satyajit.evolutionoflife.Utils.ServiceManager;
import com.satyajit.evolutionoflife.adapters.ItemsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class SelectionActivity extends AppCompatActivity {

    String name;
    Typeface Cav;
    public  int Start_Index,End_index;
    RecyclerView recyclerView;
    public List<Items> namesList = new ArrayList<>();
    private ItemsAdapter mAdapter;
    ServiceManager sm = new ServiceManager(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity_layout);

        Intent i = getIntent();

        name = i.getStringExtra("name");


        Cav =  Typeface.createFromAsset(getAssets(), "fonts/cav.ttf"); //Typeface Cavier Dreams

        setCustomTitlebar();

        if ( sm.isConnectionAvailable() ) { //Checks for internet

            initUI();

            chkRange();

            setupRecylerView();

            LoadtoList();

        }

    }
    void setCustomTitlebar(){
        //Set Title bar with Custom Typeface
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(name);
        tv.setTextSize(20);
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

    void setupRecylerView(){

        mAdapter = new ItemsAdapter(namesList);
        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 333);  //Per row 3 items ... 1000/3=333.33
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



    }

    void chkRange(){

        if (name.equalsIgnoreCase("Fish"))
        {
            Start_Index = 66;
            End_index = 75;
        }

       else if (name.equalsIgnoreCase("Amphibians"))
        {
            Start_Index = 76;
            End_index = 78;
        }
        else if (name.equalsIgnoreCase("Pre Mammals"))
        {
            Start_Index = 80;
            End_index = 84;
        }
        else if (name.equalsIgnoreCase("Mammals"))
        {
            Start_Index = 113;
            End_index = 201;
        }
        else if (name.equalsIgnoreCase("Reptiles"))
        {
            Start_Index = 85;
            End_index = 105;
        }

        else if (name.equalsIgnoreCase("Birds"))
        {
            Start_Index = 106;
            End_index = 112;
        }

    }

    void initUI(){

        recyclerView = findViewById(R.id.items);


    }


    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = this.getAssets().open("file.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    void LoadtoList(){


        String label ;
        JSONObject obj ;

        try {
            obj = new JSONObject(loadJSONFromAsset());



            for (int i =Start_Index;i<=End_index; i++) {

                if (obj.getJSONObject(String.valueOf(i)).has("d")) {
                    label = obj.getJSONObject(String.valueOf(i)).getString("d");

                    String temp = obj.getJSONObject(String.valueOf(i)).getString("parent");

                    if (obj.getJSONObject(temp).has("time"))
                    temp = obj.getJSONObject(temp).getString("time");
                    else {
                        temp = obj.getJSONObject(temp).getString("parent");
                        temp = obj.getJSONObject(temp).getString("time");
                    }

                    Items item = new Items(label,String.valueOf(i),temp);
                    namesList.add(item);
                }

                }


               if (obj.has(name)) {

                   Iterator<String> keys = obj.getJSONObject(name).keys();

                   while (keys.hasNext()){

                       label = keys.next();

                       Log.d("TZG",label);

                       String item_key = label;

                     label = obj.getJSONObject(name).getJSONObject(label).getString("name");

                       Log.d("TZG",label);
                           Items item = new Items(label,String.valueOf(Start_Index),obj.getJSONObject(name).getJSONObject(item_key).getString("time"));
                           namesList.add(item);





                   }

               }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter.notifyDataSetChanged();

    }


}
