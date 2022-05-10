package com.kdb2018.sasa.percent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kdb2018.sasa.percent.fragment.Fragment_DovizHisse;
import com.kdb2018.sasa.percent.fragment.Fragment_Hesaplama;
import com.kdb2018.sasa.percent.fragment.Fragment_Kripto;
import com.kdb2018.sasa.percent.fragment.Fragment_Varliklarim;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottom_nav;
    private Fragment tempFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottom_nav = findViewById(R.id.bottom_nav);


        getSupportFragmentManager().beginTransaction().add(R.id.fragment_tutucu, new Fragment_Kripto()).commit();

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.kripto_nav_button)//KRİPTO tuşu
                {
                    tempFragment = new Fragment_Kripto();
                }
                if(item.getItemId() == R.id.doviz_hisse_nav_button)//DÖVİZ/HİSSE tuşu
                {
                    tempFragment = new Fragment_DovizHisse();
                }
                if(item.getItemId() == R.id.hesaplama_nav_button)//HESAPLAMA tuşu
                {
                    tempFragment = new Fragment_Hesaplama();
                }
                if(item.getItemId() == R.id.varliklar_nav_button)//VARLIKLARIM tuşu
                {
                    tempFragment = new Fragment_Varliklarim();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,tempFragment).commit();
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);



    }
}