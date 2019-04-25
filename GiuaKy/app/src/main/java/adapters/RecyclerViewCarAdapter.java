package adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import classes.Car;
import xyz.khang.quanlythuexedulich.R;
import xyz.khang.quanlythuexedulich.databinding.CarItemBinding;

public class RecyclerViewCarAdapter extends RecyclerView.Adapter {

    private List<Car> cars;
    private Callback callback;

    public RecyclerViewCarAdapter(Callback callback, List<Car> cars) {
        this.callback = callback;
        this.cars = cars;
    }

    public interface Callback {
        void delete(String MAXE);

        void edit(String MAXE);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        CarItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.car_item, parent, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        final CarItemBinding binding = DataBindingUtil.findBinding(holder.itemView);
        assert binding != null;
        binding.txtTenXe.setText(cars.get(position).TENXE);
        binding.txtMaXe.setText(cars.get(position).MAXE);
        binding.txtXuatXu.setText(cars.get(position).XUATXU);

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.delete(cars.get(holder.getAdapterPosition()).MAXE);
            }
        });

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.edit(cars.get(holder.getAdapterPosition()).MAXE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
