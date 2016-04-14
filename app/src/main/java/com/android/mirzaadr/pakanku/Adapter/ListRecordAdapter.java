package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Record;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/11/2016.
 */
public class ListRecordAdapter extends BaseAdapter {

    public static final String TAG = "ListRecordAdapter";

    private List<Record> mItems;
    private LayoutInflater mInflater;

    public ListRecordAdapter(Context context, List<Record> listBahan) {
        this.setItems(listBahan);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Record getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getIdrecord() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_record, parent, false);
            holder = new ViewHolder();
            holder.thumbHewan = (ImageView) v.findViewById(R.id.thumbnail);
            holder.thumbTujuan = (ImageView) v.findViewById(R.id.thumbnailTujuan);
            holder.txtNama = (TextView) v.findViewById(R.id.nama_record);
            holder.txtBobot = (TextView) v.findViewById(R.id.bobot_record);
            holder.txtJumlah = (TextView) v.findViewById(R.id.jumlah_record);
            holder.txtTanggal = (TextView) v.findViewById(R.id.tanggal_record);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }


        // fill row data
        final Record currentItem = getItem(position);
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
        return v;
    }

    public List<Record> getItems() {
        return mItems;
    }

    public void setItems(List<Record> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtNama;
        TextView txtJumlah;
        TextView txtBobot;
        TextView txtTanggal;
        ImageView thumbHewan;
        ImageView thumbTujuan;
    }
}
