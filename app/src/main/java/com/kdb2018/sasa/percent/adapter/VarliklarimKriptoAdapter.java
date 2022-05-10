package com.kdb2018.sasa.percent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kdb2018.sasa.percent.model.CuzdanModel;
import com.kdb2018.sasa.percent.R;
import com.kdb2018.sasa.percent.model.kriptoModel.Datum;

import java.io.Serializable;
import java.util.List;

public class VarliklarimKriptoAdapter extends RecyclerView.Adapter<VarliklarimKriptoAdapter.CardTasarimTutucu> implements Serializable {


    private ItemClickListener itemClickListener;

    private Context context;
    private List<CuzdanModel.CuzdanModelKripto> cuzdanList;
    private List<Datum> mData;

    public VarliklarimKriptoAdapter(Context context, List<CuzdanModel.CuzdanModelKripto> cuzdanList, List<Datum> mData, ItemClickListener itemClickListener) {
        this.context = context;
        this.cuzdanList = cuzdanList;
        this.mData=mData;
        this.itemClickListener=itemClickListener;

    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuzdan_rc_tasarim, parent, false);
        return new CardTasarimTutucu(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

        CuzdanModel.CuzdanModelKripto cuzdan = cuzdanList.get(position);

        for(int i=0;i<500;i++){
            if(mData.get(i).getSymbol().equals(cuzdan.getCoin_adi())){

                holder.textViewCoinAdi.setText(cuzdan.getCoin_adi());
                holder.textViewCoinDegeriAdapter.setText(String.format("%.3f",mData.get(i).getQuote().getUSD().getPrice()));
                holder.textViewAlisFiyatiAdapter.setText(String.format("%.3f",cuzdan.getCoin_alis_fiyati()));
                holder.textViewCoinAdediAdapter.setText(String.format("%.3f",cuzdan.getCoin_alis_adeti()));

                float degisen_fiyat= (float) ((mData.get(i).getQuote().getUSD().getPrice())-(cuzdan.getCoin_alis_fiyati()));
                float x=(degisen_fiyat)/(cuzdan.getCoin_alis_fiyati());
                float degisim_yuzdesi=x*100;

                holder.textViewCoinDegisimAdapter.setText("% "+String.format("%.3f",degisim_yuzdesi));
            }

        }

    }

    @Override
    public int getItemCount() {

        return Math.min(cuzdanList.size(), mData.size());
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewCoinAdi, textViewCoinDegisimAdapter,
                textViewCoinAdediAdapter, textViewCoinDegeriAdapter, textViewAlisFiyatiAdapter;

        ItemClickListener itemClickListener;

        public CardTasarimTutucu(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            textViewCoinAdi = itemView.findViewById(R.id.textViewCoinAdiAdapter);
            textViewCoinDegisimAdapter = itemView.findViewById(R.id.textViewCoinDegisimAdapter);
            textViewCoinAdediAdapter = itemView.findViewById(R.id.textViewCoinAdediAdapter);
            textViewCoinDegeriAdapter = itemView.findViewById(R.id.textViewCoinDegeriAdapter);
            textViewAlisFiyatiAdapter = itemView.findViewById(R.id.textViewAlisFiyatiAdapter);

            this.itemClickListener=itemClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }


    public interface ItemClickListener {
        void onItemClick(int position);
    }

}
