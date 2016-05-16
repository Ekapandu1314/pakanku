package com.android.mirzaadr.pakanku.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 5/15/2016.
 */

public class ListBahanHargaAdapter extends RecyclerView.Adapter<ListBahanHargaAdapter.MyViewHolder> {

    private List<Bahan> mItems;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView intIdBahan;
        TextView txtNamaBahan;
        TextView txtHarga;

        public MyViewHolder(View view) {
            super(view);
            intIdBahan = (TextView) view.findViewById(R.id.notable);
            txtNamaBahan = (TextView) view.findViewById(R.id.namatable);
            txtHarga = (TextView) view.findViewById(R.id.hargatable);
            intIdBahan.setVisibility(View.INVISIBLE);
        }
    }


    public ListBahanHargaAdapter(List<Bahan> moviesList) {
        this.mItems = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_harga, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bahan currentItem = mItems.get(position);
        //if(currentItem != null) {

        //Log.d("Data : ", currentItem.getIdbahan());

        holder.intIdBahan.setText(String.valueOf(currentItem.getIdbahan())+".");
        holder.txtNamaBahan.setText(currentItem.getNamaBahan());
        holder.txtHarga.setText("Rp. " + String.valueOf(currentItem.getHarga()));

        //}

        if(currentItem.getKategori().equals("hijauan")) {


            //v.setBackgroundResource(R.color.listHijauan);
            holder.txtNamaBahan.setTextColor(Color.parseColor("#ffffff"));
            holder.txtHarga.setTextColor(Color.parseColor("#ffffff"));

        }
        else if(currentItem.getKategori().equals("protein")) {

            //v.setBackgroundResource(R.color.listProtein);
            holder.txtNamaBahan.setTextColor(Color.parseColor("#000000"));
            holder.txtHarga.setTextColor(Color.parseColor("#000000"));

        }
        else if(currentItem.getKategori().equals("energi")) {

            //v.setBackgroundResource(R.color.listEnergi);
            holder.txtNamaBahan.setTextColor(Color.parseColor("#FFE817"));
            holder.txtHarga.setTextColor(Color.parseColor("#FFE817"));

        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
