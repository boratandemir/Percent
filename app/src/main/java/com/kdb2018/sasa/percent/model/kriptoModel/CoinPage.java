package com.kdb2018.sasa.percent.model.kriptoModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.kdb2018.sasa.percent.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CoinPage extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kripto_detay);

        Intent intent = getIntent();
        Datum datum = (Datum) intent.getSerializableExtra("coin");



        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        TextView date = findViewById(R.id.date);

        TextView symbol = findViewById(R.id.symbol);
        TextView symboldata = findViewById(R.id.symbolData);

        TextView slug = findViewById(R.id.slug);
        TextView slugdata = findViewById(R.id.slugData);

        TextView volume24h = findViewById(R.id.volume24h);
        TextView volume24hdata = findViewById(R.id.volume24hData);

        TextView circulating_supply = findViewById(R.id.circulating_supply);
        TextView circulating_supplydata = findViewById(R.id.circulating_supplyData);

        TextView max_supply = findViewById(R.id.max_supply);
        TextView max_supplydata = findViewById(R.id.max_supplyData);

        TextView market_cap = findViewById(R.id.market_cap);
        TextView market_capdata = findViewById(R.id.market_capData);

        TextView change1h = findViewById(R.id.change1h);
        TextView change1hdata = findViewById(R.id.change1hData);

        TextView change24h = findViewById(R.id.change24h);
        TextView change24hdata = findViewById(R.id.change24hData);

        TextView change7d = findViewById(R.id.change7d);
        TextView change7ddata = findViewById(R.id.change7dData);


        name.setText(datum.getName() + " (" + datum.getSymbol() + ")");
        price.setText("Değeri: $" + String.format("%,f", datum.getQuote().getUSD().getPrice()));
        date.setText("Son Güncelleme: " + parseDateToddMMyyyy(datum.getLastUpdated()));

        symboldata.setText(datum.getSymbol());
        slugdata.setText(datum.getSlug());
        volume24hdata.setText("$" + String.format("%,d", Math.round(datum.getQuote().getUSD().getVolume24h())));

        circulating_supplydata.setText(String.format("%.0f", datum.getCirculatingSupply()) + " " + datum.getSymbol());
        max_supplydata.setText(String.format("%.0f", datum.getMaxSupply()) + " " + datum.getSymbol());

        market_capdata.setText("$" +String.format("%,d", Math.round(datum.getQuote().getUSD().getMarketCap())));

        change1hdata.setText(String.format("%.5f", datum.getQuote().getUSD().getPercentChange1h()) + "%");
        change24hdata.setText(String.format("%.5f", datum.getQuote().getUSD().getPercentChange24h()) + "%");
        change7ddata.setText(String.format("%.5f", datum.getQuote().getUSD().getPercentChange7d()) + "%");
    }


    private String parseDateToddMMyyyy(String time) {
        //parse the server timestamp. Make sure it is in UTC timezone as per API specifications.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        //format the utc server timestamp to local timezone.
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        output.setTimeZone(TimeZone.getDefault());

        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output.format(date);
    }


}
