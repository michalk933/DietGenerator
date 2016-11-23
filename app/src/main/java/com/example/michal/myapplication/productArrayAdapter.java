package com.example.michal.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by michal on 20.11.2016.
 */

public class productArrayAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;


    public productArrayAdapter(Context context, Cursor c,int flags) {
        super(context, c,flags);
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.from(context).inflate(R.layout.product_list,parent,false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameListProductTextView = (TextView) view.findViewById(R.id.nameListProductTextView);
        TextView kcalListProductTextView = (TextView) view.findViewById(R.id.kcalListProductTextView);
        TextView igListProductTextView = (TextView) view.findViewById(R.id.igListProductTextView);
        TextView WWListProductTextView = (TextView) view.findViewById(R.id.WWListProductTextView);
        TextView BListProductTextView = (TextView) view.findViewById(R.id.BListProductTextView);
        TextView TListProductTextView = (TextView) view.findViewById(R.id.TListProductTextView);
        ImageView imageViewProduct = (ImageView) view.findViewById(R.id.imageViewProduct);


        Bitmap bitmap = getImage(cursor.getBlob(cursor.getColumnIndexOrThrow("IMG")));

        imageViewProduct.setImageBitmap(bitmap);
        nameListProductTextView.setText("Nazwa : " + cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
        kcalListProductTextView.setText("Kcal /100g : " + String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("KCAL_G"))));
        igListProductTextView.setText("IG : " + String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("IG"))));
        WWListProductTextView.setText("Węglowodanów /100g : " + String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("WW_G"))));
        BListProductTextView.setText("Białka /100g : " + String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("B_G"))));
        TListProductTextView.setText("Tłuszczy /100g : " + String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("T_G"))));


    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


}
