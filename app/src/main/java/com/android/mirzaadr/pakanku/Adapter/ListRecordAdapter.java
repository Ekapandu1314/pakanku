package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.Model.Record;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/11/2016.
 */
public class ListRecordAdapter extends RecyclerView.Adapter<ListRecordAdapter.MyViewHolder> {

    private List<Record> mItems;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama;
        TextView txtJumlah;
        TextView txtBobot;
        TextView txtTanggal;
        ImageView thumbHewan;
        ImageView thumbTujuan;

        public MyViewHolder(View view) {
            super(view);
            thumbHewan = (ImageView) view.findViewById(R.id.thumbnail);
            thumbTujuan = (ImageView) view.findViewById(R.id.thumbnailTujuan);
            txtNama = (TextView) view.findViewById(R.id.nama_record);
            txtBobot = (TextView) view.findViewById(R.id.bobot_record);
            txtJumlah = (TextView) view.findViewById(R.id.jumlah_record);
            txtTanggal = (TextView) view.findViewById(R.id.tanggal_record);
        }
    }

    public void setmItems(List<Record> mItems) {
        this.mItems = mItems;
    }

    public ListRecordAdapter(List<Record> moviesList) {
        this.mItems = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_record, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Record currentItem = mItems.get(position);
        if(currentItem != null) {

            holder.txtNama.setText(currentItem.getNama_record());
            holder.txtBobot.setText(String.valueOf(currentItem.getRberat1()));
            holder.txtJumlah.setText(String.valueOf(currentItem.getRjternak()));
            holder.txtTanggal.setText(currentItem.getRtanggal());

            if(currentItem.getRhewan().equals("Sapi")){

                holder.thumbHewan.setImageResource(R.drawable.sapi);

            }
            else if (currentItem.getRhewan().equals("Kambing")){

                holder.thumbHewan.setImageResource(R.drawable.kambing);

            }
            else if (currentItem.getRhewan().equals("Ayam")){

                holder.thumbHewan.setImageResource(R.drawable.ayam);

            }
            else if (currentItem.getRhewan().equals("Domba")){

                holder.thumbHewan.setImageResource(R.drawable.domba);

            }
            else{

                holder.thumbHewan.setImageResource(R.drawable.fail);

            }

            if (currentItem.getRtujuan().equals("Potong")) {

                holder.thumbTujuan.setImageResource(R.drawable.ic_logo_potong);

            }
            else if(currentItem.getRtujuan().equals("Perah")) {

                holder.thumbTujuan.setImageResource(R.drawable.ic_logo_perah);

            }
            else if(currentItem.getRtujuan().equals("Petelur")) {

                holder.thumbTujuan.setImageResource(R.drawable.ic_logo_petelur);

            }
            else {
                holder.thumbTujuan.setImageResource(R.drawable.fail);

            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
