package com.android.mirzaadr.pakanku.Fragment;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListCheckBoxBahanAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Dao.HewanDAO;
import com.android.mirzaadr.pakanku.Dao.VersionDAO;
import com.android.mirzaadr.pakanku.Decoration.DividerItemDecoration;
import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;
import com.android.mirzaadr.pakanku.Interface.BahanCommunicator;
import com.android.mirzaadr.pakanku.Activity.PilihBahan;

import java.util.List;

public class BahanEnergi extends Fragment implements BahanCommunicator.FragmentCommunicator {

    View view;

    private BahanCommunicator.ActivityCommunicator activityCommunicator;
    private BahanCommunicator.ActivityCommunicator fragmentCommunicator;

    public Context context;

    private ListCheckBoxBahanAdapter mAdapter;
    private List<Bahan> mListBahan;
    private VersionDAO mVersionDao;
    private HewanDAO mHewanDao;
    private TextView mTxtEmptyListBahan;
    private RecyclerView bahanListView;

    private BahanDAO mBahanDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bahan_energi, container, false);

        mBahanDao = new BahanDAO(getActivity());
        mVersionDao = new VersionDAO(getActivity());
        mHewanDao = new HewanDAO(getActivity());
        // fill the listView
        mListBahan = mBahanDao.getAllBahanByKategori("energi");

        initViews();

        mAdapter = new ListCheckBoxBahanAdapter(mListBahan);
        bahanListView.setAdapter(mAdapter);

        //Utility.setListViewHeightBasedOnChildren(bahanListView);

        return view;
    }

    private void initViews() {
        this.bahanListView = (RecyclerView) view.findViewById(R.id.listEnergi);
        this.mTxtEmptyListBahan = (TextView) view.findViewById(R.id.table);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        bahanListView.setLayoutManager(mLayoutManager);
        bahanListView.setItemAnimator(new DefaultItemAnimator());
        bahanListView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        bahanListView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), bahanListView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bahan bahan1 = mListBahan.get(position);
                Toast.makeText(getActivity(), bahan1.getNamaBahan() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        context = getActivity();
        activityCommunicator =(BahanCommunicator.ActivityCommunicator)context;
        ((PilihBahan)context).fragmentCommunicator1 = this;
    }

    @Override
    public void passDataToFragment(String haha){

        String bahanid = null;

        if(haha.toString().equals("energi")){


            StringBuffer responseText = new StringBuffer();

            List<Bahan> bahanxxxx = mAdapter.getmItems();
            for (int i = 0; i < bahanxxxx.size(); i++) {
                Bahan bahanxv = bahanxxxx.get(i);
                if (bahanxv.isSelected()) {
                    responseText.append("-" + bahanxv.getIdbahan());

                }

            }

            bahanid = responseText.toString();

            if(bahanid.equals("")) {

                TabLayout layout = (TabLayout) getActivity().findViewById(R.id.tabsbahan);

                Toast.makeText(getActivity(), "Bahan sumber energi tidak boleh kosong", Toast.LENGTH_SHORT).show();

                bahanid = "";

                activityCommunicator.passDataToActivity("haha");

                layout.getTabAt(1).select();

            }
            else {

                activityCommunicator.passDataToActivity(bahanid);

                bahanid = "";

            }



            //Toast.makeText(getActivity(), bahanid, Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(getActivity(), "pass gagal", Toast.LENGTH_SHORT).show();
        }
        //textView.setText(activityAssignedValue);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private BahanEnergi.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final BahanEnergi.ClickListener clickListener) {
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
