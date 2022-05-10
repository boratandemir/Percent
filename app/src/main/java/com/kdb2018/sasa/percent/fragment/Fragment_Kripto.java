package com.kdb2018.sasa.percent.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kdb2018.sasa.percent.adapter.KriptoAdapter;
import com.kdb2018.sasa.percent.model.kriptoModel.CoinPage;
import com.kdb2018.sasa.percent.R;
import com.kdb2018.sasa.percent.api.ApiClient;
import com.kdb2018.sasa.percent.api.CoinMarketApi;

import com.kdb2018.sasa.percent.model.kriptoModel.CryptoModel;

import com.kdb2018.sasa.percent.model.kriptoModel.Datum;
import com.kdb2018.sasa.percent.veriTabani.CuzdanDaoKripto;
import com.kdb2018.sasa.percent.veriTabani.VeriTabani;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Kripto extends Fragment implements KriptoAdapter.ItemClickListener {

    private SwipeRefreshLayout swipeRefresh;

    KriptoAdapter adapter;
    List<Datum> filteredList2 = new ArrayList<>();
    CoinMarketApi api;
    Button button;
    EditText editText;
    private RecyclerView recyclerView;
    private List<Datum> cryptoList = null;
    private VeriTabani vt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.kripto_fragment_layout, container, false);

        api = ApiClient.getClient().create(CoinMarketApi.class);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewKripto);
        editText = rootView.findViewById(R.id.editTextSearch);
        button = rootView.findViewById(R.id.buttonSearch);
        vt = new VeriTabani(getActivity());

        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                RecyclerBagla(rootView);
                CoinList();
                swipeRefresh.setRefreshing(false);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseKeyboard(getActivity());
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filteredList2=filter(s.toString());

            }
        });
        CoinList();
        RecyclerBagla(rootView);

        return rootView;
    }

    private void RecyclerBagla(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerViewKripto);

        cryptoList = new ArrayList<>();

        adapter = new KriptoAdapter(cryptoList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    public void CoinList() {
        Call<CryptoModel> call = api.coinMarketListCall("500");

        call.enqueue(new Callback<CryptoModel>() {
            @Override
            public void onResponse(Call<CryptoModel> call, Response<CryptoModel> response) {

                CryptoModel list = response.body();

                cryptoList.clear();
                cryptoList.addAll(list.getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CryptoModel> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure", Toast.LENGTH_SHORT).show();
                Log.d("XXXX", t.getLocalizedMessage());
                call.cancel();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        View tasarim = getLayoutInflater().inflate(R.layout.kripto_alert_view, null);
        Button buttonAdet, buttonDolar, buttonAnewliz, buttonDetay;
        EditText editTextCuzdanEkle, editTextDegerGir;


        Switch aSwitch;
        aSwitch = tasarim.findViewById(R.id.aSwitch);
        buttonAdet = tasarim.findViewById(R.id.buttonAdet);
        buttonDolar = tasarim.findViewById(R.id.buttonDolar);
        buttonAnewliz = tasarim.findViewById(R.id.buttonAnewliz);
        buttonDetay = tasarim.findViewById(R.id.buttonDetay);
        editTextCuzdanEkle = tasarim.findViewById(R.id.editTextCuzdanEkle);
        editTextDegerGir = tasarim.findViewById(R.id.editTextDegerGir);
        editTextDegerGir.setVisibility(editTextDegerGir.INVISIBLE);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!aSwitch.isChecked()) {
                    editTextDegerGir.setVisibility(editTextDegerGir.INVISIBLE);
                    editTextDegerGir.setText(null);
                } else {
                    editTextDegerGir.setVisibility(editTextDegerGir.VISIBLE);
                }
            }
        });


        String img = "R.drawable." + cryptoList.get(position).getSymbol().toLowerCase();
        AlertDialog ad = new AlertDialog.Builder(this.getActivity()).create();

        buttonAdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((editTextCuzdanEkle.getText() + "").equals("")) {
                    Toast.makeText(getContext(), "Cüzdana eklemek için değer giriniz", Toast.LENGTH_SHORT).show();
                } else {

                    if (!aSwitch.isChecked()) {
                        String coin_adi = cryptoList.get(position).getSymbol();
                        float coin_alis = Float.valueOf(String.valueOf(cryptoList.get(position).getQuote().getUSD().getPrice()));
                        Float coin_adet = Float.valueOf(String.valueOf(editTextCuzdanEkle.getText()));


                        new CuzdanDaoKripto().CoinEkle(vt, coin_adi, coin_alis, coin_adet);

                        Toast.makeText(getContext(), "Cüzdana Eklendi", Toast.LENGTH_SHORT).show();

                        editTextCuzdanEkle.setText("");
                        editTextDegerGir.setText("");


                    } else {

                        String coin_adi = cryptoList.get(position).getSymbol();
                        Float coin_adet = Float.valueOf(String.valueOf(editTextCuzdanEkle.getText()));
                        float alis_fiyati = Float.valueOf(String.valueOf(editTextDegerGir.getText()));
                        new CuzdanDaoKripto().CoinEkle(vt, coin_adi, alis_fiyati, coin_adet);

                        editTextCuzdanEkle.setText("");
                        editTextDegerGir.setText("");
                    }

                }

                InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(editTextCuzdanEkle.getWindowToken(), 0);
                ad.cancel();
            }
        });
        buttonDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((editTextCuzdanEkle.getText() + "").equals("")) {
                    Toast.makeText(getContext(), "Cüzdana eklemek için değer giriniz", Toast.LENGTH_SHORT).show();

                } else {
                    if (!aSwitch.isChecked()) {
                        String coin_adi = cryptoList.get(position).getSymbol();
                        float alis_fiyati = Float.parseFloat(String.valueOf(cryptoList.get(position).getQuote().getUSD().getPrice()));
                        float cuzdan = Float.parseFloat(editTextCuzdanEkle.getText().toString());
                        Float bakiye = Float.valueOf(String.valueOf(cuzdan / cryptoList.get(position).getQuote().getUSD().getPrice()));
                        new CuzdanDaoKripto().CoinEkle(vt, coin_adi, alis_fiyati, bakiye);
                        Toast.makeText(getContext(), "cüzdandaki değer = " + bakiye, Toast.LENGTH_LONG).show();

                        editTextCuzdanEkle.setText("");
                        editTextDegerGir.setText("");
                    } else {
                        String coin_adi = cryptoList.get(position).getSymbol();
                        float alis_fiyati = Float.parseFloat(editTextDegerGir.getText().toString());
                        float cuzdan = Float.parseFloat(editTextCuzdanEkle.getText().toString());
                       // float yeni_deger = Float.parseFloat(editTextDegerGir.getText().toString());
                        Float bakiye = Float.valueOf(String.valueOf(cuzdan / alis_fiyati));
                        new CuzdanDaoKripto().CoinEkle(vt, coin_adi, alis_fiyati, bakiye);
                        Toast.makeText(getContext(), "cüzdandaki değer adet olarak = " + bakiye, Toast.LENGTH_LONG).show();
                        editTextCuzdanEkle.setText("");
                        editTextDegerGir.setText("");
                    }
                }
                InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(editTextCuzdanEkle.getWindowToken(), 0);
                ad.cancel();
            }

        });

        if(filteredList2.size()==0){
            ad.setTitle(cryptoList.get(position).getName() + "(" + cryptoList.get(position).getSymbol() + ")");
            ad.setMessage("\n" + "Değer: " + String.format("%.2f", cryptoList.get(position).getQuote().getUSD().getPrice()) + "\n" + "\n" +
                    "Değişim Yüzdesi: " + String.format("%.2f", cryptoList.get(position).getQuote().getUSD().getPercentChange1h()));
        }else{
            ad.setTitle(filteredList2.get(position).getName() + "(" + filteredList2.get(position).getSymbol() + ")");
            ad.setMessage("\n" + "Değer: " + String.format("%.2f", filteredList2.get(position).getQuote().getUSD().getPrice()) + "\n" + "\n" +
                    "Değişim Yüzdesi: " + String.format("%.2f", filteredList2.get(position).getQuote().getUSD().getPercentChange1h()));
        }


        //ad.setIcon(R.drawable.eth);
        ad.setView(tasarim);
        ad.show();
        buttonDetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CoinPage.class);
                intent.putExtra("coin", adapter.getItem(position));

                startActivity(intent);
            }
        });
    }

    public void CloseKeyboard(Activity activity) {

        if (activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public List<Datum> filter(String key) {
        List<Datum> filteredList = new ArrayList<>();
        for (Datum item : cryptoList) {
            if (item.getName().toLowerCase().contains(key.toLowerCase()) ||
                    item.getSymbol().toLowerCase().contains(key.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.FilteredList(filteredList);

        return filteredList;
    }

}
