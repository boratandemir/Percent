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

import java.util.ArrayList;

public class MaliyetHesaplama extends AppCompatActivity {
    EditText editTextTextPersonName;
    EditText editTextTextPersonName2;
    TextView textView4_Maliyet;
    Button button_maliyet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maliyet_hesaplama);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        textView4_Maliyet = findViewById(R.id.textView4_Maliyet);
        button_maliyet = findViewById(R.id.button_maliyey);


        ArrayList<Double> miktar123 = new ArrayList<Double>();
        //top_para 0. indekste top miktar 2. indekste

        button_maliyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double top_para=0;
                double miktar=0;
                double top_miktar=0;
                double maliyet = 0;
                double para=0;
                double coin_degeri=0;
                if(editTextTextPersonName.getText().toString().equals("")&&
                        editTextTextPersonName2.getText().toString().equals(""))
                        {
                    Toast.makeText(MaliyetHesaplama.this, "Değer giriniz", Toast.LENGTH_SHORT).show();
                }else{
                    para = Double.parseDouble(editTextTextPersonName.getText().toString());
                    miktar123.add(para);
                    coin_degeri = Double.parseDouble(editTextTextPersonName2.getText().toString());
                    miktar = para/coin_degeri;
                    miktar123.add(miktar);
                    for(int i=0;i<miktar123.size();i++)
                    {
                        if(i%2==0)//tek
                        {
                            top_para=top_para+miktar123.get(i);
                        }
                        else//çifttir
                        {
                            top_miktar=top_miktar+miktar123.get(i);
                        }
                    }

                    //top_para=top_para+para;
                    //top_miktar = top_miktar + miktar;

                    maliyet = top_para/top_miktar;
                    textView4_Maliyet.setText(maliyet+"");




                }
                CloseKeyboard(MaliyetHesaplama.this);
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