package com.android.mirzaadr.pakanku.Fragment;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.mirzaadr.pakanku.Adapter.ListBahanHargaAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.MasukkanHarga;
import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Mirzaadr on 4/1/2016.
 */

public class InfoHarga extends Fragment {

    private ListView mListviewHijau, mListviewEnergi, mListviewProtein;



    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_harga, container, false);

        ListBahanHargaAdapter mAdapterHijauan;
        ListBahanHargaAdapter mAdapterEnergi;
        ListBahanHargaAdapter mAdapterProtein;

        List<Bahan> mListHargaHijauan;
        List<Bahan> mListHargaEnergi;
        List<Bahan> mListHargaProtein;

        BahanDAO mBahanDao;

        mBahanDao = new BahanDAO(getActivity());
        // fill the listView
        mListHargaHijauan = mBahanDao.getAllBahanByKategori("hijauan");
        mListHargaEnergi = mBahanDao.getAllBahanByKategori("energi");
        mListHargaProtein = mBahanDao.getAllBahanByKategori("protein");

        initViews();

        mAdapterHijauan = new ListBahanHargaAdapter(getActivity(), mListHargaHijauan);
        mAdapterEnergi = new ListBahanHargaAdapter(getActivity(), mListHargaEnergi);
        mAdapterProtein = new ListBahanHargaAdapter(getActivity(), mListHargaProtein);
        mListviewHijau.setAdapter(mAdapterHijauan);
        mListviewEnergi.setAdapter(mAdapterEnergi);
        mListviewProtein.setAdapter(mAdapterProtein);

        Utility.setListViewHeightBasedOnChildren(mListviewHijau);
        Utility.setListViewHeightBasedOnChildren(mListviewEnergi);
        Utility.setListViewHeightBasedOnChildren(mListviewProtein);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MasukkanHarga.class);
                startActivity(intent);}
        });

        return view;
    }

    private void initViews() {
        this.mListviewHijau = (ListView)view.findViewById(R.id.hargalist);
        this.mListviewEnergi = (ListView)view.findViewById(R.id.hargalistenergi);
        this.mListviewProtein = (ListView)view.findViewById(R.id.hargalistprotein);
        //this.mTxtEmptyListBahan = (TextView)view.findViewById(R.id.table);
    }

    //Test scrollview custom
    public static class Utility {
        public static void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }
    }
}
