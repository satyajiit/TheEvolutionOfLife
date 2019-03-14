package com.satyajit.evolutionoflife.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.satyajit.evolutionoflife.GetterSetter.Items;
import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.Utils.AutoFitGridLayoutManager;
import com.satyajit.evolutionoflife.Utils.ServiceManager;
import com.satyajit.evolutionoflife.adapters.PastAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import pl.droidsonroids.gif.GifImageView;

public class pastActivity extends AppCompatActivity {

    String name, id;
    int time;
    Typeface Cav;
    RecyclerView recyclerView;
    public List<Items> namesList = new ArrayList<>();
    private PastAdapter mAdapter;
    TextView tv,emptyTXT;
    GifImageView IV;
    ServiceManager sm = new ServiceManager(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity_layout);

        if ( sm.isConnectionAvailable() ) {  //Checks for internet

            initUI(); //Initialise the Views

            setCustomTitlebar();

            setupRecylerView();

            LoadtoList();

        }

    }

    void setCustomTitlebar(){
        //Set Title bar with Custom Typeface
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(time+" MA years ago...");
        tv.setTextSize(18);
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

        mAdapter = new PastAdapter(namesList);
        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 333);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



    }


    void initUI(){

        Intent i = getIntent();
        recyclerView = findViewById(R.id.items);
        tv = findViewById(R.id.tv);
        name = i.getStringExtra("topic");
        id = i.getStringExtra("id");
         time = i.getIntExtra("time",0);
         Cav =  Typeface.createFromAsset(getAssets(), "fonts/cav.ttf"); //Typeface Cavier Dreams
        tv.setVisibility(View.VISIBLE);
        IV = findViewById(R.id.empty);
        emptyTXT = findViewById(R.id.emt_txt);

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


        String label;
        JSONObject obj ;

        try {
            obj = new JSONObject(loadJSONFromAsset());

            Items item;

            String temp = id;

            while (true) {

                if (obj.getJSONObject(temp).has("time")){

                        if (Integer.parseInt(obj.getJSONObject(temp).getString("time"))>=time)
                        {
                             label = obj.getJSONObject(temp).getString("name");


                             item = new Items(label,temp,obj.getJSONObject(temp).getString("time"));
                            namesList.add(item);

                            //we got the position of the near element , lets show those elements upto zero or LIFE element



                            showListUpto(obj.getJSONObject(temp).getString("parent"),obj);

                            break;
                        }
                }

                if (temp.equals("1")) {

                    //The User Perhaps entered a high value
                    //We didn't find any species or element matching with the time... :(
                    emptyTXT.setVisibility(View.VISIBLE);
                    IV.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.GONE);

                    setEmptyTextListener();

                    break;
                }


               temp = obj.getJSONObject(temp).getString("parent");

            }






        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter.notifyDataSetChanged();

    }


    void showListUpto(String index,JSONObject obj) {

        String label;
        try {


            while (true) {

                label = obj.getJSONObject(index).getString("name");
                Items item = new Items(label, index,obj.getJSONObject(index).getString("time"));
                namesList.add(item);

                //we got the position of the near element , lets show those elements upto zero or LIFE element

                if (index.equals("1")) break;

                // Log.d(TAG,obj.getJSONObject(temp).getString("parent"));
                index = obj.getJSONObject(index).getString("parent");

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter.notifyDataSetChanged();


    }

    void setEmptyTextListener(){

        emptyTXT.setOnClickListener(v -> finish());

    }

}
