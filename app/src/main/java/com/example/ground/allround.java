package com.example.ground;

import android.app.Application;

public class allround extends Application {
    private String ID;
    private String SCHOOL;
    private int ADMIN;
    private String NICKNAME;

    @Override
    public void onCreate() {
        ID = "";
        SCHOOL = "";
        ADMIN = 0;
        NICKNAME = "";
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setSCHOOL(String SCHOOL) {
        this.SCHOOL = SCHOOL;
    }

    public String getSCHOOL() {
        return SCHOOL;
    }

    public void setADMIN(int ADMIN) {
        this.ADMIN = ADMIN;
    }

    public int getADMIN() {
        return ADMIN;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

}
