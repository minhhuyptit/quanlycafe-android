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

public class MonHocAdapter extends ArrayAdapter<MonHoc> {
    Context context;
    int layoutResourceId;
    ArrayList<MonHoc> monHocs = null;

    public MonHocAdapter(Context context, int resource, ArrayList<MonHoc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.monHocs = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EmployeeHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

//            TextView txtMaMH;
//            TextView txtTenMH;
//            TextView txtSoTiet;


            holder = new EmployeeHolder();
            holder.imgItem = row.findViewById(R.id.imgitem);
            holder.txtMaMH = row.findViewById(R.id.txtMaMH);
            holder.txtTenMH = row.findViewById(R.id.txtTenMH);
            holder.txtSoTiet = row.findViewById(R.id.txtSoTiet);

            row.setTag(holder);
        }
        else{
            holder = (EmployeeHolder) row.getTag();
        }

        MonHoc item = monHocs.get(position);

        //thong tin nan vien
        holder.txtMaMH.setText(item.getId());
        holder.txtTenMH.setText(item.getName());
        holder.txtSoTiet.setText(item.getSoTiet());
        holder.imgItem.setImageResource(R.drawable.ic_launcher_foreground);

        return row;
    }

    static class EmployeeHolder{
        ImageView imgItem;
        TextView txtMaMH;
        TextView txtTenMH;
        TextView txtSoTiet;
    }
}

