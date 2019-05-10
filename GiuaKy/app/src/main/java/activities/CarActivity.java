package activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import adapters.RecyclerViewCarAdapter;
import classes.Car;
import xyz.khang.quanlythuexedulich.R;
import xyz.khang.quanlythuexedulich.databinding.ActivityCarBinding;

public class CarActivity extends Activity implements RecyclerViewCarAdapter.Callback {
    RecyclerViewCarAdapter adapter;
    ActivityCarBinding binding;
    List<Car> cars = new ArrayList<>();
    DatabaseReference rootCar = MainActivity.root.child("cars");
    String tenXe, xuatXu, maXe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car);

        //fakeData();
        initRecyclerView();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        rootCar.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                cars.add(dataSnapshot.getValue(Car.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.rcvCar;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerViewCarAdapter(this, cars);
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

        String type = "Thêm Siêu Xe";
        lblType.setText(type);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenXe = txtTenXe.getText().toString();
                xuatXu = txtXuatXu.getText().toString();
                maXe = txtMAXE.getText().toString();
                if(tenXe.equals("") || xuatXu.equals("") || maXe.equals("")){
                    Toast.makeText(CarActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (findCar(txtMAXE.getText().toString()) == null) {
                    Car car = new Car();
                    car.TENXE = tenXe;
                    car.XUATXU = xuatXu;
                    car.MAXE = maXe;
//                    cars.add(car);
                    rootCar.child(car.MAXE).setValue(car);
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
                            rootCar.child(MAXE).setValue(null);
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

        String type = "Sửa Siêu Xe";
        lblType.setText(type);
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
                rootCar.child(MAXE).setValue(findCar(MAXE));
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

    void createCarData(){
        for(Car c: cars) rootCar.child(c.MAXE).setValue(c);
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
