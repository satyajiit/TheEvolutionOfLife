package com.satyajit.evolutionoflife.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.satyajit.evolutionoflife.GetterSetter.Items;
import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.Utils.AutoFitGridLayoutManager;
import com.satyajit.evolutionoflife.adapters.SortedAdapter;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

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
public class ViewAllActivity extends AppCompatActivity {

    Typeface Cav;
    RecyclerView recyclerView;
    private SortedAdapter mAdapter;
    public List<Items> namesList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_all);

        Cav =  Typeface.createFromAsset(getAssets(), "fonts/cav.ttf");

        setCustomTitlebar();

        initUI();

        setupRecylerView();

        LoadtoList();



    }


    void setCustomTitlebar(){
        //Set Title bar with Custom Typeface
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("All Species");
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

    void setupRecylerView(){



        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 333);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        ((DragScrollBar)findViewById(R.id.dragScrollBar))
                .setIndicator(new AlphabetIndicator(this), true);



    }

    void initUI(){

        mAdapter = new SortedAdapter();
        recyclerView = findViewById(R.id.items_all);


    }


    void LoadtoList(){


        String label;
        JSONObject obj ;
        String image;
        String time;

        try {
            obj = new JSONObject(loadJSONFromAsset());


            for (int i =1;i<=201; i++) {

                if (obj.getJSONObject(String.valueOf(i)).has("d")) {
                    label = obj.getJSONObject(String.valueOf(i)).getString("d");
                    image = obj.getJSONObject(String.valueOf(i)).getString("d");

                }

                    else {
                    label = obj.getJSONObject(String.valueOf(i)).getString("name");
                         image = "NULL";
                    }


                if (obj.getJSONObject(String.valueOf(i)).has("time"))
                            time = obj.getJSONObject(String.valueOf(i)).getString("time");
                else time = "NULL";


                    Items item = new Items(label, image,time,String.valueOf(i));
                    namesList.add(item);
                }


                String[] names={"Birds","Amphibians","Pre Mammals","Reptiles","Mammals"};

            int Start_Index = 0;

            for (int i =0 ; i<names.length;i++)
            {
                try {
                    Iterator<String> keys = obj.getJSONObject(names[i]).keys();

                    if (names[i].equalsIgnoreCase("Fish"))
                    {
                        Start_Index = 66;
                    }

                    else if (names[i].equalsIgnoreCase("Amphibians"))
                    {
                        Start_Index = 76;
                    }
                    else if (names[i].equalsIgnoreCase("Pre Mammals"))
                    {
                        Start_Index = 80;
                    }
                    else if (names[i].equalsIgnoreCase("Mammals"))
                    {
                        Start_Index = 113;
                    }
                    else if (names[i].equalsIgnoreCase("Reptiles"))
                    {
                        Start_Index = 85;
                    }

                    else if (names[i].equalsIgnoreCase("Birds"))
                    {
                        Start_Index = 106;
                    }








                    while (keys.hasNext()) {

                        label = keys.next();

                        Log.d("TZG", label);

                        String item_key = label;

                        label = obj.getJSONObject(names[i]).getJSONObject(label).getString("name");


                        Items item = new Items(label, label, obj.getJSONObject(names[i]).getJSONObject(item_key).getString("time"),String.valueOf(Start_Index));
                        namesList.add(item);

                    }
                }

                catch (JSONException e){


                }

            }





          mAdapter.addAll(namesList);


} catch (JSONException e) {
                         e.printStackTrace();
        }

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





}
