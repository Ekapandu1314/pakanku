package com.android.mirzaadr.pakanku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Adapter.ListBahanHargaAdapter;
import com.android.mirzaadr.pakanku.Adapter.ListCheckBoxBahanAdapter;
import com.android.mirzaadr.pakanku.Adapter.TableListAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Dao.HewanDAO;
import com.android.mirzaadr.pakanku.Dao.VersionDAO;
import com.android.mirzaadr.pakanku.Model.Bahan;

import java.util.ArrayList;
import java.util.List;

public class ternak2 extends AppCompatActivity {

    private ListCheckBoxBahanAdapter mAdapter;
    private List<Bahan> mListBahan;
    private VersionDAO mVersionDao;
    private HewanDAO mHewanDao;

    private ListView mListviewBahan;

    private TextView mTxtEmptyListBahan;
    private ListView bahanListView;

    private List<Bahan> mListHarga;
    private BahanDAO mBahanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ternak2);
        //TableListAdapter adapter = new TableListAdapter(this ,mListHarga);
        //bahanListView.setAdapter(adapter);

        mBahanDao = new BahanDAO(this);
        mVersionDao = new VersionDAO(this);
        mHewanDao = new HewanDAO(this);
        // fill the listView
        mListBahan = mBahanDao.getAllBahan();

        initViews();
        mAdapter = new ListCheckBoxBahanAdapter(this, mListBahan);
        bahanListView.setAdapter(mAdapter);

        Utility.setListViewHeightBasedOnChildren(bahanListView);
    }

    private void initViews() {
        this.bahanListView = (ListView) findViewById(R.id.listBahan);
        this.mTxtEmptyListBahan = (TextView) findViewById(R.id.table);

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
