package com.example.michal.myapplication;

import android.renderscript.Double2;
import android.widget.Toast;

/**
 * Created by michal on 15.11.2016.
 */

public class UserCreate {

    private DB_User userDataBase;

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
    private int kcal;

    public int solve;
    public int b;
    public int t;
    public int ww;


    public UserCreate(){}


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
    public void setkcal(int newKcal){this.kcal = newKcal;}

    public String getNameuser(){
        return this.name;
    }
    public String getPass(){
        return this.pass;
    }
    public String getDateAdd(){return this.addDate; }
    public String getAim(){
        return this.aim;
    }
    public String getHealth(){
        return this.health;
    }
    public String getTypDiet(){
        return this.typDiet;
    }
    public int getTypDiabets(){
        return this.typDiabets;
    }
    public int getAge(){
        return this.age;
    }
    public int getHeight(){
        return this.height;
    }
    public int getWeight(){
        return this.weight;
    }
    public int getSex(){
        return this.sex;
    }
    public int getLvlActi(){return this.lvlActi;}
    public int getKcal(){return this.kcal;}





    public String showUser(){
        return this.name + " - " + this.pass + " - " + this.aim + " - " + this.health + " - " +
                this.typDiet + " - " + this.typDiabets + " - " + this.age + " - " + this.height +" - " + this.weight +
                " - " + this.sex + " - " + this.lvlActi + " - "+ this.kcal;
    }


    public void addNewUserDB(){
        userDataBase = new DB_User(LvlActivity.getAppContext());
        boolean isAdd = userDataBase.insertNewUser(this.name, this.pass, this.addDate, this.aim, this.health, this.typDiet,
                this.typDiabets, this.age, this.height, this.weight, this.sex, this.lvlActi, this.kcal);
        if(isAdd == true){
            Toast.makeText(LvlActivity.getAppContext(),"DOADNO do bazy USER" ,Toast.LENGTH_SHORT).show();
        }else if(isAdd == false){
            Toast.makeText(LvlActivity.getAppContext(),"NIE DODANO",Toast.LENGTH_SHORT).show();
        }
    }



    // KCAL wiht aim
    public int getaimKCAL(){
        int cmp = getCMP();

        if(this.aim.equals("redukcja")){
            cmp = (int)(cmp * 0.75);

        }else if(this.aim.equals("utrzymanie")){

        }else if(this.aim.equals("budowanie") && !isHealt() ){
            cmp = (int)(cmp * 1.25);
        }

        this.kcal = cmp;
        return cmp;
    }




    //KCAL of the day
    private int getCMP(){
        int cmp = 0;

        if( this.health.equals("zdrowy") || this.health.equals("otyły") ){//health or fat
            int bmr = getBMR();
            cmp = bmr + getNEAT() + getTEF(bmr) + getTEA(bmr);
        }else if(this.health.equals("diabetyk")){// Diabets
            cmp = this.weight * getBMIandActivity();
        }
        return cmp;
    }





    // if methoth return true, user dont have diabets
    // if methods return false, user have diabets
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


    private int getBMR(){
        int kcal = 0;

        if(this.sex == 0){//Woman
            //665.09 + [9.56 x M(kg)] + [1.85 x h(cm)] – [4.67 x W(lata)]
            kcal = (int)(665.09 + (9.56 * this.weight) + (1.85 * this.height) - (4.67 * this.age));
        }else{//Man
            //66.47 + [13.75 x M(kg)] + [5 x h(cm)] – [6.75 x W(lata)]
            kcal = (int)(66.47 +(13.75 * this.weight) + (5 * this.height) - (6.75 * this.age));
        }
        return kcal;
    }

    //Method check level activity user and return kcal
    private int getNEAT(){
        return (this.lvlActi == 1 || this.lvlActi == 2) ? 300 : 400;
    }

    private int getTEF(int bmr){
        return (int)(bmr * 0.1);
    }

    private int getTEA(int bmr){
        return (int)((this.lvlActi == 1 || this.lvlActi == 2) ? (bmr * 0.3) : (bmr * 0.5));
    }


    //BMI = Masa ciała (kg) / Wzrost (m)
    private int getBMI(){
        return (int)(this.weight / Math.pow(this.height,2));
    }

    private int getPointerBMI(){
        int plusKcalDiabets = 0;
        int bmi = getBMI();

        if( bmi < 18){
            plusKcalDiabets = 1;
        }else if( bmi >= 18 && bmi <= 25 ){
            plusKcalDiabets = 2;
        }else if( bmi > 15 ){
            plusKcalDiabets = 3;
        }

        return plusKcalDiabets;
    }

    private int getBMIandActivity(){
        int bmi = getPointerBMI();
        int activ = this.lvlActi;
        int result = 0;

        if( (bmi == activ) && ( bmi == 1 || bmi == 3) ){
            result = 35;
        }else if(bmi == 1 ){
            result = 40;
        }else if( bmi == 2 ){
            result = 35;
        }else if( bmi == 3 ){
            result = 30;
        }

        return result;
    }
//http://www.pracuj.pl/praca/programista-java-katowice,oferta,4945819?utm_source=rekomendacje&utm_medium=email&sendid=600c1020-bc67-414e-9d79-3a5831ce5ad2&send_date=2016-11-22&rfn=startDate&utm_campaign=prog_tags_no_aplly_6_mail2






}
