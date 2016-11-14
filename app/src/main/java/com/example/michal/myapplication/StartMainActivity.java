package com.example.michal.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class StartMainActivity extends AppCompatActivity {

    EditText nameEditText, passEditText;
    String name, pass, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameEditText = (EditText)findViewById(R.id.nameEditText);
        passEditText = (EditText)findViewById(R.id.passEditText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEditText.getText().toString();
                pass = passEditText.getText().toString();
                calendar = getTadaAdduser();
                if(name.equals("") || pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Uzupełnij pola",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),calendar,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StartMainActivity.this, AimMainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private String getTadaAdduser(){

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        return String.valueOf(day +":"+month+":"+year);
    }


}
