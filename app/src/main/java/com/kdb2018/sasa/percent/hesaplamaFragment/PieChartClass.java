package com.kdb2018.sasa.percent.hesaplamaFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.kdb2018.sasa.percent.R;

import java.util.ArrayList;

public class PieChartClass extends AppCompatActivity {

    private static String TAG = "PieChartClass";
    private float[] yData = {25.3f, 10.6f, 33.21f, 16.89f};
    private String[] xData = {"Ali Kemal", "Usman", ",Nuri baba", "Gülibik"};
    PieChart pieChart;
    //Description description = pieChart.getDescription();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pieChart =(PieChart) findViewById(R.id.pieChart);
        pieChart.setRotationEnabled(true);
      //  description.setText("Cüzdandakiler");
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Cüzdan Dağılımı");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
    private void addDataSet(){

        ArrayList<PieEntry> yEntry = new ArrayList<>();
        ArrayList<String> xEntry = new ArrayList<>();

        for (int i =0;i<yData.length;i++){
            yEntry.add(new PieEntry(yData[i]));
        }
        for (int i =1;i<xData.length;i++){
            xEntry.add(xData[i]);
        }

        PieDataSet pieDataSet= new PieDataSet(yEntry,"Adetler");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);


        PieData pieData =  new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
