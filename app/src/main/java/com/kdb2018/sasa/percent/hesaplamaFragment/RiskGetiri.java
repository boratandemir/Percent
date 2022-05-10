package com.kdb2018.sasa.percent.hesaplamaFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kdb2018.sasa.percent.R;

public class RiskGetiri extends AppCompatActivity {

    EditText editTextText_Kar_Hedefi;
    EditText editTextText_Stop_Noktası;
    Button button_risk;
    TextView textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk__getiri);
        editTextText_Kar_Hedefi = findViewById(R.id.editTextText_Kar_Hedefi);
        editTextText_Stop_Noktası = findViewById(R.id.editTextText_Stop_Noktası);
        button_risk = findViewById(R.id.button_risk);
        textView4 = findViewById(R.id.textView4);
        button_risk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextText_Kar_Hedefi.getText().toString().equals("")&&
                        editTextText_Stop_Noktası.getText().toString().equals("")) {
                    Toast.makeText(RiskGetiri.this, "Değer giriniz", Toast.LENGTH_SHORT).show();
                }else{
                    double kar = Double.parseDouble(editTextText_Kar_Hedefi.getText().toString());
                    double stop = Double.parseDouble(editTextText_Stop_Noktası.getText().toString());

                    double sonuc = stop/kar;

                    textView4.setText("Risk/Getiri oranınız: "+sonuc);
                }

                CloseKeyboard(RiskGetiri.this);
            }
        });


    }

    public void CloseKeyboard(Activity activity) {

        if (activity.getCurrentFocus() == null) {
            return;
        }
        System.out.println("hata");
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}