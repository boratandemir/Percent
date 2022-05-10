package com.kdb2018.sasa.percent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kdb2018.sasa.percent.R;
import com.kdb2018.sasa.percent.model.hisseModel.HisseModel;

import java.io.Serializable;
import java.util.List;

public class HisseAdapter extends RecyclerView.Adapter<HisseAdapter.ViewHolder>implements Serializable {
    private List<HisseModel> mData;
    private ItemClickListener itemClickListener;

    public HisseAdapter(List<HisseModel> mData,ItemClickListener itemClickListener) {
        this.mData = mData;
        this.itemClickListener=itemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.hisse_adapter_tasarim, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,itemClickListener);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HisseModel datum =mData.get(position);
        if(datum.equals(null)){
            System.out.println("hatalar");

        }else{

            ImageView imageView=holder.imageView;

            TextView name = holder.textView_Symbol;
            name.setText(datum.getSymbol());

            TextView price = holder.textView_Hisse_Percent;
            price.setText(String.valueOf(datum.getClose()));

            TextView perc = holder.textView_Open;

            if(Float.valueOf(datum.getPercentChange())<0){
                imageView.setImageResource(R.drawable.ic_arrow_down);
                perc.setText(String.format("%.6s",datum.getChange())+"%");

            }else if (Float.valueOf(datum.getPercentChange())>0){
                imageView.setImageResource(R.drawable.ic_arrow_up);
                perc.setText(" "+String.format("%.5s",datum.getChange())+"%");

            }else{
                imageView.setImageResource(R.drawable.ic_baseline);
                perc.setText(" "+String.format("%.5s",datum.getChange())+"%");
            }
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView_Symbol;
        TextView textView_Open;
        TextView textView_Hisse_Percent;
        ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            textView_Symbol=itemView.findViewById(R.id.textView_Symbol);
            textView_Open=itemView.findViewById(R.id.textView_Open);
            textView_Hisse_Percent=itemView.findViewById(R.id.textView_Hisse_Percent);
            this.itemClickListener=itemClickListener;
            imageView = itemView.findViewById(R.id.imageViewHisse);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public HisseModel getItem(int id) {
        return mData.get(id);
    }
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
