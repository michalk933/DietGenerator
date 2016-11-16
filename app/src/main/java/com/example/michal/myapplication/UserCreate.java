package com.example.michal.myapplication;

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

}
