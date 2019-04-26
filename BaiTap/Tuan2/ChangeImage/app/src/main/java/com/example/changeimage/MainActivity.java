package com.example.changeimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioButton radio_Dog, radio_Cat, radio_Bird, radio_Rabbit;
    private ImageView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRadioButtonClick(View view){
        result = findViewById(R.id.result);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id){
            case R.id.radio_Dog:
                result.setImageResource(R.drawable.dog);
                break;
            case R.id.radio_Cat:
                result.setImageResource(R.drawable.cat);
                break;
            case R.id.radio_Bird:
                result.setImageResource(R.drawable.bird);
                break;
            case R.id.radio_Rabbit:
                result.setImageResource(R.drawable.rabbit);
                break;

        }
    }
}
