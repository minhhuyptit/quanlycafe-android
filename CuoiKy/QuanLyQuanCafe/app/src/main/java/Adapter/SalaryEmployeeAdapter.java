package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Classes.Salary;
import Classes.User;
import xyz.khang.quanlyquancafe.R;

public class SalaryEmployeeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Salary> salaryEmployeeList;

    public SalaryEmployeeAdapter(Context context, int layout, List<Salary> salaryEmployeeList) {
        this.context = context;
        this.layout = layout;
        this.salaryEmployeeList = salaryEmployeeList;
    }

    @Override
    public int getCount() {
        return salaryEmployeeList.size();
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
        TextView tvMonthYear, tvHour, tvSalary;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SalaryEmployeeAdapter.ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new SalaryEmployeeAdapter.ViewHolder();

            //Ánh xạ view
            holder.tvMonthYear = view.findViewById(R.id.tvMonthYear);
            holder.tvHour = view.findViewById(R.id.tvHour);
            holder.tvSalary = view.findViewById(R.id.tvSalary);
            view.setTag(holder);
        }else{
            holder = (SalaryEmployeeAdapter.ViewHolder) view.getTag();
        }

        //Gán giá trị
        Salary salaryEmployee = salaryEmployeeList.get(i);
        holder.tvMonthYear.setText(salaryEmployee.month + "/" + salaryEmployee.year);
        String[] time = salaryEmployee.hour.split("\\.");
        String hour = String.valueOf(Integer.parseInt(time[0]));
        String minute = String.valueOf(Math.round(Integer.parseInt(time[1])/10000.00*60));
        holder.tvHour.setText(hour + "h" + minute + "p");
        holder.tvSalary.setText(String.format("%,.2f", salaryEmployee.salary) + " VND");
        return view;
    }
}

