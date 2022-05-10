package com.kdb2018.sasa.percent.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.kdb2018.sasa.percent.Keys;
import com.kdb2018.sasa.percent.R;
import com.kdb2018.sasa.percent.adapter.HisseAdapter;
import com.kdb2018.sasa.percent.api.DovizApi;
import com.kdb2018.sasa.percent.api.DovizClient;
import com.kdb2018.sasa.percent.model.hisseModel.HisseModel;
import com.kdb2018.sasa.percent.model.hisseModel.HissePage;
import com.kdb2018.sasa.percent.veriTabani.CuzdanDaoHisse;
import com.kdb2018.sasa.percent.veriTabani.VeriTabani;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class Fragment_DovizHisse extends Fragment implements HisseAdapter.ItemClickListener {

    private DovizApi api;
    private RecyclerView recyclerView;
    private List<HisseModel> hisseListe=new ArrayList<>();

    private HisseModel hisse;
    private EditText editTextSearchHisse;
    private HisseAdapter adapter;

    String[] sirketler =  {"AAPL","MSFT","AMZN","GOOGL","FB","NVDA"};
    //,"TSM","TSLA","BABA","V","JPM","BRK.A","JNJ","WMT","UNH","MA","BAC","HD","PG","DIS","PYPL"};
    Button button ;
    private VeriTabani vt2;

    private SwipeRefreshLayout swipeRefresh;

    String key = Keys.hisseKey;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hisse_doviz_fragment_layout, container, false);
        api = DovizClient.getClient().create(DovizApi.class);


        editTextSearchHisse=rootView.findViewById(R.id.editTextSearchHisse);
        button=rootView.findViewById(R.id.buttonSearchHisse);
        swipeRefresh=rootView.findViewById(R.id.swipeHisse);

        vt2 =new VeriTabani(getActivity());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comp = String.valueOf(editTextSearchHisse.getText());
                if(comp.equals("")){
                    ilkAcilis();
                    RecyclerBagla(rootView);
                }else{
                    DovizList(comp);
                    RecyclerBagla(rootView);
                }

            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RecyclerBagla(rootView);
                ilkAcilis();
                swipeRefresh.setRefreshing(false);
            }
        });


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewHisse);


        ilkAcilis();
        //DovizList("MSFT");
        RecyclerBagla(rootView);

        return rootView;
    }

    private void RecyclerBagla(View rootView) {
        recyclerView= rootView.findViewById(R.id.recyclerViewHisse);

        hisseListe = new ArrayList<>();

        adapter= new HisseAdapter(hisseListe, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
    public void ilkAcilis(){
        for(int i =0;i<sirketler.length;i++){

            Call<HisseModel> call = api.getUsers("https://api.twelvedata.com/quote?symbol="
                    +sirketler[i]+
                    "&apikey="+key+"&source=docs");


            call.enqueue(new Callback<HisseModel>() {
                @Override
                public void onResponse(Call<HisseModel> call, Response<HisseModel> response) {

                    if(response.isSuccessful()){

                        HisseModel list = response.body();
                    //   hisseListe.clear();

                        hisse=list;
                        hisseListe.add(hisse);


                        adapter.notifyDataSetChanged();

                    }else{
                        System.out.println("Hata");
                    }


                }

                @Override
                public void onFailure(Call<HisseModel> call, Throwable t) {
                    System.out.println(t.fillInStackTrace());
                }
            });
        }
    }

    public void DovizList(String company){


            Call<HisseModel> call = api.getUsers("https://api.twelvedata.com/quote?symbol="
                    +company+
                    "&apikey=6b957670874e4a7f81e0c77b7f193395&source=docs");

            if(call.equals(null)){
                System.out.println("hatalı giriş");
            }else{

                call.enqueue(new Callback<HisseModel>() {
                    @Override
                    public void onResponse(Call<HisseModel> call, Response<HisseModel> response) {
                        if(response.isSuccessful()){

                            HisseModel list = response.body();

                            System.out.println(list.getClose());
                            //hisseListe.clear();

                            hisse=list;
                            hisseListe.add(hisse);

                            System.out.println(hisseListe.size());
                            adapter.notifyDataSetChanged();

                        }else{
                            System.out.println("Hata");
                        }


                    }

                    @Override
                    public void onFailure(Call<HisseModel> call, Throwable t) {
                        System.out.println(t.fillInStackTrace());
                    }
                });
            }



    }

    @Override
    public void onItemClick(int position) {

        View tasarim = getLayoutInflater().inflate(R.layout.hisse_alert_view,null);

        Button buttonAdetHisse,buttonDolarHisse,buttonAnewlizHisse,buttonDetayHisse;
        EditText editTextCuzdanEkleHisse,editTextDegerGirHisse;

        Switch aSwitchHisse;
        aSwitchHisse=tasarim.findViewById(R.id.aSwitchHisse);
        buttonAdetHisse=tasarim.findViewById(R.id.buttonAdetHisse);
        buttonDolarHisse=tasarim.findViewById(R.id.buttonDolarHisse);
        buttonAnewlizHisse=tasarim.findViewById(R.id.buttonAnewlizHisse);
        buttonDetayHisse=tasarim.findViewById(R.id.buttonDetayHisse);
        editTextCuzdanEkleHisse=tasarim.findViewById(R.id.editTextCuzdanEkleHisse);
        editTextDegerGirHisse=tasarim.findViewById(R.id.editTextDegerGirHisse);
        editTextDegerGirHisse.setVisibility(editTextDegerGirHisse.INVISIBLE);

        aSwitchHisse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!aSwitchHisse.isChecked()){
                    editTextDegerGirHisse.setVisibility(editTextDegerGirHisse.INVISIBLE);
                    editTextDegerGirHisse.setText(null);
                }else{
                    editTextDegerGirHisse.setVisibility(editTextDegerGirHisse.VISIBLE);
                }
            }
        });

        AlertDialog ad = new AlertDialog.Builder(this.getActivity()).create();

        buttonAdetHisse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((editTextCuzdanEkleHisse.getText()+"").equals("")){
                    Toast.makeText(getContext(), "Cüzdana eklemek için değer giriniz", Toast.LENGTH_SHORT).show();
                }else{
                    if(!aSwitchHisse.isChecked()){
                        String hisse_adi= hisseListe.get(position).getSymbol();
                        float hisse_alis= Float.valueOf(String.valueOf(hisseListe.get(position).getClose()));
                        Float hisse_adet= Float.valueOf(String.valueOf(editTextCuzdanEkleHisse.getText()));

                        new CuzdanDaoHisse().HisseEkle(vt2,hisse_adi,hisse_alis,hisse_adet);
                        Toast.makeText(getContext(), "Cüzdana Eklendi", Toast.LENGTH_SHORT).show();


                        editTextCuzdanEkleHisse.setText("");
                        editTextDegerGirHisse.setText("");
                    }else{
                        String hisse_adi= hisseListe.get(position).getSymbol();
                        Float hisse_adet= Float.valueOf(String.valueOf(editTextCuzdanEkleHisse.getText()));
                        float hisse_alis_fiyati= Float.valueOf(String.valueOf(editTextDegerGirHisse.getText()));

                        new CuzdanDaoHisse().HisseEkle(vt2,hisse_adi,hisse_alis_fiyati,hisse_adet);
                        Toast.makeText(getContext(), "Cüzdana Eklendi", Toast.LENGTH_SHORT).show();

                        editTextCuzdanEkleHisse.setText("");
                        editTextDegerGirHisse.setText("");
                    }
                }
                InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(editTextCuzdanEkleHisse.getWindowToken(),0);
                ad.cancel();
            }

        });

        buttonDolarHisse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((editTextCuzdanEkleHisse.getText()+"").equals("")){
                    Toast.makeText(getContext(), "Cüzdana eklemek için değer giriniz", Toast.LENGTH_SHORT).show();

                }else{
                    if(!aSwitchHisse.isChecked()) {
                        String hisse_adi = hisseListe.get(position).getSymbol();
                        float hisse_alis_fiyati = Float.parseFloat(String.valueOf(hisseListe.get(position).getClose()));
                        float cuzdan = Float.parseFloat(editTextCuzdanEkleHisse.getText().toString());
                        Float bakiye = Float.valueOf(String.valueOf(cuzdan / Float.valueOf(hisseListe.get(position).getClose())));
                        new CuzdanDaoHisse().HisseEkle(vt2, hisse_adi, hisse_alis_fiyati, bakiye);
                        Toast.makeText(getContext(), "Cüzdana Eklendi", Toast.LENGTH_SHORT).show();

                        Toast.makeText(getContext(), "cüzdandaki değer = " + bakiye, Toast.LENGTH_LONG).show();

                    }else{
                        String hisse_adi= hisseListe.get(position).getSymbol();
                        float hisse_alis_fiyati= Float.parseFloat(String.valueOf(editTextCuzdanEkleHisse.getText()));
                        float cuzdan = Float.parseFloat(editTextCuzdanEkleHisse.getText().toString());
                        float yeni_deger = Float.parseFloat(editTextDegerGirHisse.getText().toString());
                        Float bakiye = Float.valueOf(String.valueOf(cuzdan/yeni_deger));

                        new CuzdanDaoHisse().HisseEkle(vt2,hisse_adi,hisse_alis_fiyati,bakiye);
                        Toast.makeText(getContext(), "Cüzdana Eklendi", Toast.LENGTH_SHORT).show();

                        Toast.makeText(getContext(), "cüzdandaki değer adet olarak = "+bakiye, Toast.LENGTH_LONG).show();
                        editTextCuzdanEkleHisse.setText("");
                        editTextDegerGirHisse.setText("");


                    }

                }
                InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(editTextCuzdanEkleHisse.getWindowToken(),0);
                ad.cancel();

            }
        });

        ad.setTitle(hisseListe.get(position).getName());
        ad.setMessage("\n"+"Değişim yüzdesi"+String.valueOf(hisseListe.get(position).getPercentChange()+"\n"+"\n"+
                        "Değeri :" + String.valueOf(hisseListe.get(position).getClose())));

        ad.setView(tasarim);
        ad.show();
        buttonDetayHisse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HissePage.class);
                intent.putExtra("hisse",adapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
