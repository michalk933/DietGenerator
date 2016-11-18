package com.example.michal.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by michal on 18.11.2016.
 */

public class DietPlan {

    private DB_DietPlan db_dietPlan;
    private String health;
    private int typDiabets;
    private String typDiet;

    private int kcal;
    private int Ww;
    private int B;
    private int T;
    private int meal = 6;

    private UserCreate userCreate;

    public DietPlan(){
        setDietPlane();
        //zapisywanie danych z bazy danych ;)
    }

    public DietPlan(int kcal, String healt, int typ, String newtypDiet){
        this.kcal = kcal;
        this.health = healt;
        this.typDiabets = typ;
        this.typDiet = newtypDiet;
        getProportionsBTWinMeal();
        addNewPlanBD();

    }



    public void setKcal(int newKcal){this.kcal = newKcal;}
    public void setWw(int newWw){this.Ww = newWw;}
    public void setB(int newB){this.B = newB;}
    public void setT(int newT){this.T = newT;}
    public void setmeal(int newMeal){this.meal = newMeal;}
    public void setHealthPlan(String newHealth){this.health = newHealth;}
    public void settypDiabets(int typ){this.typDiabets = typ;}
    public void setTypDiet(String newtypDiet){this.typDiet = newtypDiet;}

    public int getKcal(){return this.kcal;}
    public int getWw(){return this.Ww;}
    public int getB(){return this.B;}
    public int getT(){return this.T;}
    public int getmeal(){return this.meal;}

    public String show(){
        return this.kcal +" - "+this.Ww+" - "+this.B+" - "+this.T+" - "+this.meal;
    }


    public int getBmail(){
        return (int)(this.B/6);
    }
    public int getTmail(){
        return (int)(this.T/6);
    }
    public int getWwmail(){
        return (int)(this.Ww/6);
    }

    public void addNewPlanBD(){
        db_dietPlan = new DB_DietPlan(MainActivity.getAppContext());
        boolean isAdd = db_dietPlan.insertNewDiet(this.kcal,this.Ww,this.B,this.T,6);
        if(isAdd == true){
            Toast.makeText(MainActivity.getAppContext(),"DOADNO : "+ show() ,Toast.LENGTH_SHORT).show();

        }else if(isAdd == false){
            Toast.makeText(MainActivity.getAppContext(),"NIE DODANO",Toast.LENGTH_SHORT).show();

        }


    }

    //// powielanie bez sensu póxniej do zmiany
    public boolean isHealt(){

        boolean healhtOrDiabets = true;

        if(this.health.equals("zdrowy")){
            healhtOrDiabets = true;

        }else if(this.health.equals("diabetyk")){
            healhtOrDiabets = false;

        }else{
            healhtOrDiabets = true;
        }

        return healhtOrDiabets;
    }


    // BTW in grams
    private void getProportionsBTWinMeal(){
        if(isHealt() ) {
            switch (this.typDiet) {/// to musze mieć w gramach nie w kcal
                case "carbo":
                    this.B = getMacroBTW(this.kcal, 0.20);
                    this.T = getMacroBTW(this.kcal, 0.15);
                    this.Ww = getMacroBTW(this.kcal, 0.65);
                    break;
                case "lowCarbo":
                    this.B = getMacroBTW(this.kcal, 0.35);
                    this.T = getMacroBTW(this.kcal, 0.15);
                    this.Ww = getMacroBTW(this.kcal, 0.50);
                    break;
                case "keto":
                    this.B = getMacroBTW(this.kcal, 0.20);
                    this.T = getMacroBTW(this.kcal, 0.70);
                    this.Ww = getMacroBTW(this.kcal, 0.10);
                    break;
            }

        }else if( !isHealt() ){//// typ 2 normalnei ?/// typ jeden innnaczej
            this.B = getMacroBTW(this.kcal, 0.30);
            this.T = getMacroBTW(this.kcal, 0.20);
            this.Ww = getMacroBTW(this.kcal, 0.50);
        }
    }


    private int getMacroBTW(int kcal, double divide){
        return (int)((kcal * divide)/4);
    }


// Save data in class with database
    private void setDietPlane(){
        try{
            SQLiteOpenHelper hh = new DB_DietPlan(MainActivity.getAppContext());
            SQLiteDatabase db = hh.getReadableDatabase();
            Cursor cur = db.query("PLAN", new String[]{"KCA","WW","B","T","MEAL"}, null, null, null, null, null);

            if(cur.moveToLast()){

                this.kcal = cur.getInt(0);
                this.B = cur.getInt(1);
                this.T = cur.getInt(2);
                this.Ww = cur.getInt(3);
                this.meal =cur.getInt(4);

            }
            cur.close();
            db.close();

        }catch (SQLiteException e){
            Toast.makeText(MainActivity.getAppContext(), "Błąd bazy dabych", Toast.LENGTH_SHORT).show();
        }



    }




}
