package com.example.michal.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by michal on 19.11.2016.
 */

public class AddProductFragment extends DialogFragment {

    private EditText nameProductEditText,producentEditText,typProductEditText,kcalProductEditText,IGEditText,
            WWProductEditText,BProductEditText,TProductEditText,banProductEditText;

    private DB_Product product;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View addView = getActivity().getLayoutInflater().inflate(R.layout.add_product,null);
        builder.setTitle("Dodaj produkt");
        builder.setView(addView);

        nameProductEditText = (EditText)addView.findViewById(R.id.nameProductEditText);
        producentEditText = (EditText)addView.findViewById(R.id.producentEditText);
        typProductEditText = (EditText)addView.findViewById(R.id.typProductEditText);
        kcalProductEditText = (EditText)addView.findViewById(R.id.kcalProductEditText);
        IGEditText = (EditText)addView.findViewById(R.id.IGEditText);
        WWProductEditText = (EditText)addView.findViewById(R.id.WWProductEditText);
        BProductEditText = (EditText)addView.findViewById(R.id.BProductEditText);
        TProductEditText = (EditText)addView.findViewById(R.id.TProductEditText);
        banProductEditText = (EditText)addView.findViewById(R.id.banProductEditText);




        builder.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if((nameProductEditText.equals(""))  || (typProductEditText.equals("")) || (kcalProductEditText.equals("")) ||
                        (IGEditText.equals("")) || (WWProductEditText.equals("")) || (BProductEditText.equals("")) || (TProductEditText.equals("")) || (banProductEditText.equals(""))){
                    Toast.makeText(getContext(),"Uzupełnij pola",Toast.LENGTH_LONG).show();


                    //insertNewProduct(String name,String producent, String type,int kcal_g, int ig, int ww_g, int b_g,int t_g,String ban,String date)

                }else {
                    String name = nameProductEditText.getText().toString();
                    String producent = producentEditText.getText().toString();
                    String typ = typProductEditText.getText().toString();
                    int kcal =Integer.parseInt(kcalProductEditText.getText().toString());
                    int ig  = Integer.parseInt(IGEditText.getText().toString());
                    int ww = Integer.parseInt(WWProductEditText.getText().toString());
                    int b = Integer.parseInt(BProductEditText.getText().toString());
                    int t = Integer.parseInt(TProductEditText.getText().toString());
                    String ban = banProductEditText.getText().toString();
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


}
