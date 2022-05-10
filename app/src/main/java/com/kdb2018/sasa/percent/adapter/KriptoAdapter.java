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
import com.kdb2018.sasa.percent.model.kriptoModel.Datum;

import java.io.Serializable;
import java.util.List;

public class KriptoAdapter extends RecyclerView.Adapter<KriptoAdapter.ViewHolder> implements Serializable {
    private List<Datum> mData;
    private ItemClickListener mClickListener;
    private  ItemClickListener itemClickListener;


    public KriptoAdapter(List<Datum> mData, ItemClickListener itemClickListener) {
        this.mData = mData;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.kripro_adapter_tasarim, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,itemClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Datum datum = mData.get(position);

        TextView name = holder.name;
        name.setText(datum.getSymbol());

        TextView price = holder.price;
        price.setText("$" + String.format("%.2f", datum.getQuote().getUSD().getPrice()));


        TextView perc = holder.percent;
        ImageView imageView = holder.imageView;


        if(datum.getQuote().getUSD().getPercentChange1h()<0){
            imageView.setImageResource(R.drawable.ic_arrow_down);

            perc.setText(String.format("%.2f", datum.getQuote().getUSD().getPercentChange1h()) + "%");
        }else if(datum.getQuote().getUSD().getPercentChange1h()>0){

            imageView.setImageResource(R.drawable.ic_arrow_up);
            perc.setText(" "+String.format("%.2f",datum.getQuote().getUSD().getPercentChange1h()) + "%");
        }else{
            imageView.setImageResource(R.drawable.ic_baseline);
            perc.setText(" "+String.format("%.2f",datum.getQuote().getUSD().getPercentChange1h()) + "%");
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView price;
        TextView percent;
        ItemClickListener itemClickListener;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_Name);
            price = itemView.findViewById(R.id.textView_Price);
            percent = itemView.findViewById(R.id.textView_Perc);
            imageView = itemView.findViewById(R.id.imageViewKripto);
            this.itemClickListener=itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public Datum getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(int position);
    }
    public void FilteredList(List<Datum> filteredList){

        mData= filteredList;
        notifyDataSetChanged();

    }
}