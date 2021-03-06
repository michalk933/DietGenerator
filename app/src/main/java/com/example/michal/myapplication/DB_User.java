package com.example.michal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michal on 15.11.2016.
 */

public class DB_User extends SQLiteOpenHelper {

    private static final String DB_NAME = "user.db";
    private static final int BD_VERSION = 1;

    public DB_User(Context context) {
        super(context, DB_NAME, null, BD_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        upData(db, 0, BD_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        upData(db,oldVersion,newVersion);
    }

    private void upData(SQLiteDatabase db,int oldVersionDB, int newVersionDB) {
        if (oldVersionDB < 1) {
            db.execSQL("CREATE TABLE USER(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT,"
                    + "PASS TEXT, "
                    + "DATE_ADD TEXT, "
                    + "AIM_DIET TEXT, "
                    + "HEALTH TEXT, "
                    + "TYP_DIET TEXT, "
                    + "TYP_DIABETS INTEGER, "
                    + "WEIGHT INTEGER, "
                    + "HEIGHT INTEGER, "
                    + "AGE INTEGER, "
                    + "SEX INTEGER, "
                    + "LVL_ACTI INTEGER,"
                    + "KCAL INTEGER);");

            insetsTest(db,"Janusz","Cebula","15.11.2016","Redukcja","fat","carbo",1,86,178,23,1,2,2000);

            //ID_USE,Imię,Passwod,Data_Konta,Cel_Diety,Stan_Zdrowia,Typ_Diety ,Typ_Cukrzycy,Waga,Wzrost,Wiek,Poziom_Act

        }else if(oldVersionDB < 2){
            db.execSQL("ALTER TABLE TS ADD COLUMN F NUMERIC;");
        }

    }


    //Test methot, check using data base
    private static void insetsTest(SQLiteDatabase db,String name,String pass,String date_add,
                                   String aim_diet,String health,String typ_diet,int typ_diabets,
                                   int weight,int height,int age,int sex, int lvl ,int kcal){
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("PASS", pass);
        cv.put("DATE_ADD", date_add);
        cv.put("AIM_DIET", aim_diet);
        cv.put("HEALTH", health);
        cv.put("TYP_DIET", typ_diet);
        cv.put("TYP_DIABETS",typ_diabets);
        cv.put("WEIGHT", weight);
        cv.put("HEIGHT", height);
        cv.put("AGE", age);
        cv.put("SEX", sex);
        cv.put("LVL_ACTI", lvl);
        cv.put("KCAL", kcal);
        db.insert("USER",null,cv);

    }


    // Hethot add new user into data base
    public boolean insertNewUser(String name,String pass,String date_add,
                                 String aim_diet,String health,String typ_diet,int typ_diabets,
                                 int weight,int height,int age,int sex,int lvl,int kcal){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("NAME", name);
        cv.put("PASS", pass);
        cv.put("DATE_ADD", date_add);
        cv.put("AIM_DIET", aim_diet);
        cv.put("HEALTH", health);
        cv.put("TYP_DIET", typ_diet);
        cv.put("TYP_DIABETS",typ_diabets);
        cv.put("WEIGHT", weight);
        cv.put("HEIGHT", height);
        cv.put("AGE", age);
        cv.put("SEX", sex);
        cv.put("LVL_ACTI", lvl);
        cv.put("KCAL", kcal);

        long result = db.insert("USER",null,cv);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


}
