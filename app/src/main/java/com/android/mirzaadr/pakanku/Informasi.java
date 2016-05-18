package com.android.mirzaadr.pakanku;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.mirzaadr.pakanku.Fragment.InfoHarga;
import com.android.mirzaadr.pakanku.Fragment.InfoRecord;
import com.android.mirzaadr.pakanku.Fragment.InfoTips;

import java.util.ArrayList;
import java.util.List;


public class Informasi extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    //private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ImageView reload, add;

    private AppBarLayout mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        mActionBar = (AppBarLayout)findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_informasi);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        //mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        //mViewPager.setAdapter(mSectionsPagerAdapter);
        setupViewPager(mViewPager);

        //final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //fab.hide();

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), MasukkanHarga.class);
//                startActivity(intent);}
//        });

//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
////            @Override
////            public void onPageSelected(int position) {
////
////                switch (position) {
////                    case 1:
////                        fab.show();
////                        break;
////
////                    default:
////                        fab.hide();
////                        break;
////                }
////            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

//        reload = (ImageView) findViewById(R.id.reload);
//        add = (ImageView) findViewById(R.id.add);
        mViewPager.setOffscreenPageLimit(2);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        iconChange(mViewPager.getCurrentItem());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pageSelected) {
                iconChange(pageSelected);
            }

            @Override
            public void onPageScrolled(int pageSelected, float positionOffset,
                                       int positionOffsetPixel) {}
            @Override
            public void onPageScrollStateChanged(int state) { }
        });

    }

    public void masukkan_harga(View view) {

        Intent intent = new Intent(getApplicationContext(), MasukkanHarga.class);
        startActivity(intent);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InfoTips(), "Tips & Trik");
        adapter.addFragment(new InfoHarga(), "Harga");
        adapter.addFragment(new InfoRecord(), "Record");
        viewPager.setAdapter(adapter);

        //setup toolbar icon
        reload = (ImageView) findViewById(R.id.reload);
        add = (ImageView) findViewById(R.id.add);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
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

    private void iconChange (int index){
        switch (index){
            case 0:
            {
                reload.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
                mActionBar.setExpanded(true, true);
                break;
            }
            case 1:
            {
                reload.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                mActionBar.setExpanded(true, true);
                break;
            }
            default:
            {
                reload.setVisibility(View.GONE);
                add.setVisibility(View.GONE);
                mActionBar.setExpanded(true, true);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_information, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
