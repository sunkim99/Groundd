package com.example.ground;

import android.app.Application;

public class allround extends Application {
    private String ID; //아이디
    private String SCHOOL; //학교이름
    private int ADMIN; //관리자 번호
    private String NICKNAME; //닉네임
    private String SCHADD, SCHPH; //학교주소, 학교 연락처
    private int Char_hair, Char_face, Char_cloth, Char_acce;
    private String notNum;

    @Override
    public void onCreate() {
        ID = "";
        SCHOOL = "";
        ADMIN = 0;
        NICKNAME = "";
        SCHADD = "";
        SCHPH = "";


        Char_hair = 0;
        Char_face = 0;
        Char_cloth = 0;
        Char_acce = 0;
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setID(String ID) {
        this.ID = ID;
    } //아이디

    public String getID() {
        return ID;
    }

    public void setSCHOOL(String SCHOOL) {
        this.SCHOOL = SCHOOL;
    } //학교이름

    public String getSCHOOL() {
        return SCHOOL;
    }

    public void setADMIN(int ADMIN) {
        this.ADMIN = ADMIN;
    } //관리자 여부

    public int getADMIN() {
        return ADMIN;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    } //닉네임

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setSCHADD(String SCHADD) {
        this.SCHADD = SCHADD;
    } //학교 주소

    public String getSCHADD() {
        return SCHADD;
    }

    public void setSCHPH(String SCHPH) { this.SCHPH = SCHPH; } //학교 연락처

    public String getSCHPH() {
        return SCHPH;
    }




    public void setChar_hair(int Char_hair) {
        this.Char_hair = Char_hair;
    }

    public int getChar_hair() {
        return Char_hair;
    }

    public void setChar_face(int Char_face) {
        this.Char_face = Char_face;
    }

    public int getChar_face() {
        return Char_face;
    }

    public void setChar_cloth(int Char_cloth) { this.Char_cloth = Char_cloth; }

    public int getChar_cloth() {
        return Char_cloth;
    }

    public void setChar_acce(int Char_acce) {
        this.Char_acce = Char_acce;
    }

    public int getChar_acce() {
        return Char_acce;
    }

}
