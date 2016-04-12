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
    Context context;

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
            holder.thumbRecord = (ImageView) v.findViewById(R.id.thumbnail);
            holder.txtNama = (TextView) v.findViewById(R.id.nama_record);
            holder.txtHewan = (TextView) v.findViewById(R.id.jenis_hewan);
            holder.txtTujuan = (TextView) v.findViewById(R.id.tujuan_hewan);
            holder.txtTanggal = (TextView) v.findViewById(R.id.tanggal_record);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }


        // fill row data
        final Record currentItem = getItem(position);
        if(currentItem != null) {

            //Log.d("Data : ", currentItem.getIdbahan());


            //holder.intIdBahan.setText(String.valueOf(currentItem.getIdbahan()))
            holder.txtNama.setText(currentItem.getNama_record());
            holder.txtHewan.setText(currentItem.getRhewan());
            holder.txtTujuan.setText(currentItem.getRtujuan());
            holder.txtTanggal.setText(currentItem.getRtanggal());

            if(currentItem.getRhewan().equals("Sapi")){

                //Drawable myDrawable = context.getResources().getDrawable(R.drawable.fail);

                holder.thumbRecord.setImageResource(R.drawable.sapi);

                //holder.thumbRecord.setImageDrawable(myDrawable);

            }
            else if (currentItem.getRhewan().equals("Kambing")){

                //Drawable myDrawable = context.getResources().getDrawable(R.drawable.fail);

                //holder.thumbRecord.setImageDrawable(myDrawable);

                holder.thumbRecord.setImageResource(R.drawable.kambing);

            }
            else if (currentItem.getRhewan().equals("Ayam")){

                //Drawable myDrawable = context.getResources().getDrawable(R.drawable.fail);

                //holder.thumbRecord.setImageDrawable(myDrawable);

                holder.thumbRecord.setImageResource(R.drawable.ayam);

            }
            else{

                //Drawable myDrawable = context.getResources().getDrawable(R.drawable.fail);

                //holder.thumbRecord.setImageDrawable(myDrawable);

                holder.thumbRecord.setImageResource(R.drawable.fail);

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
        TextView txtHewan;
        TextView txtTujuan;
        TextView txtTanggal;
        ImageView thumbRecord;

    }
}
