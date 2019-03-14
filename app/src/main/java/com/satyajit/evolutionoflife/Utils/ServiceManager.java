package com.satyajit.evolutionoflife.Utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.satyajit.evolutionoflife.fragments.ErrorDialog;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceManager {

    Context context;

    public ServiceManager(Context base) {
        context = base;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    public boolean isConnectionAvailable(){


        if (!isNetworkAvailable()) {
            ErrorDialog fragError = new ErrorDialog();
            fragError.show(((AppCompatActivity) context).getSupportFragmentManager(), "NO NET");
                                                        //will redirect or start the no internet connection pop up

            return false;
        }

        return true;

    }


}