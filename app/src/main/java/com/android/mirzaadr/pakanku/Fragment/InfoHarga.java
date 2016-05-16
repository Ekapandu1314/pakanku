package com.android.mirzaadr.pakanku.Fragment;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListBahanHargaAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Activity.MasukkanHarga;
import com.android.mirzaadr.pakanku.Decoration.DividerItemDecoration;
import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Mirzaadr on 4/1/2016.
 */

public class InfoHarga extends Fragment {

    private RecyclerView mListviewHijau, mListviewEnergi, mListviewProtein;

    List<Bahan> mListHargaHijauan;
    List<Bahan> mListHargaEnergi;
    List<Bahan> mListHargaProtein;

    NestedScrollView scrollharga;

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

        BahanDAO mBahanDao;

        mBahanDao = new BahanDAO(getActivity());
        // fill the listView
        mListHargaHijauan = mBahanDao.getAllBahanByKategori("hijauan");
        mListHargaEnergi = mBahanDao.getAllBahanByKategori("energi");
        mListHargaProtein = mBahanDao.getAllBahanByKategori("protein");

        initViews();

        mAdapterHijauan = new ListBahanHargaAdapter(mListHargaHijauan);
        mAdapterEnergi = new ListBahanHargaAdapter(mListHargaEnergi);
        mAdapterProtein = new ListBahanHargaAdapter(mListHargaProtein);
        mListviewHijau.setAdapter(mAdapterHijauan);
        mListviewEnergi.setAdapter(mAdapterEnergi);
        mListviewProtein.setAdapter(mAdapterProtein);

        mAdapterHijauan.notifyDataSetChanged();
        mAdapterEnergi.notifyDataSetChanged();
        mAdapterProtein.notifyDataSetChanged();

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
        mListviewHijau = (RecyclerView)view.findViewById(R.id.hargalist);
        mListviewEnergi = (RecyclerView)view.findViewById(R.id.hargalistenergi);
        mListviewProtein = (RecyclerView)view.findViewById(R.id.hargalistprotein);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity());

        mListviewHijau.setLayoutManager(mLayoutManager1);
        mListviewHijau.setItemAnimator(new DefaultItemAnimator());
        mListviewHijau.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        mListviewHijau.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mListviewHijau, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bahan bahan1 = mListHargaHijauan.get(position);
                Toast.makeText(getActivity(), bahan1.getNamaBahan() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mListviewEnergi.setLayoutManager(mLayoutManager2);
        mListviewEnergi.setItemAnimator(new DefaultItemAnimator());
        mListviewEnergi.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mListviewEnergi.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mListviewEnergi, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bahan bahan2 = mListHargaEnergi.get(position);
                Toast.makeText(getActivity(), bahan2.getNamaBahan() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mListviewProtein.setLayoutManager(mLayoutManager3);
        mListviewProtein.setItemAnimator(new DefaultItemAnimator());
        mListviewProtein.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mListviewProtein.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mListviewProtein, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bahan bahan3 = mListHargaProtein.get(position);
                Toast.makeText(getActivity(), bahan3.getNamaBahan() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private InfoHarga.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final InfoHarga.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
