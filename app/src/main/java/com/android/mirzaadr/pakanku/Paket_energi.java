package com.android.mirzaadr.pakanku;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListBahanAdapter;
import com.android.mirzaadr.pakanku.Adapter.ListBahanHargaAdapter;
import com.android.mirzaadr.pakanku.Adapter.ListCheckBoxBahanAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Dao.HewanDAO;
import com.android.mirzaadr.pakanku.Dao.VersionDAO;
import com.android.mirzaadr.pakanku.Model.Bahan;

import java.util.List;

public class Paket_energi extends Fragment implements interfaces.FragmentCommunicator {

    View view;

    private interfaces.ActivityCommunicator activityCommunicator;
    private interfaces.ActivityCommunicator fragmentCommunicator;

    public Context context;

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
        view = inflater.inflate(R.layout.paket_energi, container, false);

        mBahanDao = new BahanDAO(getActivity());
        mVersionDao = new VersionDAO(getActivity());
        mHewanDao = new HewanDAO(getActivity());
        // fill the listView
        mListBahan = mBahanDao.getAllBahanByKategori("energi");

        initViews();

        mAdapter = new ListCheckBoxBahanAdapter(getActivity(), mListBahan);
        bahanListView.setAdapter(mAdapter);

        //Utility.setListViewHeightBasedOnChildren(bahanListView);

        return view;
    }

    private void initViews() {
        this.bahanListView = (ListView) view.findViewById(R.id.listEnergi);
        this.mTxtEmptyListBahan = (TextView) view.findViewById(R.id.table);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        context = getActivity();
        activityCommunicator =(interfaces.ActivityCommunicator)context;
        ((ternak2)context).fragmentCommunicator1 = this;
    }

    @Override
    public void passDataToFragment(String haha){

        if(haha.toString().equals("energi")){

            StringBuffer responseText = new StringBuffer();

            List<Bahan> bahanxxxx = mAdapter.getItems();
            for (int i = 0; i < bahanxxxx.size(); i++) {
                Bahan bahanxv = bahanxxxx.get(i);
                if (bahanxv.isSelected()) {
                    responseText.append("-" + bahanxv.getIdbahan());
                }

            }

            String bahanid = responseText.toString();

            activityCommunicator.passDataToActivity(bahanid);

            //Toast.makeText(getActivity(), bahanid, Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(getActivity(), "pass gagal", Toast.LENGTH_SHORT).show();
        }
        //textView.setText(activityAssignedValue);
    }




}
