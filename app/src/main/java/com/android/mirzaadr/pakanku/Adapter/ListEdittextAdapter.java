package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/11/2016.
 */
public class ListEdittextAdapter extends BaseAdapter {

    public static final String TAG = "ListEdittextAdapter";

    private List<Bahan> mItems;
    private LayoutInflater mInflater;

    public ListEdittextAdapter(Context context, List<Bahan> listBahan) {
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
            v = mInflater.inflate(R.layout.list_item_edittext, parent, false);
            holder = new ViewHolder();
            holder.txtBahanEdit = (TextView) v.findViewById(R.id.bahan_edittext);
            holder.editHarga = (EditText) v.findViewById(R.id.editHarga);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }



        // fill row data
        final Bahan currentItem = getItem(position);
        if(currentItem != null) {


            holder.editHarga.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    EditText cb = (EditText) holder.editHarga;

                    Bahan bahanx = (Bahan) cb.getTag();

                    if(cb.getText().toString().trim().length() > 1){

                        currentItem.setHarga_baru(cb.getText().toString());

                    }
                    else {

                        currentItem.setHarga_baru("-");


                    }

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    EditText cb = (EditText) holder.editHarga;

                    Bahan bahanx = (Bahan) cb.getTag();

                    if(cb.getText().toString().trim().length() > 1){

                        currentItem.setHarga_baru(cb.getText().toString());

                    }
                    else {

                        currentItem.setHarga_baru("-");

                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                    EditText cb = (EditText) holder.editHarga;

                    Bahan bahanx = (Bahan) cb.getTag();

                    if(cb.getText().toString().trim().length() > 1){

                        currentItem.setHarga_baru(cb.getText().toString());

                    }
                    else {

                        currentItem.setHarga_baru("-");

                    }

                }
            });

            //Log.d("Data : ", currentItem.getIdbahan());
            holder.editHarga.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {





                }
            });

            //holder.intIdBahan.setText(String.valueOf(currentItem.getIdbahan()));
            holder.txtBahanEdit.setText(currentItem.getNamaBahan());
            holder.editHarga.setText(currentItem.getHarga_baru());


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
        TextView txtBahanEdit;
        EditText editHarga;

    }

}
