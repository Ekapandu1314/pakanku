package com.android.mirzaadr.pakanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Adapter.ListResepAdapter;
import com.android.mirzaadr.pakanku.Model.Resep;

import java.util.List;

public class ResepRansum extends AppCompatActivity {

    List<Resep> newlistResepHijauan;
    List<Resep> newlistResepEnergi;
    List<Resep> newlistResepProtein;

    private ListResepAdapter mAdapterHijauan;
    private ListResepAdapter mAdapterEnergi;
    private ListResepAdapter mAdapterProtein;

    private ListView mListviewResepHijauan;
    private ListView mListviewResepEnergi;
    private ListView mListviewResepProtein;
    private TextView textviewHargaHari;
    private TextView textviewHargaBulan;
    private TextView textviewTextHargaBulan;
    private TextView textviewKeuntungan;
    private TextView textviewTextKeuntungan;

    int panjangListHijauan;
    int panjangListEnergi;
    int panjangListProtein;

    int hargaHari;
    int hargaBulan;
    int Keuntungan = 0;
    int lama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep_ransum);

        Intent i = getIntent();
        newlistResepHijauan = (List<Resep>) i.getSerializableExtra("resephijauan");
        newlistResepEnergi = (List<Resep>) i.getSerializableExtra("resepenergi");
        newlistResepProtein = (List<Resep>) i.getSerializableExtra("resepprotein");
        panjangListHijauan = i.getExtras().getInt("jmlhijauan");
        panjangListEnergi = i.getExtras().getInt("jmlenergi");
        panjangListProtein = i.getExtras().getInt("jmlprotein");
        hargaHari = i.getExtras().getInt("hargahari");
        hargaBulan = i.getExtras().getInt("hargabulan");
        Keuntungan = i.getExtras().getInt("keuntungan");
        lama = i.getExtras().getInt("lama");

        initViewResep();

        mAdapterHijauan = new ListResepAdapter(this, newlistResepHijauan);
        mAdapterEnergi = new ListResepAdapter(this, newlistResepEnergi);
        mAdapterProtein = new ListResepAdapter(this, newlistResepProtein);
        mListviewResepHijauan.setAdapter(mAdapterHijauan);
        mListviewResepEnergi.setAdapter(mAdapterEnergi);
        mListviewResepProtein.setAdapter(mAdapterProtein);

        textviewHargaHari.setText("Rp. " + hargaHari);
        textviewHargaBulan.setText("Rp. " + hargaBulan);
        textviewTextHargaBulan.setText("Harga pakan " + lama + " bulan");
        textviewTextKeuntungan.setText("Keuntungan " + lama + " bulan");

        if(Keuntungan != 0) {

            textviewKeuntungan.setText("Rp. " + Keuntungan);

        }
        else {

            textviewTextKeuntungan.setVisibility(View.GONE);
            textviewKeuntungan.setVisibility(View.GONE);

        }



    }

    private void initViewResep() {

        this.mListviewResepHijauan = (ListView) findViewById(R.id.listResepHijauan );
        this.mListviewResepEnergi = (ListView) findViewById(R.id.listResepEnergi );
        this.mListviewResepProtein = (ListView) findViewById(R.id.listResepProtein );
        this.textviewHargaHari = (TextView) findViewById(R.id.harga_hari);
        this.textviewHargaBulan = (TextView) findViewById(R.id.harga_bulan);
        this.textviewTextHargaBulan = (TextView) findViewById(R.id.text_harga_bulan);
        this.textviewKeuntungan = (TextView) findViewById(R.id.keuntungan);
        this.textviewTextKeuntungan = (TextView) findViewById(R.id.text_keuntungan);

        RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) mListviewResepHijauan.getLayoutParams();
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) mListviewResepEnergi.getLayoutParams();
        RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) mListviewResepProtein.getLayoutParams();
        lp1.height = 63*panjangListHijauan;
        lp2.height = 63*panjangListEnergi;
        lp3.height = 63*panjangListProtein;
        mListviewResepHijauan.setLayoutParams(lp1);
        mListviewResepEnergi.setLayoutParams(lp2);
        mListviewResepProtein.setLayoutParams(lp3);

    }
}