package com.example.michal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michal on 18.11.2016.
 */

public class DB_DietPlan extends SQLiteOpenHelper {

    private static final String DB_NAME = "dietPlan.db";
    private static final int BD_VERSION = 1;

    public DB_DietPlan(Context context) {
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
            db.execSQL("CREATE TABLE PLAN(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "KCA INTEGER,"
                    + "WW INTEGER, "
                    + "B INTEGER, "
                    + "T INTEGER, "
                    + "MEAL INTEGER);");




            insetsTest(db,2000,200,150,80,6);



        }else if(oldVersionDB < 2){
            db.execSQL("ALTER TABLE TS ADD COLUMN F NUMERIC;");
        }

    }


    //Test methot, check using data base
    private static void insetsTest(SQLiteDatabase db,int kcal,int Ww,int B,int T,int meal){
        ContentValues cv = new ContentValues();
        cv.put("KCA", kcal);
        cv.put("WW", Ww);
        cv.put("B", B);
        cv.put("T", T);
        cv.put("MEAL", meal);

        db.insert("PLAN",null,cv);

    }


    // Hethot add new user into data base
    public boolean insertNewDiet(int kcal,int Ww,int B,int T,int meal){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("KCA", kcal);
        cv.put("WW", Ww);
        cv.put("B", B);
        cv.put("T", T);
        cv.put("MEAL", meal);

        long result = db.insert("PLAN",null,cv);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
