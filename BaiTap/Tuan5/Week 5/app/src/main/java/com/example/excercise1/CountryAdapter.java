package com.example.excercise1;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Country> countries = null;

    public CountryAdapter(Context context, int resource, ArrayList<Country> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutResourceId = resource;
        this.countries = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CountryHolder holder = null;
        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent,false);

            holder = new CountryHolder();
            holder.imgFlag = row.findViewById(R.id.imageView_flag);
            holder.txtCountryName = row.findViewById(R.id.textView_contryName);
            holder.txtPopulation = row.findViewById(R.id.textView_population);

            row.setTag(holder);
        } else {
            holder = (CountryHolder) row.getTag();
        }

        Country country = countries.get(position);;
        holder.imgFlag.setImageResource(country.getFlagName());
        holder.txtCountryName.setText(country.getCountryName());
        holder.txtPopulation.setText("Population: " + country.getPopulation());

        return row;
    }
    static class CountryHolder{
        ImageView imgFlag;
        TextView txtCountryName;
        TextView txtPopulation;
    }
}
