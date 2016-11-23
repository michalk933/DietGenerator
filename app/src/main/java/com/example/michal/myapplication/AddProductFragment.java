package com.example.michal.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by michal on 19.11.2016.
 */

public class AddProductFragment extends DialogFragment {

    private EditText nameProductEditText,producentEditText,kcalProductEditText,IGEditText,
            WWProductEditText,BProductEditText,TProductEditText,banProductEditText;

    private DB_Product product;
    private String[] elementy = {"Węglowodanowy", "Białkowy", "Tłuszczowy"};
    private String typProduct = "Węglowodanowy";
    private String banProduct = "brak";

    private Button takePhotoButton;
    private String name;
    private int kcal;


    private Bitmap bitmap;
    private ImageView imageView;
    private static final int REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST = 1888;



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View addView = getActivity().getLayoutInflater().inflate(R.layout.add_product,null);
        builder.setTitle("Dodaj produkt");
        builder.setView(addView);

        nameProductEditText = (EditText)addView.findViewById(R.id.nameProductEditText);
        producentEditText = (EditText)addView.findViewById(R.id.producentEditText);
        kcalProductEditText = (EditText)addView.findViewById(R.id.kcalProductEditText);
        IGEditText = (EditText)addView.findViewById(R.id.IGEditText);
        WWProductEditText = (EditText)addView.findViewById(R.id.WWProductEditText);
        BProductEditText = (EditText)addView.findViewById(R.id.BProductEditText);
        TProductEditText = (EditText)addView.findViewById(R.id.TProductEditText);
        banProductEditText = (EditText)addView.findViewById(R.id.banProductEditText);

        takePhotoButton = (Button)addView.findViewById(R.id.takePhoto);
        getTadaAdduser();
        imageView = (ImageView)addView.findViewById(R.id.imageViewAdd);

        takePhotoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });




        Spinner spinner = (Spinner)addView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,elementy );
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  switch (position){
                                                      case 0:
                                                          typProduct = "Węglowodanowy";
                                                          break;
                                                      case 1:
                                                          typProduct = "Białkowy";
                                                          break;

                                                      case 2:
                                                          typProduct = "Tłuszczowy";
                                                          break;
                                                  }

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }
                                          });



        builder.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if((nameProductEditText.equals(""))   || (kcalProductEditText.equals("")) || (IGEditText.equals("")) ||
                        (WWProductEditText.equals("")) || (BProductEditText.equals("")) || (TProductEditText.equals("")) || (banProductEditText.equals(""))){
                    Toast.makeText(getContext(),"Uzupełnij pola",Toast.LENGTH_LONG).show();


                    //insetNewProduct(String name,String producent, String type,int kcal_g, int ig, int ww_g, int b_g,int t_g,String ban,String date)

                }else {
                    name = nameProductEditText.getText().toString();
                    String producent = producentEditText.getText().toString();
                    String typ = typProduct;
                    kcal =Integer.parseInt(kcalProductEditText.getText().toString());
                    int ig  = Integer.parseInt(IGEditText.getText().toString());
                    int ww = Integer.parseInt(WWProductEditText.getText().toString());
                    int b = Integer.parseInt(BProductEditText.getText().toString());
                    int t = Integer.parseInt(TProductEditText.getText().toString());
                    banProduct = banProductEditText.getText().toString();
                    String ban = banProduct;
                    String date = getTadaAdduser();


                    product = new DB_Product(getContext());
                    boolean isAdd = product.insertNewProduct(name,producent,typ,kcal,ig,ww,b,t,ban,date);


                    if(isAdd){
                        Toast.makeText(getContext(),"Dodano produkt",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getContext(),"Nie dodano",Toast.LENGTH_LONG).show();
                    }


                }
            }
        });

        return builder.create();
    }



    private String getTadaAdduser(){

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        return String.valueOf(day +":"+month+":"+year);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }




}
