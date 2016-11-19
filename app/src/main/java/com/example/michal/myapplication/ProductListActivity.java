package com.example.michal.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.DialogInterface;

public class ProductListActivity extends AppCompatActivity {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ProductListActivity.context = getApplicationContext();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ProductListActivity productListActivity = new ProductListActivity();
                AddProductFragment addProductFragment = new AddProductFragment();
                addProductFragment.show(getSupportFragmentManager(),"Dodaj produkt");
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

}
