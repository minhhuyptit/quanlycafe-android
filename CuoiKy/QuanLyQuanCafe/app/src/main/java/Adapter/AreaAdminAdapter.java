package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Classes.Area;
import xyz.khang.quanlyquancafe.R;

public class AreaAdminAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Area> areaList;

    public AreaAdminAdapter(Context context, int layout, List<Area> areaList) {
        this.context = context;
        this.layout = layout;
        this.areaList = areaList;
    }

    @Override
    public int getCount() {
        return areaList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvIDArea, tvNameArea;
        ImageView imgArea;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AreaAdminAdapter.ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new AreaAdminAdapter.ViewHolder();

            //Ánh xạ view
            holder.tvIDArea = view.findViewById(R.id.tvIDArea);
            holder.tvNameArea = view.findViewById(R.id.tvNameArea);
            holder.imgArea = view.findViewById(R.id.imgArea);
            view.setTag(holder);
        }else{
            holder = (AreaAdminAdapter.ViewHolder) view.getTag();
        }

        //Gán giá trị
        Area area = areaList.get(i);
        holder.tvIDArea.setText(String.valueOf(area.id));
        holder.tvNameArea.setText(area.name);
        holder.imgArea.setImageResource(randomImage(i));
        return view;
    }

    private int randomImage(int i){
        ArrayList<Integer> randImg = new ArrayList<>();
        randImg.add(R.drawable.area_1);
        randImg.add(R.drawable.area_2);
        randImg.add(R.drawable.area_3);
        randImg.add(R.drawable.area_4);
        randImg.add(R.drawable.area_5);
        return randImg.get(i);
    }
}
