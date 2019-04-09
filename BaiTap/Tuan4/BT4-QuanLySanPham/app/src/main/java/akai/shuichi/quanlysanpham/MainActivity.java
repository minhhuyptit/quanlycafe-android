package akai.shuichi.quanlysanpham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerDM;
    EditText edtMaSP, edtTenSP;
    Button btnNhapSP;
    ListView listSP;
    ArrayList<String> arraySP, arrayDM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        arraySP = new ArrayList<>();
        arraySP.add("SP1 - Samsung - Galaxy S7");
        arraySP.add("SP2 - Samsung - Note 8");
        arraySP.add("SP3 - Nokia - Lumia 730");
        arraySP.add("SP4 - Apple - Iphone X");
        arraySP.add("SP5 - Sony - Xperia ZX");
        arraySP.add("SP6 - Asus - Zend 2");

        arrayDM = new ArrayList<>();
        arrayDM.add("Samsung");
        arrayDM.add("Apple");
        arrayDM.add("Nokia");
        arrayDM.add("Motorola");
        arrayDM.add("Asus");
        arrayDM.add("Xiaomi");

        final ArrayAdapter SP_adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arraySP);
        final ArrayAdapter DM_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayDM);
        listSP.setAdapter(SP_adapter);
        spinnerDM.setAdapter(DM_adapter);
        btnNhapSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edtMaSP.getText().toString()
                        + " - " + spinnerDM.getSelectedItem().toString()
                        + " - " + edtTenSP.getText().toString();
                arraySP.add(str);
                SP_adapter.notifyDataSetChanged();
            }
        });

    }
    private void mapping(){
        spinnerDM = (Spinner) findViewById(R.id.spinerDanhMuc);
        edtMaSP = (EditText) findViewById(R.id.edtMaSP);
        edtTenSP = (EditText) findViewById(R.id.edtTenSP);
        btnNhapSP = (Button) findViewById(R.id.btnNhapSP);
        listSP = (ListView) findViewById(R.id.listSP);
    }
}
