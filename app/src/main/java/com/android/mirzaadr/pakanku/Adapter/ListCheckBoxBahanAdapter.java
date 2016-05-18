package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/4/2016.
 */
public class ListCheckBoxBahanAdapter extends RecyclerView.Adapter<ListCheckBoxBahanAdapter.MyViewHolder> {

    private List<Bahan> mItems;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtKategori;
        TextView txtNamaBahan;
        CheckBox chckBahan;
        RelativeLayout kanan;

        public MyViewHolder(View view) {
            super(view);
            txtKategori = (TextView) view.findViewById(R.id.table2);
            txtNamaBahan = (TextView) view.findViewById(R.id.table);
            chckBahan = (CheckBox) view.findViewById(R.id.checkBox);
            kanan = (RelativeLayout) view.findViewById(R.id.kanan);


        }
    }


    public ListCheckBoxBahanAdapter(List<Bahan> moviesList) {
        this.mItems = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pilih_bahan, parent, false);

        return new MyViewHolder(itemView);
    }

    public List<Bahan> getmItems() {
        return mItems;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Bahan currentItem = mItems.get(position);
        if(currentItem != null) {

            holder.chckBahan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) holder.chckBahan;
                    Bahan bahanx = (Bahan) cb.getTag();
                    currentItem.setSelected(cb.isChecked());
                }
            });

            holder.kanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), currentItem.getNamaBahan() + " Selected!", Toast.LENGTH_SHORT).show();
                }
            });

            //holder.intIdBahan.setText(String.valueOf(currentItem.getIdbahan()));
            holder.txtNamaBahan.setText(currentItem.getNamaBahan());
            holder.txtKategori.setText("Rp. " + String.valueOf(currentItem.getHarga()));
            holder.chckBahan.setChecked(currentItem.isSelected());
            holder.txtNamaBahan.setTypeface(null, Typeface.BOLD);

            if(currentItem.getKategori().equals("hijauan")) {

                holder.txtNamaBahan.setTextColor(Color.parseColor("#ffffff"));
                holder.txtKategori.setTextColor(Color.parseColor("#ffffff"));

            }
            else if(currentItem.getKategori().equals("protein")) {

                holder.txtNamaBahan.setTextColor(Color.parseColor("#000000"));
                holder.txtKategori.setTextColor(Color.parseColor("#000000"));

            }
            else if(currentItem.getKategori().equals("energi")) {

                holder.txtNamaBahan.setTextColor(Color.parseColor("#FFE817"));
                holder.txtKategori.setTextColor(Color.parseColor("#FFE817"));

            }



        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
