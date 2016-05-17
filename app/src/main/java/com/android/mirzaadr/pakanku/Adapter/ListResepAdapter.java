package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.Model.Resep;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/9/2016.
 */
public class ListResepAdapter extends RecyclerView.Adapter<ListResepAdapter.MyViewHolder> {

    private List<Resep> mItems;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama_bahan;
        TextView txtProporsi;
        TextView txtHarga_bahan;
        TextView txtHarga_kg;

        public MyViewHolder(View view) {
            super(view);
            txtNama_bahan = (TextView) view.findViewById(R.id.nama_bahan_resep);
            txtProporsi = (TextView) view.findViewById(R.id.proporsi_resep);
            txtHarga_bahan = (TextView) view.findViewById(R.id.harga_resep);
            txtHarga_kg = (TextView) view.findViewById(R.id.harga_bahan_resep);

        }
    }


    public ListResepAdapter(List<Resep> moviesList) {
        this.mItems = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_resep, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Resep currentItem = mItems.get(position);
        if(currentItem != null) {

            holder.txtNama_bahan.setText(currentItem.getNama_bahan());
            holder.txtProporsi.setText(currentItem.getProporsi());
            holder.txtHarga_bahan.setText(currentItem.getHarga_bahan());
            holder.txtHarga_kg.setText(currentItem.getHarga_kg());

            holder.txtNama_bahan.setTypeface(null, Typeface.BOLD);

        }

        if(currentItem.getJenis_bahan().equals("hijauan")) {

            holder.txtNama_bahan.setTextColor(Color.parseColor("#ffffff"));
            holder.txtProporsi.setTextColor(Color.parseColor("#ffffff"));
            holder.txtHarga_bahan.setTextColor(Color.parseColor("#ffffff"));
            holder.txtHarga_kg.setTextColor(Color.parseColor("#ffffff"));

        }
        else if(currentItem.getJenis_bahan().equals("protein")) {

            holder.txtNama_bahan.setTextColor(Color.parseColor("#000000"));
            holder.txtProporsi.setTextColor(Color.parseColor("#000000"));
            holder.txtHarga_bahan.setTextColor(Color.parseColor("#000000"));
            holder.txtHarga_kg.setTextColor(Color.parseColor("#000000"));

        }
        else if(currentItem.getJenis_bahan().equals("energi")) {

            holder.txtNama_bahan.setTextColor(Color.parseColor("#FFE817"));
            holder.txtProporsi.setTextColor(Color.parseColor("#FFE817"));
            holder.txtHarga_bahan.setTextColor(Color.parseColor("#FFE817"));
            holder.txtHarga_kg.setTextColor(Color.parseColor("#FFE817"));

        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
