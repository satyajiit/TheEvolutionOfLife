package com.satyajit.evolutionoflife.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.satyajit.evolutionoflife.R;

import androidx.fragment.app.DialogFragment;

public class ErrorDialog  extends DialogFragment {

    private Button ok;


    public ErrorDialog() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        setCancelable(false);

        View view = inflater.inflate(R.layout.no_internet_pop, container);  //Inflate the Layout

        initUI(view);

        setListeners();

        return view;
    }


    private void initUI(View v) {


                ok = v.findViewById(R.id.ok);


    }

    private void setListeners(){

                ok.setOnClickListener(v -> {

                    getActivity().finish();

                    dismiss();
                });

    }

}
