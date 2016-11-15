package com.example.michal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userCreate = new UserCreate();

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userCreate.getNameUser());
        editor.apply();



        try{
            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
            SQLiteOpenHelper hh = new DB_User(this);
            Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
            SQLiteDatabase db = hh.getReadableDatabase();
            Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
            Cursor cur = db.query("USER", new String[]{"NAME", "PASS"}, "AIM_DIET = ?", new String[]{"Redukcja"}, null, null, null);
            //getHaslazDB(cur);

            Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_SHORT).show();
            if(cur.moveToFirst()){
                String n = cur.getString(0);
                String p = cur.getString(1);

                Toast.makeText(getApplicationContext(),n + " / "+p,Toast.LENGTH_SHORT).show();

            }

            cur.close();
            db.close();


        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(), "Błąd bazy dabych", Toast.LENGTH_SHORT).show();

        }





        if(sharedPreferences.getString("userName","franek").equals("franek") ) {
            Intent intent = new Intent(this, StartMainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"nie wiem",Toast.LENGTH_SHORT).show();
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

    /*
     public void getHaslazDB(Cursor cur){
        int ktoreHaslo=0;
        cur.moveToFirst();
        String[] names = new String[cur.getCount()];
        while(!cur.isAfterLast()) {
            names[ktoreHaslo] = cur.getString(cur.getColumnIndex("TXT"));
            cur.moveToNext();
            ktoreHaslo++;
        }
        losowanieHasla(names);

    }
     */




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
