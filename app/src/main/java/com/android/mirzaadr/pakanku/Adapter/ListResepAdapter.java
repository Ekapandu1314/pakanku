package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
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
            holder.txtProporsi.setText(currentItem.getProporsi() + "%");
            holder.txtHarga_bahan.setText("Rp. " + currentItem.getHarga_bahan());

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
    }

}
