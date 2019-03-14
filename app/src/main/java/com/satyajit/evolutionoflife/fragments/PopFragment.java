package com.satyajit.evolutionoflife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.satyajit.evolutionoflife.R;
import com.satyajit.evolutionoflife.activity.pastActivity;
import androidx.fragment.app.DialogFragment;

public class PopFragment  extends DialogFragment {

String title,id;
int time;
TextView tv;
EditText eT;
Button jump,back;
    Button wiki;

    public PopFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.info_pop, container);  //Inflate the Layout


        initUI(view);  //Initialise the views

        setListeners();

        return view;
    }

    private void initUI(View v) {

        //Initialise the Values/views/others
        title = getArguments().getString("title");
        id = getArguments().getString("id");
        tv = v.findViewById(R.id.title);
        tv.setText(title);
        wiki = v.findViewById(R.id.wiki);
        jump = v.findViewById(R.id.jump);
        eT = v.findViewById(R.id.time);
        back = v.findViewById(R.id.back);


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


        jump.setOnClickListener(v -> {



            String temp = eT.getText().toString();

            if (temp.equals("")||temp.equals(" ")||temp.isEmpty()||(temp.charAt(0))>='A'||temp.length()>6){

                //Validate for a correct number value
                Toast.makeText(getActivity(),"Please enter Correct or Valid Value!!",Toast.LENGTH_LONG).show();
            }

            else {





                //Validation done...Start the activity
                dismiss(); //closes the popup
                time = Integer.parseInt(eT.getText().toString());

                if (time>0){
                    Intent i = new Intent(getActivity(), pastActivity.class);
                    i.putExtra("topic", title);
                    i.putExtra("id", id);
                    i.putExtra("time", time);
                    startActivity(i);
                }

                else    //Avoid Negative Numbers
                    Toast.makeText(getActivity(),"Please enter Correct or Valid Value!!",Toast.LENGTH_LONG).show();




            }
        });

    }




}