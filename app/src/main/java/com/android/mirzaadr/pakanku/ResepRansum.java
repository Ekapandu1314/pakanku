package com.android.mirzaadr.pakanku;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Adapter.ListResepAdapter;
import com.android.mirzaadr.pakanku.Decoration.DividerItemDecoration;
import com.android.mirzaadr.pakanku.Model.Resep;

import java.util.List;

public class ResepRansum extends AppCompatActivity {

    List<Resep> newlistResepHijauan;
    List<Resep> newlistResepEnergi;
    List<Resep> newlistResepProtein;

    public ListResepAdapter mAdapterHijauan;
    public ListResepAdapter mAdapterEnergi;
    public ListResepAdapter mAdapterProtein;

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

    private RecyclerView mListviewResepHijauan;
    private RecyclerView mListviewResepEnergi;
    private RecyclerView mListviewResepProtein;

    private RelativeLayout layoutUntung;

    String textProduk = new String();

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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

        mAdapterHijauan = new ListResepAdapter(newlistResepHijauan);
        mAdapterEnergi = new ListResepAdapter(newlistResepEnergi);
        mAdapterProtein = new ListResepAdapter(newlistResepProtein);
        mListviewResepHijauan.setAdapter(mAdapterHijauan);
        mListviewResepEnergi.setAdapter(mAdapterEnergi);
        mListviewResepProtein.setAdapter(mAdapterProtein);

        mAdapterHijauan.notifyDataSetChanged();
        mAdapterEnergi.notifyDataSetChanged();
        mAdapterProtein.notifyDataSetChanged();

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

        this.mListviewResepHijauan = (RecyclerView) findViewById(R.id.listResepHijauan );
        this.mListviewResepEnergi = (RecyclerView) findViewById(R.id.listResepEnergi );
        this.mListviewResepProtein = (RecyclerView) findViewById(R.id.listResepProtein );
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

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getApplicationContext());

        mListviewResepHijauan.setLayoutManager(mLayoutManager1);
        mListviewResepHijauan.setItemAnimator(new DefaultItemAnimator());
        mListviewResepHijauan.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

        mListviewResepHijauan.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mListviewResepHijauan, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Bahan bahan1 = newlistResepHijauan.get(position);
//                Toast.makeText(getApplicationContext(), bahan1.getNamaBahan() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mListviewResepEnergi.setLayoutManager(mLayoutManager2);
        mListviewResepEnergi.setItemAnimator(new DefaultItemAnimator());
        mListviewResepEnergi.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        mListviewResepEnergi.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mListviewResepHijauan, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Bahan bahan2 = mListHargaEnergi.get(position);
//                Toast.makeText(getApplicationContext(), bahan2.getNamaBahan() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mListviewResepProtein.setLayoutManager(mLayoutManager3);
        mListviewResepProtein.setItemAnimator(new DefaultItemAnimator());
        mListviewResepProtein.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        mListviewResepProtein.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mListviewResepHijauan, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Bahan bahan3 = mListHargaProtein.get(position);
//                Toast.makeText(getApplicationContext(), bahan3.getNamaBahan() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ResepRansum.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ResepRansum.ClickListener clickListener) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resep_ransum, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_close_resepransum) {
            finish();
            BuatPakan.buatPakan.finish();
            PilihBahan.pilihBahan.finish();
        }

        if(id == android.R.id.home)
        {
            finish();
            PilihBahan.pilihBahan.finish();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
