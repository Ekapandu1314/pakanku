package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Resep;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/9/2016.
 */
public class ListResepAdapter extends BaseAdapter {

    public static final String TAG = "ListResepAdapter";

    private List<Resep> mItems;
    private LayoutInflater mInflater;

    public ListResepAdapter(Context context, List<Resep> listResep) {
        this.setItems(listResep);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Resep getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getIdresep() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_resep, parent, false);
            holder = new ViewHolder();
            holder.txtNama_bahan = (TextView) v.findViewById(R.id.nama_bahan_resep);
            holder.txtProporsi = (TextView) v.findViewById(R.id.proporsi_resep);
            holder.txtHarga_bahan = (TextView) v.findViewById(R.id.harga_resep);
            holder.txtHarga_kg = (TextView) v.findViewById(R.id.harga_bahan_resep);

            holder.txtNama_bahan.setTypeface(null, Typeface.BOLD);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Resep currentItem = getItem(position);
        if(currentItem != null) {

            //Log.d("Data : ", currentItem.getIdbahan());

            holder.txtNama_bahan.setText(currentItem.getNama_bahan());
            holder.txtProporsi.setText(currentItem.getProporsi());
            holder.txtHarga_bahan.setText(currentItem.getHarga_bahan());
            holder.txtHarga_kg.setText(currentItem.getHarga_kg());

        }

        if(currentItem.getJenis_bahan().equals("hijauan")) {


            v.setBackgroundResource(R.color.listHijauan);
            holder.txtNama_bahan.setTextColor(Color.parseColor("#ffffff"));
            holder.txtProporsi.setTextColor(Color.parseColor("#ffffff"));
            holder.txtHarga_bahan.setTextColor(Color.parseColor("#ffffff"));
            holder.txtHarga_kg.setTextColor(Color.parseColor("#ffffff"));

        }
        else if(currentItem.getJenis_bahan().equals("protein")) {

            v.setBackgroundResource(R.color.listProtein);
            holder.txtNama_bahan.setTextColor(Color.parseColor("#000000"));
            holder.txtProporsi.setTextColor(Color.parseColor("#000000"));
            holder.txtHarga_bahan.setTextColor(Color.parseColor("#000000"));
            holder.txtHarga_kg.setTextColor(Color.parseColor("#000000"));

        }
        else if(currentItem.getJenis_bahan().equals("energi")) {

            v.setBackgroundResource(R.color.listEnergi);
            holder.txtNama_bahan.setTextColor(Color.parseColor("#FFE817"));
            holder.txtProporsi.setTextColor(Color.parseColor("#FFE817"));
            holder.txtHarga_bahan.setTextColor(Color.parseColor("#FFE817"));
            holder.txtHarga_kg.setTextColor(Color.parseColor("#FFE817"));

        }

        return v;
    }

    public List<Resep> getItems() {
        return mItems;
    }

    public void setItems(List<Resep> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtNama_bahan;
        TextView txtProporsi;
        TextView txtHarga_bahan;
        TextView txtHarga_kg;
    }

}
