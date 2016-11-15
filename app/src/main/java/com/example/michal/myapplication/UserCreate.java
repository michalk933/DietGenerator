package com.example.michal.myapplication;

/**
 * Created by michal on 15.11.2016.
 */

public class UserCreate {

    private String name = "franek";
    private String pass;
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


    public String getNameUser(){
        return this.name;
    }

    public void setNameuser(String name){
        this.name = name;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
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
    public void lvlActi(int lvlActi){
        this.lvlActi = lvlActi;
    }




}
