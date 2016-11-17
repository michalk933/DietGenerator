package com.example.michal.myapplication;

import android.renderscript.Double2;
import android.widget.Toast;

/**
 * Created by michal on 15.11.2016.
 */

public class UserCreate {

    DB_User userDataBase;

    private String name;
    private String pass;
    private String addDate;
    private String aim;
    private String health;
    private String typDiet;
    private int typDiabets;
    private int age;
    private int height;
    private int weight;
    private int sex;
    private int lvlActi;

    public UserCreate(){}

/*
    public String getNameUser(){
        return this.name;
    }
*/
    public void setNameuser(String name){
        this.name = name;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public void setDateAdd(String date){this.addDate = date; }
    public void setAim(String aim){
        this.aim = aim;
    }
    public void setHealth(String health){
        this.health = health;
    }
    public void setTypDiet(String typDiet){
        this.typDiet = typDiet;
    }
    public void setTypDiabets(int typDiabets){
        this.typDiabets = typDiabets;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setSex(int sex){
        this.sex = sex;
    }
    public void setLvlActi(int lvlActi){
        this.lvlActi = lvlActi;
    }


    public String showUser(){
        return this.name + " - " + this.pass + " - " + this.aim + " - " + this.health + " - " +
                this.typDiet + " - " + this.typDiabets + " - " + this.age + " - " + this.height +" - " + this.weight +
                " - " + this.sex + " - " + this.lvlActi ;
    }


    public void addNewUserDB(){
        userDataBase = new DB_User(LvlActivity.getAppContext());
        boolean isAdd = userDataBase.insertNewUser(this.name, this.pass, this.addDate, this.aim, this.health, this.typDiet,
                this.typDiabets, this.age, this.height, this.weight, this.sex, this.lvlActi);
        if(isAdd == true){
            Toast.makeText(LvlActivity.getAppContext(),"DOADNO : "+ showUser() ,Toast.LENGTH_SHORT).show();

        }else if(isAdd == false){
            Toast.makeText(LvlActivity.getAppContext(),"NIE DODANO",Toast.LENGTH_SHORT).show();


        }
    }

    // if methoth return true, user dont have diabets
    // if methods return false, user have diabets
    private boolean isHealt(){
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


    public int kcalDay() {
        double kcal = 0;
        int kcalOfDay;
        double bmi;

        if (isHealt()) {

            if (this.sex == 0) {
                kcal = (665.09 + ((9.56 * this.weight) * 1.1) + ((1.85 * this.height) * lvl()) + (4.67 * this.age)) + 300;
            }else if(this.sex == 1) {
                kcal = (66.47 + ((13.75 * this.weight) * 1.1) + ((5 * this.height) * lvl()) + (6.75 * this.age)) + 400;
            }

        }else if(!isHealt()){

            bmi = this.weight / (this.height * this.height);
            int kcalWihtBmi = bmiRange(bmi);
            kcal = weight * kcalWihtBmi;

        }

        Double parser = new Double(kcal);
        kcalOfDay = parser.intValue();

        return kcalOfDay;

    }

    public double lvl() {
        double solve = 1.3;
        if (this.lvlActi == 1) {
            solve = 1.3;
        } else if (this.lvlActi == 2) {
            solve = 1.3;
        } else if (this.lvlActi == 3) {
            solve = 1.5;
        }
        return solve;
    }

    public int bmiRange(double bmi) {
        Double d = new Double(bmi);
        int bmiRange = d.intValue();
        int kcalWihtBmi = 35;

        if (bmiRange < 18) {
            kcalWihtBmi = 40;
        } else if (bmiRange > 18 && bmiRange < 25) {
            kcalWihtBmi = 35;

        } else if (bmiRange > 25) {
            kcalWihtBmi = 30;
        }

        activ(kcalWihtBmi);
        return kcalWihtBmi;
    }

    public int activ(int kcalWihtBmi) {
        int kcalWithActivity = 35;
        switch (this.lvlActi) {
            case 1:
                kcalWithActivity = 30;
                break;
            case 2:
                kcalWithActivity = 35;
                break;
            case 3:
                kcalWithActivity = 34;
                break;
        }
        return kcalFinall(kcalWihtBmi, kcalWithActivity);
    }

    public int kcalFinall(int kcalWihtBmi, int kcalWithActivity) {
        int kclaFinall = 35;

        if (kcalWihtBmi == 30 && kcalWithActivity == 40) {
            kclaFinall = 35;

        } else if (kcalWihtBmi == 30 || kcalWithActivity == 30) {
            kclaFinall = 30;

        } else if (kcalWihtBmi == 40 || kcalWithActivity == 40) {
            kclaFinall = 40;
        } else {
            kclaFinall = 35;

        }
        return kclaFinall;
    }


}
