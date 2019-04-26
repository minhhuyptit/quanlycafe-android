package com.example.exemple;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    Context context;
    int layoutResourceId;
    ArrayList<Employee> employees = null;

    public EmployeeAdapter(Context context, int resource, ArrayList<Employee> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.employees = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EmployeeHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EmployeeHolder();
            holder.imgItem = row.findViewById(R.id.imgitem);
            holder.txtItem = row.findViewById(R.id.txtitem);

            row.setTag(holder);
        }
        else{
            holder = (EmployeeHolder) row.getTag();
        }

        Employee item = employees.get(position);

        //thong tin nan vien
        holder.txtItem.setText(item.toString());
        //icon gioi tinh
        if(item.isGender())
            holder.imgItem.setImageResource(R.drawable.ic_launcher_background);
        else//nếu là Nam thì lấy hình con trai
            holder.imgItem.setImageResource(R.drawable.ic_launcher_foreground);

        return row;
    }

    static class EmployeeHolder{
        ImageView imgItem;
        TextView txtItem;
    }
}

