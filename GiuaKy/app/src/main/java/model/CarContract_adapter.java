package model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import xyz.khang.quanlythuexedulich.R;

public class CarContract_adapter extends ArrayAdapter<CarContract> {
    Context context;
    int layoutResourceId;
    ArrayList<CarContract> cars = null;

    public CarContract_adapter(Context context, int resource, ArrayList<CarContract> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.cars = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CarHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CarHolder();
            holder.imgCar = row.findViewById(R.id.imgCar);
            holder.txtMaXe = row.findViewById(R.id.txtMaXe);
            holder.txtSoNgay = row.findViewById(R.id.txtSoNgay);
            holder.txtGiaThue = row.findViewById(R.id.txtGiaThue);

            row.setTag(holder);
        } else {
            holder = (CarHolder) row.getTag();
        }

        CarContract item = cars.get(position);

        //thong tin nan vien
        holder.txtMaXe.setText(item.id);
        holder.txtSoNgay.setText(item.sumDay);
        holder.txtGiaThue.setText(item.price);

        return row;

    }

    static class CarHolder {
        ImageView imgCar;
        TextView txtMaXe;
        TextView txtSoNgay;
        TextView txtGiaThue;
    }
}
