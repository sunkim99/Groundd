package com.example.ground;

import android.app.Application;

public class allround extends Application {
    private String ID; //아이디
    private String SCHOOL; //학교이름
    private int ADMIN; //관리자 번호
    private String NICKNAME; //닉네임
    private String SCHADD, SCHPH; //학교주소, 학교 연락처

    @Override
    public void onCreate() {
        ID = "";
        SCHOOL = "";
        ADMIN = 0;
        NICKNAME = "";
        SCHADD = "";
        SCHPH = "";
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

    public void setSCHADD(String SCHADD) {
        this.SCHADD = SCHADD;
    }

    public String getSCHADD() {
        return SCHADD;
    }

    public void setSCHPH(String SCHPH) { this.SCHPH = SCHPH; }

    public String getSCHPH() {
        return SCHPH;
    }

}
