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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static UserCreate userCreate;
    private DietPlan dietPlan;
    private String nameWitheDB;
    private String passWithDB;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String nameSharedPreferences;
    private String passSharedPreferences;
    private int kcalWithDB;
    private String healthWithDB;
    private String typDietWithDB;
    private int typDiabetsWithDB;

    private static Context context;

    private TextView KcalTextView,WwTextView,BtextView,TtextView,WWtextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DietPlan
        MainActivity.context = getApplicationContext();


        //Creta class with data user
        userCreate = new UserCreate();

        // get value with sharedPref
        sharedPreferences = getSharedPreferences("com.example.michal.myapplication", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        nameSharedPreferences = sharedPreferences.getString("userName","");
        passSharedPreferences = sharedPreferences.getString("userPass","");

        //Inicialize Data base
        startDataBase();

        // User has got account
        if( (nameSharedPreferences.equals(nameWitheDB) && passSharedPreferences.equals(passWithDB)) || ( !nameSharedPreferences.equals("") && !passSharedPreferences.equals("") )) {
            dietPlan = new DietPlan();
            changeView(dietPlan.getKcal(), dietPlan.getB(), dietPlan.getT(), dietPlan.getWw());


            // User hasn't got account
        }else if( !nameSharedPreferences.equals(nameWitheDB) && !passSharedPreferences.equals(passWithDB) ) {
            Toast.makeText(getApplicationContext(),nameWitheDB + "  !=  "+ nameSharedPreferences + " i halo :" + passWithDB + "  !=  "+ passSharedPreferences,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, StartMainActivity.class);
            startActivity(intent);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = sharedPreferences.edit();
                editor.putString("userName", "");
                editor.putString("userPass", "");
                editor.commit();
            }
        });
    }

    public static UserCreate getUserCreatee(){
        return userCreate;
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    private void startDataBase(){

        try{
            SQLiteOpenHelper hh = new DB_User(this);
            SQLiteDatabase db = hh.getReadableDatabase();
            Cursor cur = db.query("USER", new String[]{"NAME", "PASS","KCAL","HEALTH","TYP_DIET","TYP_DIABETS"}, "NAME = ?", new String[]{nameSharedPreferences}, null, null, null);

            if(cur.moveToFirst()){
                nameWitheDB = cur.getString(0);
                passWithDB = cur.getString(1);
                kcalWithDB = cur.getInt(2);
                healthWithDB = cur.getString(3);
                typDietWithDB = cur.getString(4);
                typDiabetsWithDB = cur.getInt(5);
            }
            cur.close();
            db.close();

        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(), "Błąd bazy dabych", Toast.LENGTH_SHORT).show();
        }
    }


    private void changeView(int kcal, int b, int t, int ww){
        KcalTextView = (TextView)findViewById(R.id.KcalTextView);
        WwTextView = (TextView)findViewById(R.id.WwTextView);
        BtextView = (TextView)findViewById(R.id.BtextView);
        TtextView = (TextView)findViewById(R.id.TtextView);
        WWtextView = (TextView)findViewById(R.id.WWtextView);


        KcalTextView.setText("Kcal : "+String.valueOf(kcal));
        WwTextView.setText("Węglowodany : "+String.valueOf(ww));
        BtextView.setText("Białko : "+String.valueOf(b));
        TtextView.setText("Tłuszcze : "+String.valueOf(t));
        //WWtextView.setText("Wymiennik węglowodanowy : "+String.valueOf(kcal);

        
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.list_product) {
            Intent intent = new Intent(this,ProductListActivity.class);
            startActivity(intent);

            return true;
        }






        return super.onOptionsItemSelected(item);
    }
}
