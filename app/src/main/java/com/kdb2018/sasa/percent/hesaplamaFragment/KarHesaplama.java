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

public class KarHesaplama extends AppCompatActivity {

    EditText editText_giriş;
    EditText editText_hedef;
    EditText editText_Tutar;
    Button button;
    TextView textView_kar;
    TextView textView_kar_tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kar__hesaplama);

        editText_giriş = findViewById(R.id.editText_giris);
        editText_hedef = findViewById(R.id.editText_Hedef);
        editText_Tutar = findViewById(R.id.editText_Tutar);
        button = findViewById(R.id.button_hesapla);
        textView_kar = findViewById(R.id.textView_kar);
        textView_kar_tl = findViewById(R.id.textView_kar_tl);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                double tutar=0.0;
                if(editText_giriş.getText().toString().equals("")&&
                        editText_hedef.getText().toString().equals("")&&
                        editText_Tutar.getText().toString().equals("")) {
                    Toast.makeText(KarHesaplama.this, "Değer giriniz", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    double giriş = Double.parseDouble(editText_giriş.getText().toString());
                    double hedef = Double.parseDouble(editText_hedef.getText().toString());
                    tutar = Double.parseDouble(editText_Tutar.getText().toString());

                    //Log.d(giriş.toString(),hedef.toString(),tutar.toString());

                    //System.out.println(giriş+""+hedef+""+tutar);
                    double giriş_yuzde = giriş/100;
                    double yuzde = (hedef/giriş_yuzde)-100;
                    double son_tutar = (tutar/100)*(yuzde+100);

                    // System.out.println(yuzde+""+"     "+son_tutar);
                    textView_kar.setText("%"+yuzde);
                    textView_kar_tl.setText(son_tutar+"");

                }CloseKeyboard(KarHesaplama.this);
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