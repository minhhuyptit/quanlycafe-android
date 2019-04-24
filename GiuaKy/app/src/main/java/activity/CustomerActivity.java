package activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.khang.quanlythuexedulich.R;

public class CustomerActivity extends AppCompatActivity {

    SearchView sv_khachhang;
    ListView lv_khachhang;

    ArrayList<Customer> arrayKH;
    CustomerAdapter adapter;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        mapping();

        adapter = new CustomerAdapter(this, R.layout.per_line_customer, arrayKH);
        lv_khachhang.setAdapter(adapter);

        lv_khachhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CustomerActivity.this, "Click " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lv_khachhang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final CharSequence[] items = {"Edit", "Delete" };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Select Options");
                builder.setIcon(R.drawable.options);
                position = i;
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Toast.makeText(CustomerActivity.this, "Edit KH " + position, Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(CustomerActivity.this, "Delete KH " + position, Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });



        sv_khachhang.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    private void mapping(){
        sv_khachhang = (SearchView) findViewById(R.id.sv_khachhang);
        lv_khachhang = (ListView) findViewById(R.id.lv_khachhang);
        arrayKH = new ArrayList<>();
        Customer customer_1 = new Customer("KH01", "Nguyễn Tấn Luông", "Bến Tre");
        arrayKH.add(customer_1);
        Customer customer_2 = new Customer("KH02", "Nguyễn Hà Minh Huy", "Long An");
        arrayKH.add(customer_2);
        Customer customer_3 = new Customer("KH03", "Dương Đình Hạnh", "Đak Lak");
        arrayKH.add(customer_3);
        Customer customer_4 = new Customer("KH04", "Nguyễn Hữu Thắng", "Lâm Đồng");
        arrayKH.add(customer_4);
        Customer customer_5 = new Customer("KH04", "Đặng Hoàng Khang", "TP.Hồ Chí Minh");
        arrayKH.add(customer_5);
        Customer customer_6 = new Customer("KH04", "Trần Hữu Thế", "Nghệ An");
        arrayKH.add(customer_6);
    }
}
