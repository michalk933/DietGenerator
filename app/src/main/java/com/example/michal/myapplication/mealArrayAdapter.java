package com.example.michal.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by michal on 20.11.2016.
 */

public class mealArrayAdapter extends ArrayAdapter<DietPlan> {


    public mealArrayAdapter(Context context, List<DietPlan> lista) {
        super(context,-1, lista);
    }

    private static class ViewHolder{
        TextView numberMealTextView ;
        TextView KcalMealTextView ;
        TextView WWMealtextView ;
        TextView WwMealTextView ;
        TextView BMealTextView ;
        TextView TMealTextView ;



    }



    public View getView(int position, View converView, ViewGroup parent){
        DietPlan dietPlan = getItem(position);// to nie wiem co to jest

        ViewHolder viewHolder ;

        if(converView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            converView = inflater.inflate(R.layout.meal_list_viwe,parent,false);
            viewHolder.numberMealTextView =(TextView)converView.findViewById(R.id.numberMealTextView);
            viewHolder.KcalMealTextView =(TextView)converView.findViewById(R.id.KcalMealTextView);
            viewHolder.WWMealtextView =(TextView)converView.findViewById(R.id.WWMealtextView);
            viewHolder.WwMealTextView =(TextView)converView.findViewById(R.id.WwMealTextView);
            viewHolder.BMealTextView =(TextView)converView.findViewById(R.id.BMealTextView);
            viewHolder.TMealTextView =(TextView)converView.findViewById(R.id.TMealTextView);
        }else {
            viewHolder = (ViewHolder)converView.getTag();
        }

        Context context = getContext();
        viewHolder.numberMealTextView.setText( String.valueOf(dietPlan.getIncrementMeal()) );
        viewHolder.KcalMealTextView.setText("Kcal :" + String.valueOf(dietPlan.getKcalMeal()) );
        viewHolder.WWMealtextView.setText("Wymiennik :" + String.valueOf(dietPlan.getWymiennikMeal()) + " g");
        viewHolder.WwMealTextView.setText("Węglowodany :" + String.valueOf(dietPlan.getWwmail()) + " g");
        viewHolder.BMealTextView.setText("Białko :" + String.valueOf(dietPlan.getBmail()) + " g");
        viewHolder.TMealTextView.setText("Tłuszcze :" + String.valueOf(dietPlan.getTmail()) + " g");

        return converView;
    }



}
