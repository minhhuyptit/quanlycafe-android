package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Classes.Category;
import xyz.khang.quanlyquancafe.R;

public class CategoryAdminAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Category> categoryList;

    public CategoryAdminAdapter(Context context, int layout, List<Category> categoryList) {
        this.context = context;
        this.layout = layout;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
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
        TextView tvID, tvName;
        ImageView imgCategory;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new ViewHolder();

            //Ánh xạ view
            holder.tvID = view.findViewById(R.id.tvID);
            holder.tvName = view.findViewById(R.id.tvName);
            holder.imgCategory = view.findViewById(R.id.imgCategory);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        //Gán giá trị
        Category category = categoryList.get(i);
        holder.tvID.setText(category.id);
        holder.tvName.setText(category.name);
        holder.imgCategory.setImageResource(randomImage());
        return view;
    }

    private int randomImage(){
        ArrayList<Integer> randImg = new ArrayList<>();
        randImg.add(R.drawable.drink_1);
        randImg.add(R.drawable.drink_2);
        randImg.add(R.drawable.drink_3);
        randImg.add(R.drawable.drink_4);
        randImg.add(R.drawable.drink_5);
        randImg.add(R.drawable.drink_6);
        randImg.add(R.drawable.drink_7);
        Collections.shuffle(randImg);
        return randImg.get(0);
    }
}
