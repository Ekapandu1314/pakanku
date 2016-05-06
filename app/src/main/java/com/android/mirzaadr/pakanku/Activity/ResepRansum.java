package com.android.mirzaadr.pakanku.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Adapter.ListResepAdapter;
import com.android.mirzaadr.pakanku.Dao.HewanDAO;
import com.android.mirzaadr.pakanku.Model.Resep;
import com.android.mirzaadr.pakanku.R;

import java.util.List;

public class ResepRansum extends AppCompatActivity {

    List<Resep> newlistResepHijauan;
    List<Resep> newlistResepEnergi;
    List<Resep> newlistResepProtein;

    private ListResepAdapter mAdapterHijauan;
    private ListResepAdapter mAdapterEnergi;
    private ListResepAdapter mAdapterProtein;

    private TextView textviewPorsi;
    private TextView textviewHargaHari;
    private TextView textviewHargaBulan;
    private TextView textviewTextPengeluaran;
    private TextView textviewTextHargaBulan;
    private TextView textviewProdukperKg;
    private TextView textviewTextProdukperKg;
    private TextView textviewJumlahTernak;
    private TextView textviewLama;
    private TextView textviewProduksi;
    private TextView textviewTotalPemasukan;
    private TextView textviewTextTotalPemasukan;
    private TextView textviewTotalUntung;
    private TextView textviewTextTotalUntung;

    private ListView mListviewResepHijauan;
    private ListView mListviewResepEnergi;
    private ListView mListviewResepProtein;

    private RelativeLayout layoutUntung;

    String bahanid;
    String hewan;
    String tujuan;
    String textProduk = new String();

    double berat1;
    double produk;
    double asfeed_total;

    int jumlah;
    int harga_produk;
    int lama;
    int harga_total;
    int biaya_pakan;
    int keuntungan = 0;
    int penjualan_produk = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep_ransum);

        Intent i = getIntent();
        newlistResepHijauan = (List<Resep>) i.getSerializableExtra("resephijauan");
        newlistResepEnergi = (List<Resep>) i.getSerializableExtra("resepenergi");
        newlistResepProtein = (List<Resep>) i.getSerializableExtra("resepprotein");
        asfeed_total = i.getExtras().getInt("asfeedtotal");
        harga_total = i.getExtras().getInt("hargahari");
        biaya_pakan = i.getExtras().getInt("hargatotal");
        harga_produk = i.getExtras().getInt("hargaproduk");
        produk = i.getExtras().getDouble("produk");
        jumlah = i.getExtras().getInt("jmlternak");
        textProduk = i.getExtras().getString("textproduk");
        penjualan_produk = i.getExtras().getInt("totalpemasukan");
        keuntungan = i.getExtras().getInt("keuntungan");
        lama = i.getExtras().getInt("lama");

        initViewResep();

        mAdapterHijauan = new ListResepAdapter(getBaseContext(), newlistResepHijauan);
        mAdapterEnergi = new ListResepAdapter(this, newlistResepEnergi);
        mAdapterProtein = new ListResepAdapter(this, newlistResepProtein);
        mListviewResepHijauan.setAdapter(mAdapterHijauan);
        mListviewResepEnergi.setAdapter(mAdapterEnergi);
        mListviewResepProtein.setAdapter(mAdapterProtein);

        initListHeight();

        textviewHargaHari.setText("Rp. " + harga_total);
        textviewHargaBulan.setText("Rp. " + biaya_pakan);
        textviewTextHargaBulan.setText("Pengeluaran selama " + lama + " hari");
        textviewPorsi.setText(String.valueOf((int)asfeed_total));
        textviewTextPengeluaran.setText("pengeluaran / hari x " + jumlah + " ekor x " + lama + " hari");

        if(keuntungan != 0) {

            textviewTextProdukperKg.setText("Harga Produk (per " + textProduk + ")");
            textviewProdukperKg.setText("Rp. " + String.valueOf(harga_produk));
            textviewJumlahTernak.setText(String.valueOf(jumlah));
            textviewLama.setText(String.valueOf(lama));
            textviewProduksi.setText(String.valueOf(produk) + " " + textProduk);
            textviewTotalPemasukan.setText("Rp. " + String.valueOf(penjualan_produk));
            textviewTextTotalPemasukan.setText("Rp. " + String.valueOf(harga_produk) +
                    " x "  + jumlah + " ekor x " + String.valueOf(produk) + " " + textProduk+ " x " + lama + " hari");
            textviewTotalUntung.setText("Rp. " + keuntungan);
            textviewTextTotalUntung.setText("Rp. " + String.valueOf(penjualan_produk) + " - " + "Rp. " + biaya_pakan);
        }
        else {

            layoutUntung.setVisibility(View.GONE);
        }



    }

    private void initViewResep() {

        this.mListviewResepHijauan = (ListView) findViewById(R.id.listResepHijauan );
        this.mListviewResepEnergi = (ListView) findViewById(R.id.listResepEnergi );
        this.mListviewResepProtein = (ListView) findViewById(R.id.listResepProtein );
        this.textviewHargaHari = (TextView) findViewById(R.id.harga_hari);
        this.textviewHargaBulan = (TextView) findViewById(R.id.harga_bulan);
        this.textviewTextHargaBulan = (TextView) findViewById(R.id.text_harga_bulan);
        this.textviewPorsi = (TextView) findViewById(R.id.porsi_hari);
        this.textviewTextPengeluaran = (TextView) findViewById(R.id.textPengeluaranTotal);
        this.textviewProdukperKg = (TextView) findViewById(R.id.textProdukperKg);
        this.textviewTextProdukperKg = (TextView) findViewById(R.id.textTextProdukperKg);
        this.textviewJumlahTernak = (TextView) findViewById(R.id.textJumlahTernak);
        this.textviewLama = (TextView) findViewById(R.id.textLama);
        this.textviewProduksi = (TextView) findViewById(R.id.textProduksi);
        this.textviewTotalPemasukan = (TextView) findViewById(R.id.textTotalPemasukan);
        this.textviewTextTotalPemasukan = (TextView) findViewById(R.id.textTextTotalPemasukan);
        this.textviewTotalUntung = (TextView) findViewById(R.id.textTotalUntung);
        this.textviewTextTotalUntung = (TextView) findViewById(R.id.textTextTotalUntung);
        this.layoutUntung = (RelativeLayout) findViewById(R.id.layoutUntung);



    }

    private void initListHeight(){
        Utility.setListViewHeightBasedOnChildren(mListviewResepHijauan);
        Utility.setListViewHeightBasedOnChildren(mListviewResepEnergi);
        Utility.setListViewHeightBasedOnChildren(mListviewResepProtein);
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
