package com.satyajit.evolutionoflife.fragments;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.satyajit.evolutionoflife.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import androidx.fragment.app.DialogFragment;

public class BackPop  extends DialogFragment {

    String title,id;
    TextView tv,data,time;
    Button back;
    Button wiki;


    public BackPop() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.one_stage_back_pop, container);  //Inflate the Layout


        initUI(view);  //Initialise the views

        setListeners();

        LoadToTextView();

        return view;
    }

    private void initUI(View v) {

        //Initialise the Values/views/others
        title = getArguments().getString("topic");
        id = getArguments().getString("id");
        tv = v.findViewById(R.id.title);
        wiki = v.findViewById(R.id.wiki);
        back = v.findViewById(R.id.back);
        data = v.findViewById(R.id.data);
        time = v.findViewById(R.id.timee);

    }

    private void setListeners(){

        //Set the Listeners for buttons
        getDialog().setOnKeyListener((arg0, keyCode, event) -> {
            // TODO Auto-generated method stub
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dismiss();
            }
            return true;
        });


        wiki.setOnClickListener(v -> {

            //Show about/wiki dialog
            dismiss();
            Bundle bundle = new Bundle();
            bundle.putString("topic", title );
            bundle.putString("id", id );
            InfoDialog fragInfo = new InfoDialog();
            fragInfo.setArguments(bundle);
            fragInfo.show(getFragmentManager(),"INFO");
        });


        back.setOnClickListener(v -> {

            //Show about/wiki dialog
            dismiss();
            Bundle bundle = new Bundle();
            bundle.putString("topic", title );
            bundle.putString("id", id );
            BackPop fragInfo = new BackPop();
            fragInfo.setArguments(bundle);
            fragInfo.show(getFragmentManager(),"INFO");

        });




    }


    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getActivity().getAssets().open("file.json");
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


    private void LoadToTextView(){


        JSONObject obj ;

        try {
            obj = new JSONObject(loadJSONFromAsset());

            String temp = id;

            temp = obj.getJSONObject(temp).getString("parent");

            title = obj.getJSONObject(temp).getString("name");

            if (!temp.equals("1"))
            time.setText("( "+obj.getJSONObject(temp).getString("time")+" MA )");

            else {

                back.setVisibility(View.GONE);
                time.setVisibility(View.GONE);

            }

            tv.setText(title);

            data.setText(title);


            id = temp;



    } catch (
    JSONException e) {
        e.printStackTrace();
    }



}


}