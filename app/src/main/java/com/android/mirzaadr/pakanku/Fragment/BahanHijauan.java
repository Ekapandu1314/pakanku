package com.android.mirzaadr.pakanku.Fragment;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListCheckBoxBahanAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Dao.HewanDAO;
import com.android.mirzaadr.pakanku.Dao.VersionDAO;
import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.R;
import com.android.mirzaadr.pakanku.Interface.BahanCommunicator;
import com.android.mirzaadr.pakanku.Activity.PilihBahan;

import java.util.List;

public class BahanHijauan extends Fragment implements BahanCommunicator.FragmentCommunicator {

    View view;

    private BahanCommunicator.ActivityCommunicator activityCommunicator;
    private BahanCommunicator.ActivityCommunicator fragmentCommunicator;

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
        view = inflater.inflate(R.layout.fragment_bahan_hijauan, container, false);

        mBahanDao = new BahanDAO(getActivity());
        mVersionDao = new VersionDAO(getActivity());
        mHewanDao = new HewanDAO(getActivity());
        // fill the listView
        mListBahan = mBahanDao.getAllBahanByKategori("hijauan");

        initViews();
        mAdapter = new ListCheckBoxBahanAdapter(getActivity(), mListBahan);
        bahanListView.setAdapter(mAdapter);

        //Utility.setListViewHeightBasedOnChildren(bahanListView);

        return view;
    }

    private void initViews() {
        this.bahanListView = (ListView) view.findViewById(R.id.listHijauan);
        this.mTxtEmptyListBahan = (TextView) view.findViewById(R.id.table);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        context = getActivity();
        activityCommunicator =(BahanCommunicator.ActivityCommunicator)context;
        ((PilihBahan)context).fragmentCommunicator2 = this;
    }


    @Override
    public void passDataToFragment(String haha){

        String bahanid = "";

        if(haha.toString().equals("hijauan")){



            StringBuffer responseText = new StringBuffer();

            List<Bahan> bahanxxxx = mAdapter.getItems();
            for (int i = 0; i < bahanxxxx.size(); i++) {
                Bahan bahanxv = bahanxxxx.get(i);
                if (bahanxv.isSelected()) {
                    responseText.append("-" + bahanxv.getIdbahan());
                }

            }

            bahanid = responseText.toString();

            if(bahanid.equals("")) {

                TabLayout layout = (TabLayout) getActivity().findViewById(R.id.tabsbahan);

                Toast.makeText(getActivity(), "Bahan hijauan tidak boleh kosong", Toast.LENGTH_SHORT).show();

                bahanid = "";

                activityCommunicator.passDataToActivity("haha");

                layout.getTabAt(0).select();

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
}