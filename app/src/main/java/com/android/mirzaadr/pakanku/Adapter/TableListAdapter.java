package com.android.mirzaadr.pakanku.Adapter;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mirzaadr on 4/2/2016.
 */
public class TableListAdapter extends BaseAdapter{

    private ArrayList<Bahan> bahanlist;
    private LayoutInflater mInflater;

    public TableListAdapter(Context context, ArrayList<Bahan> listBahan) {
        this.bahanlist = listBahan;
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

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        ViewHolder dataholder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.table_layout, null);
            dataholder = new ViewHolder();
            dataholder.Nama= (TextView) convertView.findViewById(R.id.table);
            dataholder.Harga= (TextView) convertView.findViewById(R.id.table2);
            //dataholder.Nutrisi= (TextView) convertView.findViewById(R.id.table3);
            convertView.setTag(dataholder);
        } else {
            dataholder = (ViewHolder) convertView.getTag();
        }


        Bahan currentItem = getItem(position);
        if(currentItem != null) {
            dataholder.Nama.setText(currentItem.getNama_bahan());
            dataholder.Harga.setText("Rp. " + String.valueOf(currentItem.getHarga()));
            //dataholder.Nutrisi.setText("Rp. " + String.valueOf(currentItem.getHarga()));
        }

        return convertView;
    }


    public ArrayList<Bahan> getItems() {
        return bahanlist;
    }

    public void setItems(ArrayList<Bahan> mItems) {
        this.bahanlist = mItems;
    }

    static class ViewHolder {
        TextView Nama;
        TextView Harga;
        TextView Nutrisi;

    }

}
