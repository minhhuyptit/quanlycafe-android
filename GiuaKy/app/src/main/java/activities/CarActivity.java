package activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.RecyclerViewCarAdapter;
import classes.Car;
import xyz.khang.quanlythuexedulich.R;
import xyz.khang.quanlythuexedulich.databinding.ActivityCarBinding;

public class CarActivity extends Activity implements RecyclerViewCarAdapter.Callback {

    ActivityCarBinding binding;
    List<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car);

        fakeData();

        initRecyclerView();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.rcvCar;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        RecyclerViewCarAdapter adapter = new RecyclerViewCarAdapter(this, cars);
        recyclerView.setAdapter(adapter);
    }

    private void add() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.edit_add_dialog);

        Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
        Button btnCancle = dialog.findViewById(R.id.btnCancle);
        TextView lblType = dialog.findViewById(R.id.lblType);
        final EditText txtMAXE = dialog.findViewById(R.id.txtMaXe);
        final EditText txtTenXe = dialog.findViewById(R.id.txtTenXe);
        final EditText txtXuatXu = dialog.findViewById(R.id.txtXuatXu);

        String type = "Thêm";
        lblType.setText(type);
        btnConfirm.setText(type);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findCar(txtMAXE.getText().toString()) == null) {
                    Car car = new Car();
                    car.TENXE = txtTenXe.getText().toString();
                    car.XUATXU = txtXuatXu.getText().toString();
                    car.MAXE = txtMAXE.getText().toString();
                    cars.add(car);
                    initRecyclerView();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Mã xe đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public void delete(final String MAXE) {
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Confirm delete");
            alertDialog.setMessage("Are you sure?");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            cars.remove(findCar(MAXE));
                            initRecyclerView();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cancle",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void edit(final String MAXE) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.edit_add_dialog);

        Button btnConfirm = dialog.findViewById(R.id.btnConfirm);
        Button btnCancle = dialog.findViewById(R.id.btnCancle);
        TextView lblType = dialog.findViewById(R.id.lblType);
        EditText txtMAXE = dialog.findViewById(R.id.txtMaXe);
        final EditText txtTenXe = dialog.findViewById(R.id.txtTenXe);
        final EditText txtXuatXu = dialog.findViewById(R.id.txtXuatXu);

        String type = "Sửa";
        lblType.setText(type);
        btnConfirm.setText(type);
        txtMAXE.setText(MAXE);
        txtTenXe.setText(findCar(MAXE).TENXE);
        txtXuatXu.setText(findCar(MAXE).XUATXU);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findCar(MAXE).XUATXU = txtXuatXu.getText().toString();
                findCar(MAXE).TENXE = txtTenXe.getText().toString();
                initRecyclerView();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void fakeData() {
        Car car;
        car = new Car();
        car.MAXE = "Dream1";
        car.TENXE = "Dream chiến 1";
        car.XUATXU = "Nhật Bổn";
        cars.add(car);

        car = new Car();
        car.MAXE = "Dream2";
        car.TENXE = "Dream chiến 2";
        car.XUATXU = "Nhật Bổn";
        cars.add(car);

        car = new Car();
        car.MAXE = "Wave1";
        car.TENXE = "Wave chiến 1";
        car.XUATXU = "Tàu Khựa";
        cars.add(car);

        car = new Car();
        car.MAXE = "Wave2";
        car.TENXE = "Wave chiến 1";
        car.XUATXU = "Nhật Tảo";
        cars.add(car);

        car = new Car();
        car.MAXE = "Lead1";
        car.TENXE = "Lead ninja 1";
        car.XUATXU = "Nhật Bổn";
        cars.add(car);

        car = new Car();
        car.MAXE = "Lead2";
        car.TENXE = "Lead shinobi 1";
        car.XUATXU = "Nhật Bổn";
        cars.add(car);

        car = new Car();
        car.MAXE = "Lead4";
        car.TENXE = "Lead shinobi 4";
        car.XUATXU = "Nhật Bổn";
        cars.add(car);

        car = new Car();
        car.MAXE = "Lead5";
        car.TENXE = "Lead shinobi 5";
        car.XUATXU = "Nhật Bổn";
        cars.add(car);

        car = new Car();
        car.MAXE = "Lead6";
        car.TENXE = "Lead shinobi 6";
        car.XUATXU = "Nhật Bổn";
        cars.add(car);
    }

    private Car findCar(String MAXE) {
        for (Car c : cars) {
            if (c.MAXE.equals(MAXE)) {
                return c;
            }
        }
        return null;
    }
}
