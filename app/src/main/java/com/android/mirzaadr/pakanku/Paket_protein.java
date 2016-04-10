package com.android.mirzaadr.pakanku;

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
import com.android.mirzaadr.pakanku.Adapter.ListCheckBoxBahanAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Dao.HewanDAO;
import com.android.mirzaadr.pakanku.Dao.VersionDAO;
import com.android.mirzaadr.pakanku.Model.Bahan;

import java.util.List;
public class Paket_protein extends Fragment {

    View view;


    private ListCheckBoxBahanAdapter mAdapter;
    private List<Bahan> mListBahan;
    private VersionDAO mVersionDao;
    private HewanDAO mHewanDao;


    private TextView mTxtEmptyListBahan;
    private ListView bahanListView;

    private BahanDAO mBahanDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.paket_protein, container, false);

        mBahanDao = new BahanDAO(getActivity());
        mVersionDao = new VersionDAO(getActivity());
        mHewanDao = new HewanDAO(getActivity());
        // fill the listView
        mListBahan = mBahanDao.getAllBahan();

        initViews();
        mAdapter = new ListCheckBoxBahanAdapter(getActivity(), mListBahan);
        bahanListView.setAdapter(mAdapter);

        //Utility.setListViewHeightBasedOnChildren(bahanListView);

        return view;
    }

    private void initViews() {
        this.bahanListView = (ListView) view.findViewById(R.id.listProtein);
        this.mTxtEmptyListBahan = (TextView) view.findViewById(R.id.table);
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
