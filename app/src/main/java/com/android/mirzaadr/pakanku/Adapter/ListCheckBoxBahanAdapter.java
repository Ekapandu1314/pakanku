package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/4/2016.
 */
public class ListCheckBoxBahanAdapter extends BaseAdapter {

    public static final String TAG = "ListCheckBoxBahanAdapter";

    private List<Bahan> mItems;
    private LayoutInflater mInflater;

    public ListCheckBoxBahanAdapter(Context context, List<Bahan> listBahan) {
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
        final ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.table_layout, parent, false);
            holder = new ViewHolder();
            holder.txtKategori = (TextView) v.findViewById(R.id.table2);
            holder.txtNamaBahan = (TextView) v.findViewById(R.id.table);
            holder.chckBahan = (CheckBox) v.findViewById(R.id.checkBox);

            holder.txtNamaBahan.setTypeface(null, Typeface.BOLD);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }



        // fill row data
        final Bahan currentItem = getItem(position);
        if(currentItem != null) {

            holder.chckBahan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) holder.chckBahan;
                    Bahan bahanx = (Bahan) cb.getTag();
                    Toast.makeText(v.getContext(),
                            "Clicked on Checkbox: " + currentItem.getNamaBahan() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_SHORT).show();
                    currentItem.setSelected(cb.isChecked());
                }
            });
            //Log.d("Data : ", currentItem.getIdbahan());

            //holder.intIdBahan.setText(String.valueOf(currentItem.getIdbahan()));
            holder.txtNamaBahan.setText(currentItem.getNamaBahan());
            holder.txtKategori.setText("Rp. " + String.valueOf(currentItem.getHarga()));
            holder.chckBahan.setChecked(currentItem.isSelected());

            if(currentItem.getKategori().equals("hijauan")) {


                v.setBackgroundResource(R.color.listHijauan);
                holder.txtNamaBahan.setTextColor(Color.parseColor("#ffffff"));
                holder.txtKategori.setTextColor(Color.parseColor("#ffffff"));

            }
            else if(currentItem.getKategori().equals("protein")) {

                v.setBackgroundResource(R.color.listProtein);
                holder.txtNamaBahan.setTextColor(Color.parseColor("#000000"));
                holder.txtKategori.setTextColor(Color.parseColor("#000000"));

            }
            else if(currentItem.getKategori().equals("energi")) {

                v.setBackgroundResource(R.color.listEnergi);
                holder.txtNamaBahan.setTextColor(Color.parseColor("#FFE817"));
                holder.txtKategori.setTextColor(Color.parseColor("#FFE817"));

            }



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
        TextView txtKategori;
        TextView txtNamaBahan;
        CheckBox chckBahan;

    }

}
