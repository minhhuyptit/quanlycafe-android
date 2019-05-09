package model;

import android.app.Activity;
import android.content.Context;
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
import xyz.khang.quanlythuexedulich.activity_list_car;

public class Contract_adapter extends ArrayAdapter<ContractModel>{
    Context context;
    int layoutResourceId;
    ArrayList<ContractModel> contracts = null;

    public Contract_adapter(Context context, int resource, ArrayList<ContractModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.contracts = objects;
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
            holder.txtMaHD = row.findViewById(R.id.txtMaHD);
            holder.txtMaKH = row.findViewById(R.id.txtMaKH);
            holder.txtNgayHD = row.findViewById(R.id.txtNgayHD);

            row.setTag(holder);
        } else {
            holder = (CarHolder) row.getTag();
        }

        ContractModel item = contracts.get(position);

        //thong tin
        holder.txtMaHD.setText("Mã HĐ: " + item.MaHD);
        holder.txtMaKH.setText("Mã KH: " + item.MaKH);
        holder.txtNgayHD.setText("Ngày HĐ: " + item.NgayHD);

        return row;
    }

    static class CarHolder {
        ImageView imgCar;
        TextView txtMaHD;
        TextView txtMaKH;
        TextView txtNgayHD;
    }
}
