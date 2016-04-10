package com.android.mirzaadr.pakanku;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Adapter.ListBahanAdapter;
import com.android.mirzaadr.pakanku.Adapter.ListBahanHargaAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Dao.VersionDAO;
import com.android.mirzaadr.pakanku.Model.Bahan;

import java.util.List;

/**
 * Created by Mirzaadr on 4/1/2016.
 */

public class Info_harga extends Fragment {

    private ListView mListviewHarga;
    private TextView mTxtEmptyListBahan;

    private ListBahanHargaAdapter mAdapter;

    private List<Bahan> mListHarga;
    private BahanDAO mBahanDao;

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.harga_layout, container, false);

        mBahanDao = new BahanDAO(getActivity());
        // fill the listView
        mListHarga = mBahanDao.getAllBahan();

        initViews();

        mAdapter = new ListBahanHargaAdapter(getActivity(), mListHarga);
        mListviewHarga.setAdapter(mAdapter);

        return view;
    }

    private void initViews() {
        this.mListviewHarga = (ListView)view.findViewById(R.id.hargalist);
        //this.mTxtEmptyListBahan = (TextView)view.findViewById(R.id.table);
    }
}
