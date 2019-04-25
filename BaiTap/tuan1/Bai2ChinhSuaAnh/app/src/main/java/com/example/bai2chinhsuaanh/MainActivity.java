package com.example.bai2chinhsuaanh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private CheckBox chkHair;
    private CheckBox chkDress;
    private CheckBox chkHat;
    private CheckBox chkMask;
    private ImageView imgPicture;
    private Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }
    private void setEvent() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkHair.isChecked() && chkDress.isChecked() && chkHat.isChecked() && chkMask.isChecked()) {
                    imgPicture.setImageResource(R.drawable.body_full);

                }
                else
                {
                    imgPicture.setImageResource(R.drawable.body);
                }

                    if (chkHair.isChecked()) {
                        imgPicture.setImageResource(R.drawable.hair);

                    }
                    if (chkDress.isChecked()) {
                        imgPicture.setImageResource(R.drawable.dress);
                    }
                    if (chkHat.isChecked()) {
                        imgPicture.setImageResource(R.drawable.hat);
                    }
                    if (chkMask.isChecked()) {
                        imgPicture.setImageResource(R.drawable.mask);
                    }
                    if (chkHair.isChecked() && chkDress.isChecked()) {
                        imgPicture.setImageResource(R.drawable.hair_dress);
                    }
                    if (chkHair.isChecked() && chkHat.isChecked()) {
                        imgPicture.setImageResource(R.drawable.hair_hat);
                    }
                    if (chkHair.isChecked() && chkMask.isChecked()) {
                        imgPicture.setImageResource(R.drawable.hair_mask);
                    }
                    if (chkDress.isChecked() && chkHat.isChecked()) {
                        imgPicture.setImageResource(R.drawable.dress_hat);
                    }
                    if (chkDress.isChecked() && chkMask.isChecked()) {
                        imgPicture.setImageResource(R.drawable.dress_mask);
                    }
                    if (chkHat.isChecked() && chkMask.isChecked()) {
                        imgPicture.setImageResource(R.drawable.hat_mask);
                    }
                    if (chkHair.isChecked() && chkDress.isChecked() && chkHat.isChecked()) {
                        imgPicture.setImageResource(R.drawable.hair_dress_hat);
                    }
                    if (chkHair.isChecked() && chkDress.isChecked() && chkMask.isChecked()) {
                        imgPicture.setImageResource(R.drawable.dress_hair_mask);
                    }
                    if (chkHair.isChecked() && chkHat.isChecked() && chkMask.isChecked()){
                    imgPicture.setImageResource(R.drawable.hair_hat_mask);
                    }
                    if (chkHat.isChecked() && chkDress.isChecked() && chkMask.isChecked()) {
                        imgPicture.setImageResource(R.drawable.dress_hat_mask);
                    }
                    if (chkHair.isChecked() && chkDress.isChecked() && chkHat.isChecked() && chkMask.isChecked()) {
                    imgPicture.setImageResource(R.drawable.body_full);

                    }



            }
        });
    }

    private void setControl() {
        chkHair = (CheckBox) findViewById(R.id.chkHair);
        chkDress = (CheckBox) findViewById(R.id.chkDress);
        chkHat = (CheckBox) findViewById(R.id.chkHat);
        chkMask = (CheckBox) findViewById(R.id.chkMask);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        btnOK = (Button) findViewById(R.id.btnOK);
    }


}
