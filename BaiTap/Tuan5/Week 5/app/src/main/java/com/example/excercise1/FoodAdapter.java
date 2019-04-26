package com.example.excercise1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends ArrayAdapter<Food> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Food> foods = null;


    public FoodAdapter(Context context, int resource, ArrayList<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.foods = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FoodHolder holder = null;
        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent,false);

            holder = new FoodHolder();
            holder.icon = row.findViewById(R.id.icon);
            holder.txtTitle = row.findViewById(R.id.textView_title);
            holder.txtDesc = row.findViewById(R.id.textView_desc);

            row.setTag(holder);
        } else {
            holder = (FoodHolder) row.getTag();
        }

        Food food = foods.get(position);;
        holder.icon.setImageResource(food.getImageId());
        holder.txtTitle.setText(food.getTitle());
        holder.txtDesc.setText(food.getDesc());

        return row;
    }
    static class FoodHolder{
        ImageView icon;
        TextView txtTitle;
        TextView txtDesc;
    }
}
