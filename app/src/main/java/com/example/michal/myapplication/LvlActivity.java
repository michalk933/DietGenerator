package com.example.michal.myapplication;

import android.content.Context;
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
    private int lvlActi = 1;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvlRadioGrup = (RadioGroup)findViewById(R.id.lvlRadioGrup);
        lvlRadioGrup.setOnCheckedChangeListener(changeListenerRadioGrup);

        LvlActivity.context = getApplicationContext();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getUserCreatee().setLvlActi(lvlActi);
                MainActivity.getUserCreatee().addNewUserDB();
                Toast.makeText(getApplicationContext(),MainActivity.getUserCreatee().showUser(),Toast.LENGTH_SHORT).show();


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private RadioGroup.OnCheckedChangeListener changeListenerRadioGrup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.lowRadioButton:
                    lvlActi = 0;
                    break;
                case R.id.mediumRadioButton:
                    lvlActi = 1;
                    break;
                case R.id.bigRadioButton:
                    lvlActi = 2;
                    break;
            }
        }
    };


    public static Context getAppContext() {
        return LvlActivity.context;
    }

}
