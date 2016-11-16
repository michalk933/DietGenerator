package com.example.michal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static UserCreate userCreate;
    private String nameWitheDB;
    private String passWithDB;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String nameSharedPreferences;
    private String passSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userCreate = new UserCreate();

        sharedPreferences = getSharedPreferences("com.example.michal.myapplication", Context.MODE_PRIVATE);
        nameSharedPreferences = sharedPreferences.getString("userName","");
        passSharedPreferences = sharedPreferences.getString("userPass","");
        Toast.makeText(getApplicationContext(), nameSharedPreferences + " name in MainActivity" + passSharedPreferences + "to haslo", Toast.LENGTH_SHORT).show();


        try{
            SQLiteOpenHelper hh = new DB_User(this);
            SQLiteDatabase db = hh.getReadableDatabase();
            Cursor cur = db.query("USER", new String[]{"NAME", "PASS"}, "AIM_DIET = ?", new String[]{"Redukcja"}, null, null, null);

            if(cur.moveToFirst()){
                nameWitheDB = cur.getString(0);
                passWithDB = cur.getString(1);
            }

            cur.close();
            db.close();


        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(), "Błąd bazy dabych", Toast.LENGTH_SHORT).show();

        }





        if(nameSharedPreferences.equals(nameWitheDB) && passSharedPreferences.equals(passWithDB) ) {
            Toast.makeText(getApplicationContext(),nameWitheDB + "  =  "+ nameSharedPreferences + " / " +passWithDB + "  =  "+ passSharedPreferences + "nie odpali bo już jest to w sys",Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, StartMainActivity.class);
            //startActivity(intent);
        }else if(!nameSharedPreferences.equals(nameWitheDB) && !passSharedPreferences.equals(passWithDB)) {
            Toast.makeText(getApplicationContext(),nameWitheDB + "  !=  "+ nameSharedPreferences + " i halo :" + passWithDB + "  !=  "+ passSharedPreferences,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, StartMainActivity.class);
            startActivity(intent);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static UserCreate getUserCreatee(){
        return userCreate;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
