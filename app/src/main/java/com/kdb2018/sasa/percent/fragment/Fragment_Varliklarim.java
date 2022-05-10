package com.kdb2018.sasa.percent.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kdb2018.sasa.percent.model.hisseModel.Liste;
import com.kdb2018.sasa.percent.adapter.VarliklarimKriptoAdapter;
import com.kdb2018.sasa.percent.adapter.VarliklarımHisseAdapter;
import com.kdb2018.sasa.percent.api.DovizApi;
import com.kdb2018.sasa.percent.api.DovizClient;
import com.kdb2018.sasa.percent.model.CuzdanModel;
import com.kdb2018.sasa.percent.R;
import com.kdb2018.sasa.percent.api.ApiClient;
import com.kdb2018.sasa.percent.api.CoinMarketApi;
import com.kdb2018.sasa.percent.model.hisseModel.HisseModel;
import com.kdb2018.sasa.percent.model.kriptoModel.CryptoModel;
import com.kdb2018.sasa.percent.model.kriptoModel.Datum;
import com.kdb2018.sasa.percent.veriTabani.CuzdanDaoHisse;
import com.kdb2018.sasa.percent.veriTabani.CuzdanDaoKripto;
import com.kdb2018.sasa.percent.veriTabani.VeriTabani;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Varliklarim extends Fragment implements VarliklarimKriptoAdapter.ItemClickListener, VarliklarımHisseAdapter.ItemClickListener {

    RecyclerView kripto_rc,hisse_rc;

    private SwipeRefreshLayout swipeRefreshKripto;
    private SwipeRefreshLayout swipeRefreshHisse;


    private ArrayList<CuzdanModel.CuzdanModelKripto> cuzdanModels;
    private ArrayList<CuzdanModel.CuzdanModelHisse> cuzdanModelHisse;

    VarliklarimKriptoAdapter adapter;
    VarliklarımHisseAdapter adapterHisse;

    Liste hisseListesi = new Liste();

    HisseModel hisse;
    List<HisseModel> mdata =new ArrayList<>();

    private VeriTabani vt,vt2;
    CoinMarketApi api;
    DovizApi api2;

    private List<Datum> cuzdanList =null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.varliklarim_fragment_layout, container, false);

        vt = new VeriTabani(getActivity());
        vt2=new VeriTabani(getActivity());

        kripto_rc =rootView.findViewById(R.id.cuzdan_recycler_view_cripto);
        hisse_rc=rootView.findViewById(R.id.cuzdan_recycler_view_hisse);

        api = ApiClient.getClient().create(CoinMarketApi.class);
        api2= DovizClient.getClient().create(DovizApi.class);
        cuzdanModelHisse= new CuzdanDaoHisse().Cuzdan(vt2);

        swipeRefreshKripto=rootView.findViewById(R.id.swipeCuzdanKripto);
        swipeRefreshHisse=rootView.findViewById(R.id.swipeCuzdanHisse);


        HisseList(rootView);
        int api_size=hisseListesi.Listele();
        CoinList();

        RecyclerBagla(rootView);
        HisseRecyclerBagla(rootView,api_size);

        swipeRefreshKripto.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RecyclerBagla(rootView);
                CoinList();
                swipeRefreshKripto.setRefreshing(false);
            }
        });

        swipeRefreshHisse.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HisseRecyclerBagla(rootView,api_size);
                HisseList(rootView);
                swipeRefreshHisse.setRefreshing(false);
            }
        });

        return rootView;
    }

    public void HisseList(View root){
        mdata =new ArrayList<>();
        for(int i=0;i<cuzdanModelHisse.size();i++){

          Call<HisseModel> call = api2.getUsers("https://api.twelvedata.com/quote?symbol="
                +cuzdanModelHisse.get(i).getHisse_adi()+
                "&apikey=6b957670874e4a7f81e0c77b7f193395&source=docs");

            call.enqueue(new Callback<HisseModel>() {
            @Override
            public void onResponse(Call<HisseModel> call, Response<HisseModel> response) {
                if(response.isSuccessful()){

                    HisseModel list = response.body();
                    hisseListesi.HisseEkle(list.getSymbol(),list.getClose());
//                    hisse=list;
//                    mdata.add(hisse);
                    HisseRecyclerBagla(root,hisseListesi.Listele());
                    hisseListesi.Listele();

                }else{
                    System.out.println("Hata");
                }
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<HisseModel> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        });
    }
    }


    private void HisseRecyclerBagla(View rootview,int api_size){
        hisse_rc=rootview.findViewById(R.id.cuzdan_recycler_view_hisse);
        mdata =new ArrayList<>();

        cuzdanModelHisse= new CuzdanDaoHisse().Cuzdan(vt2);

        adapterHisse= new VarliklarımHisseAdapter(getActivity(),cuzdanModelHisse, hisseListesi,api_size,this);
        hisse_rc.setAdapter(adapterHisse);
        hisse_rc.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void RecyclerBagla(View rootView) {

        kripto_rc =rootView.findViewById(R.id.cuzdan_recycler_view_cripto);
        cuzdanList = new ArrayList<>();
        cuzdanModels = new CuzdanDaoKripto().Cuzdan(vt);

        adapter = new VarliklarimKriptoAdapter(getActivity(),cuzdanModels,cuzdanList,this);
        kripto_rc.setAdapter(adapter);
        kripto_rc.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    public void CoinList() {

        Call<CryptoModel> call = api.coinMarketListCall("500");

        call.enqueue(new Callback<CryptoModel>() {

            @Override
            public void onResponse(Call<CryptoModel> call, Response<CryptoModel> response) {

                CryptoModel list = response.body();

                cuzdanList.clear();
                cuzdanList.addAll(list.getData());
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
        View tasarim = getLayoutInflater().inflate(R.layout.cuzdan_silme,null);
        TextView textView=tasarim.findViewById(R.id.textViewSil);
        AlertDialog.Builder ad = new AlertDialog.Builder(this.getActivity());
        ad.setTitle("Silinsin mi");

        ad.setView(tasarim);
        ad.setPositiveButton("Sil", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new CuzdanDaoKripto().CoinSil(vt,cuzdanModels.get(position).getId());
                FragmentTransaction tr = getFragmentManager().beginTransaction();
                Fragment temp = new Fragment_Varliklarim();
                tr.replace(R.id.fragment_tutucu,temp);
                tr.commit();

            }
        });
        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ad.create().dismiss();
            }
        });
        ad.create().show();
    }

    @Override
    public void onItemClick2(int position) {
        View tasarim = getLayoutInflater().inflate(R.layout.cuzdan_silme,null);
        AlertDialog.Builder ad = new AlertDialog.Builder(this.getActivity());
        ad.setTitle("Silinsin mi");

        ad.setView(tasarim);
        ad.setPositiveButton("Sil", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new CuzdanDaoHisse().HisseSil(vt2,cuzdanModelHisse.get(position).getId());
                FragmentTransaction tr = getFragmentManager().beginTransaction();
                Fragment temp = new Fragment_Varliklarim();
                tr.replace(R.id.fragment_tutucu,temp);
                tr.commit();
            }
        });
        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ad.create().dismiss();
            }
        });
        ad.create().show();
    }

}