package com.example.michal.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class HealthActivity extends AppCompatActivity {

    private RadioGroup healthRadioGrup,typDietRadioGrup,typDiabetsRadioGrup;
    private RadioButton typOneRadioButton, typTwoRadioButton,carboHRadioButton,carboLRadioButton,ketoRadioButton;
    private String health = "zdrowy";
    private String typDiet = "lowCarbo";
    private int typDiabets = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        healthRadioGrup = (RadioGroup)findViewById(R.id.healthRadioGrup);
        typDietRadioGrup = (RadioGroup)findViewById(R.id.typDietRadioGrup);
        typDiabetsRadioGrup = (RadioGroup)findViewById(R.id.typDiabetsRadioGrup);

        typOneRadioButton = (RadioButton)findViewById(R.id.typOneRadioButton);
        typTwoRadioButton = (RadioButton)findViewById(R.id.typTwoRadioButton);
        carboHRadioButton = (RadioButton)findViewById(R.id.carboHRadioButton);
        carboLRadioButton = (RadioButton)findViewById(R.id.carboLRadioButton);
        ketoRadioButton = (RadioButton)findViewById(R.id.ketoRadioButton);

        healthRadioGrup.setOnCheckedChangeListener(checkedlistenerHealt);
        typDietRadioGrup.setOnCheckedChangeListener(checkedlistenerTypDiet);
        typDiabetsRadioGrup.setOnCheckedChangeListener(checkedlistenerTypDiabets);

        changeTypDiet(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getUserCreatee().setHealth(health);
                MainActivity.getUserCreatee().setTypDiet(typDiet);
                MainActivity.getUserCreatee().setTypDiabets(typDiabets);

                Intent intent = new Intent(HealthActivity.this,PersonalActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private RadioGroup.OnCheckedChangeListener checkedlistenerHealt = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.healthManRadioButton:
                    health = "zdrowy";
                    changeDiabets(false);
                    changeTypDiet(true);
                    break;
                case R.id.diabetsRadioButton:
                    health = "diabetyk";
                    changeDiabets(true);
                    changeTypDiet(false);
                    break;
                case R.id.fatRadioButton:
                    health = "otyły";
                    changeTypDiet(true);
                    changeDiabets(false);
                    break;
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener checkedlistenerTypDiet = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.carboHRadioButton:
                    typDiet = "carbo";
                    break;
                case R.id.carboLRadioButton:
                    typDiet = "lowCarbo";
                    break;
                case R.id.ketoRadioButton:
                    typDiet = "keto";
                    break;
            }

        }
    };


    private RadioGroup.OnCheckedChangeListener checkedlistenerTypDiabets = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.typOneRadioButton:
                    typDiabets = 1;
                    break;
                case R.id.typTwoRadioButton:
                    typDiabets = 2;
                    break;
            }


        }
    };



    private void changeDiabets(boolean enable){
        typOneRadioButton.setEnabled(enable);
        typTwoRadioButton.setEnabled(enable);
    }

    private void changeTypDiet(boolean enable){
        carboHRadioButton.setEnabled(enable);
        carboLRadioButton.setEnabled(enable);
        ketoRadioButton.setEnabled(enable);
    }





}
