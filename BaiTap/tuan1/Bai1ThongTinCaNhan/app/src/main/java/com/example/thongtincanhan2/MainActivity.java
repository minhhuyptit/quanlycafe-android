package com.example.thongtincanhan2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    EditText txtName,txtCMND,txtThongTin;
    CheckBox cbDocBao,cbDocSach,cbDocCoding;
    Button btnGuiThongTin;
    RadioGroup group;
    RadioButton rad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName=(EditText) findViewById(R.id.txtName);
        txtCMND=(EditText) findViewById(R.id.txtCMND);
        txtThongTin=(EditText) findViewById(R.id.txtThongTin);
        cbDocBao=(CheckBox) findViewById(R.id.cbDocBao);
        cbDocSach=(CheckBox) findViewById(R.id.cbDocSach);
        cbDocCoding=(CheckBox) findViewById(R.id.cbDocCoding);
        btnGuiThongTin=(Button) findViewById(R.id.btnGuiThongTin);
        btnGuiThongTin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doShowInformation();
            }
        });
    }
    public void doShowInformation()
    {
        //Kiểm tra tên hợp lệ
        String ten=txtName.getText()+"";
        ten=ten.trim();
        if(ten.length()<3)
        {
            txtName.requestFocus();
            txtName.selectAll();
            Toast.makeText(MainActivity.this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //kiểm tra CMND hợp lệ
        String cmnd=txtCMND.getText()+"";
        cmnd=cmnd.trim();
        if(cmnd.length()!=9)
        {
            txtCMND.requestFocus();
            txtCMND.selectAll();
            Toast.makeText(MainActivity.this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //Kiểm tra bằng cấp
        String bang="";
        group=(RadioGroup) findViewById(R.id.radioGroup);
        int id=group.getCheckedRadioButtonId();
        if(id==-1)
        {
            Toast.makeText(MainActivity.this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        rad=(RadioButton) findViewById(id);
        bang=rad.getText()+"";
        //Kiểm tra sở thích
        String sothich="";
        if(cbDocBao.isChecked())
            sothich+=cbDocBao.getText()+"\n";
        if(cbDocSach.isChecked())
            sothich+=cbDocSach.getText()+"\n";
        if(cbDocCoding.isChecked())
            sothich+=cbDocCoding.getText()+"\n";

        String bosung=txtThongTin.getText()+"";

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        //tạo nội dung
        String msg=ten+"\n";
        msg+= cmnd+"\n";
        msg+=bang+"\n";
        msg+=sothich;
        msg+="-----------------------------\n";
        msg+="Thông tin bổ sung:\n";
        msg+=bosung+ "\n";
        msg+="-----------------------------";
        builder.setMessage(msg);//thiết lập nội dung
        builder.create().show();//hiển thị Dialog
    }


}

