package com.kdb2018.sasa.percent.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kdb2018.sasa.percent.R;

import java.util.ArrayList;

public class Fragment_Hesaplama extends Fragment {

    Button kar_zarar;
    Button button_maliyet;
    Button button_risk_getiri;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.hesaplama_fragment_layout, container, false);
        kar_zarar = (Button) v.findViewById(R.id.button_kar_zarar);
        button_maliyet = (Button) v.findViewById(R.id.button_maliyet);
        button_risk_getiri = (Button) v.findViewById(R.id.button_risk_getiri);

        kar_zarar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tasarim = getLayoutInflater().inflate(R.layout.activity_kar__hesaplama, null);

                TextView editText_giris = tasarim.findViewById(R.id.editText_giris);
                EditText editText_hedef = tasarim.findViewById(R.id.editText_Hedef);
                EditText editText_Tutar = tasarim.findViewById(R.id.editText_Tutar);
                Button button = tasarim.findViewById(R.id.button_hesapla);
                TextView textView_kar = tasarim.findViewById(R.id.textView_kar);
                TextView textView_kar_tl = tasarim.findViewById(R.id.textView_kar_tl);


                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("Kâr/Zarar Hesabı");

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        double tutar = 0.0;
                        if (editText_giris.getText().toString().equals("") &&
                                editText_hedef.getText().toString().equals("") &&
                                editText_Tutar.getText().toString().equals("")) {
                            Toast.makeText(getContext(), "Değer giriniz", Toast.LENGTH_SHORT).show();
                            CloseKeyboard(getActivity());
                        } else {
                            double giriş = Double.parseDouble(editText_giris.getText().toString());
                            double hedef = Double.parseDouble(editText_hedef.getText().toString());
                            tutar = Double.parseDouble(editText_Tutar.getText().toString());

                            double giriş_yuzde = giriş / 100;
                            double yuzde = (hedef / giriş_yuzde) - 100;
                            double son_tutar = (tutar / 100) * (yuzde + 100);
                            if(yuzde<0){
                                textView_kar.setTextColor(Color.RED);
                                textView_kar.setText("%" + String.format("%.4f", yuzde));
                                textView_kar_tl.setText(String.format("%.4f", son_tutar));
                            }else{
                                textView_kar.setTextColor(Color.GREEN);
                                textView_kar.setText("%" + String.format("%.4f", yuzde));
                                textView_kar_tl.setText(String.format("%.4f", son_tutar));
                            }


                        }

                        CloseKeyboard(getActivity()); }
                });

                ad.setView(tasarim);
                ad.show();
            }

        });

        button_maliyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View tasarim = getLayoutInflater().inflate(R.layout.activity_maliyet_hesaplama, null);

                EditText editTextTextPersonName = tasarim.findViewById(R.id.editTextTextPersonName);
                EditText editTextTextPersonName2 = tasarim.findViewById(R.id.editTextTextPersonName2);
                TextView textView4_Maliyet = tasarim.findViewById(R.id.textView4_Maliyet);
                Button button_maliyet = tasarim.findViewById(R.id.button_maliyey);

                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("Maliyet Hesabı");
            


                ArrayList<Double> miktar123 = new ArrayList<Double>();
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
                            Toast.makeText(getActivity(), "Değer giriniz", Toast.LENGTH_SHORT).show();
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
                        CloseKeyboard(getActivity());
                    }

                });
                ad.setView(tasarim);
                ad.show();

            }
        });

        button_risk_getiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View tasarim = getLayoutInflater().inflate(R.layout.activity_risk__getiri, null);

                EditText editTextText_Kar_Hedefi= tasarim.findViewById(R.id.editTextText_Kar_Hedefi);
                EditText editTextText_Stop_Noktası=tasarim.findViewById(R.id.editTextText_Stop_Noktası);
                Button button_risk=tasarim.findViewById(R.id.button_risk);
                TextView textView4= tasarim.findViewById(R.id.textView4);

                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("Risk Durumu Hesabı");
                ad.setMessage("Denem2");

                button_risk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editTextText_Kar_Hedefi.getText().toString().equals("")&&
                                editTextText_Stop_Noktası.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Değer giriniz", Toast.LENGTH_SHORT).show();
                        }else{
                            double kar = Double.parseDouble(editTextText_Kar_Hedefi.getText().toString());
                            double stop = Double.parseDouble(editTextText_Stop_Noktası.getText().toString());

                            double sonuc = stop/kar;

                            textView4.setText(String.format("%.4f",sonuc));
                        }

                        CloseKeyboard(getActivity());
                    }
                });
                ad.setView(tasarim);
                ad.show();


            }
        });
        return v;


    }

    public void CloseKeyboard(Activity activity) {

        if (activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
