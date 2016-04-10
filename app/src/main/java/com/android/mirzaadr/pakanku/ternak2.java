package com.android.mirzaadr.pakanku;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        ViewPager mViewPager = (ViewPager) findViewById(R.id.containerbahan);
        setupViewPager(mViewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsbahan);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Paket_hijauan(), "hijauan");
        adapter.addFragment(new Paket_energi(), "sumber energi");
        adapter.addFragment(new Paket_protein(), "sumber protein");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
