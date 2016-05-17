package com.android.mirzaadr.pakanku.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListBahanHargaAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.MasukkanHarga;
import com.android.mirzaadr.pakanku.Decoration.DividerItemDecoration;
import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

/**
 * Created by Mirzaadr on 4/1/2016.
 */

public class InfoHarga extends Fragment implements ViewTreeObserver.OnScrollChangedListener {

    private RecyclerView mListviewHijau, mListviewEnergi, mListviewProtein;

    List<Bahan> mListHargaHijauan;
    List<Bahan> mListHargaEnergi;
    List<Bahan> mListHargaProtein;

    NestedScrollView scroll_harga;

    private float mActionBarHeight;
    private AppBarLayout mActionBar;

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


        mActionBar = (AppBarLayout)getActivity().findViewById(R.id.appbar);
        final TypedArray styledAttributes = getActivity().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize });
        mActionBarHeight = styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
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

        return view;
    }

    private void initViews() {
        mListviewHijau = (RecyclerView)view.findViewById(R.id.hargalist);
        mListviewEnergi = (RecyclerView)view.findViewById(R.id.hargalistenergi);
        mListviewProtein = (RecyclerView)view.findViewById(R.id.hargalistprotein);
        scroll_harga = (NestedScrollView)view.findViewById(R.id.scroll_harga);

        mListviewHijau.setNestedScrollingEnabled(false);
        mListviewEnergi.setNestedScrollingEnabled(false);
        mListviewProtein.setNestedScrollingEnabled(false);

        (view.findViewById(R.id.scroll_harga)).getViewTreeObserver().addOnScrollChangedListener(this);


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

    @Override
    public void onScrollChanged() {



        float y = (view.findViewById(R.id.scroll_harga)).getScrollY();
        float yy = (view.findViewById(R.id.scroll_harga)).getOverScrollMode();
        if (y >= mActionBarHeight + view.getHeight() && mActionBar.isShown()) {
            mActionBar.setExpanded(false, true);
        } else if ( y==0 && !mActionBar.isShown()) {
            mActionBar.setExpanded(true, true);
        }
        Log.d("Scroll", String.valueOf(y) + " " + mActionBarHeight + " " + String.valueOf(yy));
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
