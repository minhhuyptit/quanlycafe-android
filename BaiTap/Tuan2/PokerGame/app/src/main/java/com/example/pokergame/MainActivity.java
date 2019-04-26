package com.example.pokergame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView img1_p1, img2_p1, img3_p1;
    private ImageView img1_p2, img2_p2, img3_p2;
    private TextView sorcer_p1, sorcer_p2;
    private Button playAgain_p1, playAgain_p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControlPlayer();
        setEventPlayer();
    }
    //------------------------------------------Set Control
    private void setControlPlayer() {
        img1_p1 = findViewById(R.id.poker1_p1);
        img2_p1 = findViewById(R.id.poker2_p1);
        img3_p1 = findViewById(R.id.poker3_p1);
        sorcer_p1 = findViewById(R.id.score_p1);
        playAgain_p1 = findViewById(R.id.play_again_p1);

        img1_p2 = findViewById(R.id.poker1_p2);
        img2_p2 = findViewById(R.id.poker2_p2);
        img3_p2 = findViewById(R.id.poker3_p2);
        sorcer_p2 = findViewById(R.id.score_p2);
        playAgain_p2 = findViewById(R.id.play_again_p2);
    }

    //------------------------------------------Set default game
    private void setEventPlayer() {
        int id = R.drawable.a;
        img1_p1.setImageResource(id);
        img2_p1.setImageResource(id);
        img3_p1.setImageResource(id);

        img1_p2.setImageResource(id);
        img2_p2.setImageResource(id);
        img3_p2.setImageResource(id);
    }
    private void setEventPlayer1(int[] A) {
        int id;
        String[] pokerName = new String[A.length];
        for(int i=0; i<A.length; i++)
            pokerName[i] = (A[i]>=10?""+A[i]:"0"+A[i]);

        id = getResources().getIdentifier("poker_"+pokerName[0], "drawable", getPackageName());
        img1_p1.setImageResource(id);
        id = getResources().getIdentifier("poker_"+pokerName[1], "drawable", getPackageName());
        img2_p1.setImageResource(id);
        id = getResources().getIdentifier("poker_"+pokerName[2], "drawable", getPackageName());
        img3_p1.setImageResource(id);
    }

    private void setEventPlayer2(int[] A) {
        int id;
        String[] pokerName = new String[A.length];
        for(int i=0; i<A.length; i++)
            pokerName[i] = (A[i]>=10?""+A[i]:"0"+A[i]);

        id = getResources().getIdentifier("poker_"+pokerName[0], "drawable", getPackageName());
        img1_p2.setImageResource(id);
        id = getResources().getIdentifier("poker_"+pokerName[1], "drawable", getPackageName());
        img2_p2.setImageResource(id);
        id = getResources().getIdentifier("poker_"+pokerName[2], "drawable", getPackageName());
        img3_p2.setImageResource(id);
    }

    public void NewGame_p1(View view){
        Log.d("message","New Game");
        Player p1 = new Player();
        p1.RandomPoker();
        sorcer_p1.setText("Score: " + p1.Score());
        setEventPlayer1(p1.result);
    }
    public void NewGame_p2(View view){
        Player p2 = new Player();
        p2.RandomPoker();
        sorcer_p2.setText("Score: " + p2.Score());
        setEventPlayer2(p2.result);
    }
}
