package com.android.mirzaadr.pakanku;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
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

    private ListView mListviewHijau, mListviewEnergi, mListviewProtein;

    private ListBahanHargaAdapter mAdapterHijauan;
    private ListBahanHargaAdapter mAdapterEnergi;
    private ListBahanHargaAdapter mAdapterProtein;

    private List<Bahan> mListHargaHijauan;
    private List<Bahan> mListHargaEnergi;
    private List<Bahan> mListHargaProtein;

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
                Intent intent = new Intent(getActivity(), InputHarga.class);
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
