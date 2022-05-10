package com.kdb2018.sasa.percent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(900);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);

    }
}