package com.example.ground;

import android.app.Application;

public class allround extends Application {
    private String ID;

    @Override
    public void onCreate() {
        ID = "";
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }

}
