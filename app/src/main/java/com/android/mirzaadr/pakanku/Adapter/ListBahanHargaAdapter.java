package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/2/2016.
 */
public class ListBahanHargaAdapter extends BaseAdapter {

    public static final String TAG = "ListBahanHargaAdapter";

    private List<Bahan> mItems;
    private LayoutInflater mInflater;

    public ListBahanHargaAdapter(Context context, List<Bahan> listBahan) {
        this.setItems(listBahan);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Bahan getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getIdbahan() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_harga, parent, false);
            holder = new ViewHolder();
            holder.intIdBahan = (TextView) v.findViewById(R.id.notable);
            holder.txtNamaBahan = (TextView) v.findViewById(R.id.namatable);
            holder.txtHarga = (TextView) v.findViewById(R.id.hargatable);
            holder.intIdBahan.setVisibility(View.INVISIBLE);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Bahan currentItem = getItem(position);
        if(currentItem != null) {

            //Log.d("Data : ", currentItem.getIdbahan());

            holder.intIdBahan.setText(String.valueOf(currentItem.getIdbahan())+".");
            holder.txtNamaBahan.setText(currentItem.getNamaBahan());
            holder.txtHarga.setText("Rp. " + String.valueOf(currentItem.getHarga()));

        }

        if(currentItem.getKategori().equals("hijauan")) {


            v.setBackgroundResource(R.color.listHijauan);
            holder.txtNamaBahan.setTextColor(Color.parseColor("#ffffff"));
            holder.txtHarga.setTextColor(Color.parseColor("#ffffff"));

        }
        else if(currentItem.getKategori().equals("protein")) {

            v.setBackgroundResource(R.color.listProtein);
            holder.txtNamaBahan.setTextColor(Color.parseColor("#000000"));
            holder.txtHarga.setTextColor(Color.parseColor("#000000"));

        }
        else if(currentItem.getKategori().equals("energi")) {

            v.setBackgroundResource(R.color.listEnergi);
            holder.txtNamaBahan.setTextColor(Color.parseColor("#FFE817"));
            holder.txtHarga.setTextColor(Color.parseColor("#FFE817"));

        }

        return v;
    }

    public List<Bahan> getItems() {
        return mItems;
    }

    public void setItems(List<Bahan> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView intIdBahan;
        TextView txtNamaBahan;
        TextView txtHarga;

    }

}
