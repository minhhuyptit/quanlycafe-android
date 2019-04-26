package model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import xyz.khang.quanlythuexedulich.R;
import xyz.khang.quanlythuexedulich.activity_car_detail;
import xyz.khang.quanlythuexedulich.activity_list_car;

public class Car_adapter extends ArrayAdapter<Car> implements CompoundButton.OnCheckedChangeListener{
    Context context;
    int layoutResourceId;
    ArrayList<Car> cars = null;

    public Car_adapter(Context context, int resource, ArrayList<Car> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.cars = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CarHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CarHolder();
            holder.imgCar = row.findViewById(R.id.imgCar);
            holder.txtMaXe = row.findViewById(R.id.txtMaXe);
            holder.txtTenXe = row.findViewById(R.id.txtTenXe);
            holder.txtXuatXu = row.findViewById(R.id.txtXuatXu);
            holder.cbHasChoose = row.findViewById(R.id.cbHasChoose);
            holder.linearLayout = row.findViewById(R.id.lbackground);

            row.setTag(holder);
        } else {
            holder = (CarHolder) row.getTag();
        }

        Car item = cars.get(position);

        //thong tin nan vien
        holder.txtMaXe.setText("Mã xe: " + item.id);
        holder.txtTenXe.setText("Tên xe: " + item.name);
        holder.txtXuatXu.setText("Xuất xứ: " + item.origin);

        holder.cbHasChoose.setText(item.id);

        if (item.hasChoose){
            holder.cbHasChoose.setChecked(true);
            holder.cbHasChoose.setVisibility(View.VISIBLE);
            holder.linearLayout.setBackgroundColor(Color.YELLOW);
            //android:background="#FFC107"
        }
        else{
            holder.cbHasChoose.setChecked(false);
            holder.cbHasChoose.setVisibility(View.INVISIBLE);
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        }


        holder.cbHasChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chk= v.findViewById(R.id.cbHasChoose);
                Car t = activity_list_car.data.get(position);
                if(chk.isChecked()) {
                    t.setHasChoose(true);
                }
                else {
                    t.setHasChoose(false);
                    chk.setVisibility(View.INVISIBLE);
                    t.contract=null;
                }
                Toast.makeText(context, "Checkbox  clicked! " + position + " " + t.isHasChoose(), Toast.LENGTH_SHORT).show();
                activity_list_car.data.set(position, t);
            }
        });
        return row;

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Toast.makeText(context, "sdfdsfsf", Toast.LENGTH_SHORT).show();
    }

    static class CarHolder {
        ImageView imgCar;
        TextView txtMaXe;
        TextView txtTenXe;
        TextView txtXuatXu;
        CheckBox cbHasChoose;
        LinearLayout linearLayout;
    }
}
