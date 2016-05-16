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
import android.widget.EditText;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/11/2016.
 */
public class ListEdittextAdapter extends RecyclerView.Adapter<ListEdittextAdapter.MyViewHolder> {

private List<Bahan> mItems;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView txtBahanEdit;
    EditText editHarga;

    public MyViewHolder(View view) {
        super(view);
        txtBahanEdit = (TextView) view.findViewById(R.id.bahan_edittext);
        editHarga = (EditText) view.findViewById(R.id.editHarga);
    }
}


    public ListEdittextAdapter(List<Bahan> moviesList) {
        this.mItems = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_input_harga, parent, false);

        return new MyViewHolder(itemView);
    }

    public List<Bahan> getmItems() {
        return mItems;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Bahan currentItem = mItems.get(position);

        if(currentItem != null) {

            holder.txtBahanEdit.setText(currentItem.getNamaBahan());
            holder.editHarga.setText(currentItem.getHarga_baru());

            holder.txtBahanEdit.setTypeface(null, Typeface.BOLD);

            holder.editHarga.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    EditText cb = (EditText) holder.editHarga;

                    Bahan bahanx = (Bahan) cb.getTag();

                    if(cb.getText().toString().trim().length() > 0){

                        if(cb.getText().toString().equals("0")) {

                            currentItem.setHarga_baru(cb.getText().toString());

                        }
                        else if (!cb.getText().toString().equals("0")){

                            currentItem.setHarga_baru(cb.getText().toString());

                        }
                    }
                    else {

                        currentItem.setHarga_baru("-");


                    }

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    EditText cb = (EditText) holder.editHarga;

                    Bahan bahanx = (Bahan) cb.getTag();

                    if(cb.getText().toString().trim().length() > 0){

                        if(cb.getText().toString().equals("0")) {

                            currentItem.setHarga_baru(cb.getText().toString());

                        }
                        else if (!cb.getText().toString().equals("0")){

                            currentItem.setHarga_baru(cb.getText().toString());

                        }



                    }
                    else {

                        currentItem.setHarga_baru("-");

                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                    EditText cb = (EditText) holder.editHarga;

                    Bahan bahanx = (Bahan) cb.getTag();

                    if(cb.getText().toString().trim().length() > 0){
                        if(cb.getText().toString().equals("0")) {
                            currentItem.setHarga_baru(cb.getText().toString());
                        }
                        else if (!cb.getText().toString().equals("0")){
                            currentItem.setHarga_baru(cb.getText().toString());
                        }
                    }
                    else {
                        currentItem.setHarga_baru("-");
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
