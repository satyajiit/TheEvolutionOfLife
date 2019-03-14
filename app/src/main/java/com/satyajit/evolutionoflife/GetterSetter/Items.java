package com.satyajit.evolutionoflife.GetterSetter;

/**
 *
 * Getter and Setter for Links Recycler View
 * By - Satyajit
 * Updated last 8th March 19
 *
 */


public class Items {
    private String name,id,time,key;  //Our Members


    public Items(String name,String id,String time,String key) {
        this.name = name;
        this.id = id;
        this.time = time;
        this.key = key;
    }


    public Items(String name,String id,String time) {
        this.name = name;
        this.id = id;
        this.time = time;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getTime() {
        return time;
    }




    public void setname(String name) {
        this.name = name;
    }






}