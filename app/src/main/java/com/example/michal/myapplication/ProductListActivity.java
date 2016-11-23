package com.example.michal.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.DialogInterface;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    productArrayAdapter paa;
    private Cursor cursor;
    private ListView listView;
    private static final int TAKE_PHOTO_CODE = 0;


    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ProductListActivity.context = getApplicationContext();
        listView = (ListView)findViewById(R.id.productListView);

        try {
            SQLiteOpenHelper helper = new DB_Product(this);
            SQLiteDatabase db = helper.getReadableDatabase();

            cursor = db.query("PRODUCT", new String[]{"NAME", "KCAL_G", "IG", "WW_G", "B_G", "T_G","IMG"}, null, null, null, null, null);
            //paa = new productArrayAdapter(ProductListActivity.this);

            Cursor cu = db.rawQuery("SELECT  * FROM PRODUCT", null);
            productArrayAdapter p = new productArrayAdapter(this,cu,0);
            listView.setAdapter(p);





            cursor.close();
            db.close();

        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(), "Błąd bazy dabych produktów", Toast.LENGTH_SHORT).show();

        }




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ProductListActivity productListActivity = new ProductListActivity();
                AddProductFragment addProductFragment = new AddProductFragment();
                addProductFragment.show(getSupportFragmentManager(),"Dodaj produkt");
                takePhotoMethod();
                /*
                 ColorDialog colorDialog = new ColorDialog();
                colorDialog.show(getFragmentManager(), "color");
                 */

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public static Context getAppContext() {
        return ProductListActivity.context;
    }



    private void takePhotoMethod(){

        if(getAppContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setMessage("Pozwolenie na użycie camry");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO_CODE);


                    }
                });

                builder.create();
            }else{
                requestPermissions(new String[]{Manifest.permission.CAMERA},TAKE_PHOTO_CODE);
            }
        }else{
            ///odwołanie do metody

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case TAKE_PHOTO_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //wywołanie metody
                    return;
                }
        }


    }



}
