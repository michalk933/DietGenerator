package com.example.michal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michal on 18.11.2016.
 */

public class DB_Product extends SQLiteOpenHelper {

    private static final String DB_NAME = "product.db";
    private static final int BD_VERSION = 1;

    public DB_Product(Context context) {
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
            db.execSQL("CREATE TABLE PRODUCT(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT,"
                    + "PRODUCENT TEXT, "
                    + "TYPE TEXT, "
                    + "KCAL_G INTEGER, "
                    + "IG INTEGER, "
                    + "WW_G INTEGER, "
                    + "B_G INTEGER, "
                    + "T_G INTEGER, "
                    + "BAN TEXT, "
                    + "DATE_ADD TEXT);");

            insetsTest(db,"Pierś z kurczaka","Unimięs","Białko",200,0,1,21,1,"Brak","12.12.2016");

            //ID_USE,Imię,Passwod,Data_Konta,Cel_Diety,Stan_Zdrowia,Typ_Diety ,Typ_Cukrzycy,Waga,Wzrost,Wiek,Poziom_Act

        }else if(oldVersionDB < 2){
            db.execSQL("ALTER TABLE TS ADD COLUMN F NUMERIC;");
        }

    }


    //Test methot, check using data base
    private static void insetsTest(SQLiteDatabase db,String name,String producent, String type,int kcal_g, int ig, int ww_g, int b_g,int t_g,String ban,String date){
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("PRODUCENT", producent);
        cv.put("TYPE", type);
        cv.put("KCAL_G", kcal_g);
        cv.put("IG", ig);
        cv.put("WW_G", ww_g);
        cv.put("B_G",b_g);
        cv.put("T_G", t_g);
        cv.put("BAN", ban);
        cv.put("DATE_ADD", date);

        db.insert("PRODUCT",null,cv);

    }


    // Hethot add new user into data base
    public boolean insertNewProduct(String name,String producent, String type,int kcal_g, int ig, int ww_g, int b_g,int t_g,String ban,String date){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("NAME", name);
        cv.put("PRODUCENT", producent);
        cv.put("TYPE", type);
        cv.put("KCAL_G", kcal_g);
        cv.put("IG", ig);
        cv.put("WW_G", ww_g);
        cv.put("B_G",b_g);
        cv.put("T_G", t_g);
        cv.put("BAN", ban);
        cv.put("DATE_ADD", date);

        long result = db.insert("PRODUCT",null,cv);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


}
