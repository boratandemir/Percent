package com.kdb2018.sasa.percent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kdb2018.sasa.percent.model.hisseModel.Liste;
import com.kdb2018.sasa.percent.R;
import com.kdb2018.sasa.percent.model.CuzdanModel;

import java.io.Serializable;
import java.util.List;

public class VarliklarımHisseAdapter extends RecyclerView.Adapter<VarliklarımHisseAdapter.CardTasarimTutucu> implements Serializable {


    private Context context;
    private List<CuzdanModel.CuzdanModelHisse> cuzdanList;
    private Liste mdata;
    int mdata_size;
    private  ItemClickListener itemClickListener;



    public VarliklarımHisseAdapter(Context context, List<CuzdanModel.CuzdanModelHisse> cuzdanList, Liste mdata,int mdata_size,ItemClickListener itemClickListener) {
        this.context = context;
        this.cuzdanList = cuzdanList;
        this.mdata = mdata;
        this.mdata_size=mdata_size;
        this.itemClickListener=itemClickListener;

    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuzdan_rc_tasarim_hisse,parent,false);
        return new CardTasarimTutucu(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

       String a = mdata.Esle(cuzdanList.get(position));
       float guncel_fiyat = Float.parseFloat(a);

        CuzdanModel.CuzdanModelHisse cuzdan = cuzdanList.get(position);
        holder.textViewHisseAdi.setText(cuzdan.getHisse_adi());
        holder.textViewHisseDegeriAdapter.setText(a);
        holder.textViewHisseAlisFiyatiAdapter.setText(String.valueOf(cuzdan.getHisse_alis_fiyati()));
        holder.textViewHisseAdediAdapter.setText(String.valueOf(cuzdan.getHisse_alis_adeti()));

        float degisen_fiyat= (float) (guncel_fiyat) - (cuzdan.getHisse_alis_fiyati());
        float x=(degisen_fiyat)/(cuzdan.getHisse_alis_fiyati());
        float degisim_yuzdesi=x*100;
        holder.textViewHisseDegisimAdapter.setText("%"+String.format("%.3f",degisim_yuzdesi));

    }


    @Override
    public int getItemCount() {

        return Math.min(cuzdanList.size(),mdata_size);
    }

    public  class CardTasarimTutucu extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView textViewHisseAdi, textViewHisseDegisimAdapter,
                textViewHisseAdediAdapter, textViewHisseDegeriAdapter, textViewHisseAlisFiyatiAdapter;

        ItemClickListener itemClickListener;

        public CardTasarimTutucu(@NonNull View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            textViewHisseAdi = itemView.findViewById(R.id.textViewHisseAdiAdapter);
            textViewHisseDegisimAdapter = itemView.findViewById(R.id.textViewHisseDegisimAdapter);
            textViewHisseAdediAdapter = itemView.findViewById(R.id.textViewHisseAdediAdapter);
            textViewHisseDegeriAdapter = itemView.findViewById(R.id.textViewHisseDegeriAdapter);
            textViewHisseAlisFiyatiAdapter = itemView.findViewById(R.id.textViewHisseAlisFiyatiAdapter);
            this.itemClickListener=itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick2(getAdapterPosition());
        }

    }
    public interface  ItemClickListener{
        void onItemClick2(int position);
    }
}
