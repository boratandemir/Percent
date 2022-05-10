package com.kdb2018.sasa.percent.model.hisseModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.kdb2018.sasa.percent.R;
import com.kdb2018.sasa.percent.model.hisseModel.HisseModel;

public class HissePage extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hisse_detay);

        Intent intent = getIntent();
        HisseModel datum= (HisseModel) intent.getSerializableExtra("hisse");

        TextView nameHisse = findViewById(R.id.nameHisse);
        TextView priceHisse = findViewById(R.id.priceHisse);
        TextView dateHisse = findViewById(R.id.dateHisse);

        TextView symbolHisse = findViewById(R.id.symbolHisse);
        TextView symbolDataHisse = findViewById(R.id.symbolDataHisse);

        TextView slugHisse = findViewById(R.id.slugHisse);
        TextView slugDataHisse = findViewById(R.id.slugDataHisse);

        TextView lowHisse = findViewById(R.id.lowHisse);
        TextView lowHisseData = findViewById(R.id.lowHisseData);

        TextView highHisse = findViewById(R.id.highHisse);
        TextView highHisseData = findViewById(R.id.highHisseData);

        TextView low_changeHisse = findViewById(R.id.low_changeHisse);
        TextView low_changeHisseData = findViewById(R.id.low_changeHisseData);

        TextView high_changeHisse = findViewById(R.id.high_changeHisse);
        TextView high_changeHisseData = findViewById(R.id.high_changeHisseData);

        TextView low_change_percentHisse = findViewById(R.id.low_change_percentHisse);
        TextView low_change_percentHisseData = findViewById(R.id.low_change_percentHisseData);

        TextView high_change_percentHisse = findViewById(R.id.high_change_percentHisse);
        TextView high_change_percentHisseData = findViewById(R.id.high_change_percentHisseData);

        TextView rangeHisse = findViewById(R.id.rangeHisse);
        TextView rangeHisseData = findViewById(R.id.rangeHisseData);


        nameHisse.setText(datum.getName()+" ( "+datum.getSymbol()+" ) ");
        priceHisse.setText("Değeri: $" + datum.getClose());
        dateHisse.setText("Son Güncelleme: " +datum.getDatetime());

        symbolDataHisse.setText(datum.getSymbol());
        slugDataHisse.setText(datum.getName());

        lowHisseData.setText("$" +datum.getFiftyTwoWeek().getLow());
        System.out.println("Tarih--- "+datum.getDatetime());
        highHisseData.setText("$" +datum.getFiftyTwoWeek().getHigh());
        low_changeHisseData.setText("$" +datum.getFiftyTwoWeek().getLowChange());

        high_changeHisseData.setText("$" +datum.getFiftyTwoWeek().getHighChange());

        low_change_percentHisseData.setText(datum.getFiftyTwoWeek().getLowChangePercent() + "%");
        high_change_percentHisseData.setText(datum.getFiftyTwoWeek().getHighChangePercent() + "%");
        rangeHisseData.setText(datum.getFiftyTwoWeek().getRange());


    }

}
