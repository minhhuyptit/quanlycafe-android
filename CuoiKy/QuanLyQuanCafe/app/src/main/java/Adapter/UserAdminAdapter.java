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

import Classes.User;
import xyz.khang.quanlyquancafe.R;

public class UserAdminAdapter  extends BaseAdapter {
    private Context context;
    private int layout;
    private List<User> userList;

    public UserAdminAdapter(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
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
        TextView tvID, tvFullname, tvUsername;
        ImageView imgUser;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserAdminAdapter.ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new UserAdminAdapter.ViewHolder();

            //Ánh xạ view
            holder.tvID = view.findViewById(R.id.tvID);
            holder.tvUsername = view.findViewById(R.id.tvUsername);
            holder.tvFullname = view.findViewById(R.id.tvFullname);
            holder.imgUser = view.findViewById(R.id.imgUser);
            view.setTag(holder);
        }else{
            holder = (UserAdminAdapter.ViewHolder) view.getTag();
        }

        //Gán giá trị
        User user = userList.get(i);
        holder.tvID.setText(String.valueOf(user.id));
        holder.tvUsername.setText(user.username);
        holder.tvFullname.setText(user.fullname);
        holder.imgUser.setImageResource(randomImage());
        return view;
    }

    private int randomImage(){
        ArrayList<Integer> randImg = new ArrayList<>();
        randImg.add(R.drawable.user_1);
        randImg.add(R.drawable.user_2);
        randImg.add(R.drawable.user_3);
        randImg.add(R.drawable.user_4);
        randImg.add(R.drawable.user_5);
        randImg.add(R.drawable.user_6);
        randImg.add(R.drawable.user_7);
        randImg.add(R.drawable.user_8);
        Collections.shuffle(randImg);
        return randImg.get(0);
    }
}
