package com.example.michal.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class PersonalActivity extends AppCompatActivity {

    private TextView ageTextView,heightTextView,weightTextView;
    private SeekBar ageSeekBar,heightSeekBar,weightSeekBar;
    private RadioGroup sexRadioGrup;

    private int age = 20;
    private int height = 175;
    private int weight = 75;
    private int sex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ageTextView = (TextView)findViewById(R.id.ageTextView);
        heightTextView = (TextView)findViewById(R.id.heightTextView);
        weightTextView = (TextView)findViewById(R.id.weightTextView);

        ageSeekBar = (SeekBar)findViewById(R.id.ageSeekBar);
        heightSeekBar = (SeekBar)findViewById(R.id.heightSeekBar);
        weightSeekBar = (SeekBar)findViewById(R.id.weightSeekBar);


        ageSeekBar.setOnSeekBarChangeListener(changeListenerSeekBar);
        heightSeekBar.setOnSeekBarChangeListener(changeListenerSeekBar);
        weightSeekBar.setOnSeekBarChangeListener(changeListenerSeekBar);

        ageSeekBar.setProgress(20);
        heightSeekBar.setProgress(175);
        weightSeekBar.setProgress(75);

        sexRadioGrup = (RadioGroup)findViewById(R.id.sexRadioGrup);
        sexRadioGrup.setOnCheckedChangeListener(changeListenerRadioGrup);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getUserCreatee().setAge(age);
                MainActivity.getUserCreatee().setHeight(height);
                MainActivity.getUserCreatee().setWeight(weight);
                MainActivity.getUserCreatee().setSex(sex);

                Intent intent = new Intent(PersonalActivity.this,LvlActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private SeekBar.OnSeekBarChangeListener changeListenerSeekBar = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()){
                case R.id.ageSeekBar:
                    if(fromUser) {
                        age = ageSeekBar.getProgress();
                        ageTextView.setText("Wiek : "+age+" lat");
                    }
                    break;
                case R.id.heightSeekBar:
                    if(fromUser) {
                        height = heightSeekBar.getProgress();
                        heightTextView.setText("Wzrost : "+height+" cm");
                    }
                    break;
                case R.id.weightSeekBar:
                    if(fromUser) {
                        weight = weightSeekBar.getProgress();
                        weightTextView.setText("Waga : "+weight+" kg");
                    }
                    break;

            }



        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private RadioGroup.OnCheckedChangeListener changeListenerRadioGrup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.manRadioButton:
                    sex = 1;
                    break;
                case R.id.womanRadioButton:
                    sex = 0;
                    break;
            }

        }
    };



}
