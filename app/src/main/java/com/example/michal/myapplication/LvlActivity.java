package com.example.michal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LvlActivity extends AppCompatActivity {
    private RadioGroup lvlRadioGrup;
    private int lvlActi = 2;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //final DietPlan dietPlan = new DietPlan();

        lvlRadioGrup = (RadioGroup)findViewById(R.id.lvlRadioGrup);
        lvlRadioGrup.setOnCheckedChangeListener(changeListenerRadioGrup);

        LvlActivity.context = getApplicationContext();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getUserCreatee().setLvlActi(lvlActi);

                //Chanege kcal in DietPlan class
                //dietPlan.setKcal(MainActivity.getUserCreatee().getaimKCAL());

                MainActivity.getUserCreatee().getaimKCAL();
                MainActivity.getUserCreatee().addNewUserDB();

                DietPlan dietPlan = new DietPlan();
                dietPlan = new DietPlan(MainActivity.getUserCreatee().getaimKCAL(),MainActivity.getUserCreatee().getHealth(),MainActivity.getUserCreatee().getTypDiabets(),MainActivity.getUserCreatee().getTypDiet());


                Intent intent = new Intent(LvlActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private RadioGroup.OnCheckedChangeListener changeListenerRadioGrup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.lowRadioButton:
                    lvlActi = 1;
                    break;
                case R.id.mediumRadioButton:
                    lvlActi = 2;
                    break;
                case R.id.bigRadioButton:
                    lvlActi = 3;
                    break;
            }
        }
    };


    public static Context getAppContext() {
        return LvlActivity.context;
    }

}
