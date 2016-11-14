package com.example.michal.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

public class AimMainActivity extends AppCompatActivity {
    private RadioGroup aimRadioGrup;
    private String aim = "utrzymanie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aim_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aimRadioGrup = (RadioGroup)findViewById(R.id.aimRadioGrup);
        aimRadioGrup.setOnCheckedChangeListener(checkedlistener);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AimMainActivity.this,HealthActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private RadioGroup.OnCheckedChangeListener checkedlistener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId){
                case R.id.reductionRadioButton:
                    aim = "redukcja";
                    break;
                case R.id.keepRadioButton:
                    aim = "utrzymanie";
                    break;
                case R.id.buildRadioButton:
                    aim = "budowanie";
                    break;
            }
        }
    };




}
