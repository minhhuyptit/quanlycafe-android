package com.example.pokergame;

import android.util.Log;

public class Player {
    int[] result = new int[3];

    public int Score(){
        int sum =0;
        for(int s: result){
            int score = s%13;
            if(score<=10) sum += score;
        }
        return sum%10;
    }

    public void RandomPoker(){
        int random;
        boolean check;
        for (int i=0; i<3; i++){
            check = true;
            random = (int)(Math.random() * 51 + 1); //1-52
            for(int j=0; j<i; j++){
                if(random==result[j]){
                    i--;
                    check=false;
                    break;
                }
            }
            if(check) result[i]=random;
        }
    }

    public String test(){
        String t="";
        for(int s: result){
            t+= " - " +s;
        }
        return t;
    }
}
