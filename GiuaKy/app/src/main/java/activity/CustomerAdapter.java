package activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xyz.khang.quanlythuexedulich.R;

public class CustomerAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Customer> customerList;

    public CustomerAdapter(Context context, int layout, List<Customer> customerList) {
        this.context = context;
        this.layout = layout;
        this.customerList = customerList;
    }

    @Override
    public int getCount() { //Trả về số lượng muốn trả về
        return customerList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvMaKH, tvTenKH, tvDiaChiKH;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder = new ViewHolder();

            //Ánh xạ view
            holder.tvMaKH = convertView.findViewById(R.id.tvMaKH);
            holder.tvTenKH = convertView.findViewById(R.id.tvTenKH);
            holder.tvDiaChiKH = convertView.findViewById(R.id.tvDiaChiKH);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //Gán giá trị
        Customer customer = customerList.get(position);
        holder.tvMaKH.setText(customer.getMaKH());
        holder.tvTenKH.setText(customer.getTenKH());
        holder.tvDiaChiKH.setText(customer.getDiachiKH());

        return convertView;
    }
}
