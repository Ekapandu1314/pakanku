package com.android.mirzaadr.pakanku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListResepAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Dao.HewanDAO;
import com.android.mirzaadr.pakanku.Dao.RecordDAO;
import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.Model.Hewan;
import com.android.mirzaadr.pakanku.Model.Record;
import com.android.mirzaadr.pakanku.Model.Resep;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResepRansumRecord extends Activity {

    int idrecord;

    RecordDAO mRecordDao;
    HewanDAO mHewanDao;
    BahanDAO mBahanDao;

    Record newRecord;

    String bahanid;
    String hewan;
    String tujuan;

    double berat1;
    double produk;

    int jumlah;
    int lama;
    int harga_total = 0;
    int biaya_pakan;
    int keuntungan = 0;

    int j = 0;
    int k = 0;
    int l = 0;

    private ListView mListviewResepHijauan;
    private ListView mListviewResepEnergi;
    private ListView mListviewResepProtein;
    private TextView textviewHargaHari;
    private TextView textviewHargaBulan;
    private TextView textviewTextHargaBulan;
    private TextView textviewKeuntungan;
    private TextView textviewTextKeuntungan;

    private ListResepAdapter mAdapterHijauan;
    private ListResepAdapter mAdapterEnergi;
    private ListResepAdapter mAdapterProtein;

    List<Resep> newlistResepHijauan = new ArrayList<Resep>();
    List<Resep> newlistResepEnergi = new ArrayList<Resep>();
    List<Resep> newlistResepProtein = new ArrayList<Resep>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep_ransum);

        Intent i = getIntent();
        idrecord = i.getExtras().getInt("idrecord");

        mRecordDao = new RecordDAO(this);
        mHewanDao = new HewanDAO(this);
        mBahanDao = new BahanDAO(this);

        newRecord = mRecordDao.getRecordById(idrecord);

        hewan = newRecord.getRhewan();
        tujuan = newRecord.getRtujuan();
        berat1 = newRecord.getRberat1();
        produk = newRecord.getRberat2();
        jumlah = newRecord.getRjternak();
        lama = newRecord.getRlama();
        bahanid = newRecord.getPbahan();

        if(produk == 0.0){

            HitungPakan();

        }
        else {

            CekUntung();

        }

        initViewResep();

        mAdapterHijauan = new ListResepAdapter(getBaseContext(), newlistResepHijauan);
        mAdapterEnergi = new ListResepAdapter(this, newlistResepEnergi);
        mAdapterProtein = new ListResepAdapter(this, newlistResepProtein);
        mListviewResepHijauan.setAdapter(mAdapterHijauan);
        mListviewResepEnergi.setAdapter(mAdapterEnergi);
        mListviewResepProtein.setAdapter(mAdapterProtein);

        textviewHargaHari.setText("Rp. " + harga_total);
        textviewHargaBulan.setText("Rp. " + biaya_pakan);
        textviewTextHargaBulan.setText("Harga pakan " + lama + " bulan");
        //textviewTextKeuntungan.setText("Keuntungan " + lama + " bulan");

        if(keuntungan != 0) {

            textviewKeuntungan.setText("Rp. " + keuntungan);

        }
        else {

            //textviewTextKeuntungan.setVisibility(View.GONE);
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
        //this.textviewTextKeuntungan = (TextView) findViewById(R.id.text_keuntungan);

        Utility.setListViewHeightBasedOnChildren(mListviewResepHijauan);
        Utility.setListViewHeightBasedOnChildren(mListviewResepEnergi);
        Utility.setListViewHeightBasedOnChildren(mListviewResepProtein);

    }

    public void HitungPakan() {

        Hewan hewanxx = mHewanDao.getHewanByHewanTujuan(hewan, tujuan);

        double bk_hewan = hewanxx.getBk_hewan();        // Tanpa %
        double pk_hewan = hewanxx.getPk_hewan();        // Tanpa %
        double hijauan = hewanxx.getHijau();            // Tanpa %
        double konsentrat = hewanxx.getKonsentrat();    // Tanpa %

        double bk_kg = (bk_hewan * berat1)/100;
        double pk_kg = (bk_kg * pk_hewan)/100;
        double bk_hijauan = (hijauan * bk_kg)/100;
        double bk_konsentrat = (konsentrat * bk_kg)/100;
        //double pk_hijauan = ()

        //Toast.makeText(getBaseContext(), bk_kg + "\n" + pk_kg + "\n" + bk_hijauan + "\n" + bk_konsentrat, Toast.LENGTH_LONG).show();

        String bahan[] = bahanid.split("-");

        //Toast.makeText(getBaseContext(), String.valueOf(bahan.length), Toast.LENGTH_SHORT).show();

        int hijau[] = new int[10];
        int energi[] = new int[10];
        int protein[] = new int[10];

        int j = 0;
        int k = 0;
        int l = 0;

        for(int i = 1; i < bahan.length; i++) {

            Bahan bahanzz = mBahanDao.getBahanById(Integer.parseInt(bahan[i]));

            if(bahanzz.getKategori().equals("hijauan")) {

                hijau[j] = Integer.parseInt(bahan[i]);

                j++;

            }
            else if (bahanzz.getKategori().equals("energi")) {

                energi[k] = Integer.parseInt(bahan[i]);

                k++;

            }
            else if (bahanzz.getKategori().equals("protein")) {

                protein[l] = Integer.parseInt(bahan[i]);

                l++;

            }
            else {
                Toast.makeText(getBaseContext(), "Error klasifikasi", Toast.LENGTH_SHORT).show();
            }
        }

        double pk_prs_hijauan[] = new double[10];
        double total_prs_pk_hijauan = 0;
        double pembagi_hijauan[] = new double[10];

        for (int i = 0; i < j ; i++) {

            pk_prs_hijauan[i] = mBahanDao.getBahanById(hijau[i]).getPk_prs();   // Tanpa %

            total_prs_pk_hijauan += pk_prs_hijauan[i];      // Tanpa %

        }

        //double prs_pk_hijauan[] = new double[10];

        double pk_kg_hijauan_temp = 0;
        double pk_kg_hijauan = 0;

        for (int i = 0; i < j ; i++) {

            pembagi_hijauan[i] = (pk_prs_hijauan[i]*100)/total_prs_pk_hijauan;  //Tanpa %

            pk_kg_hijauan_temp = (pembagi_hijauan[i] * pk_prs_hijauan[i] * bk_hijauan)/10000;

            pk_kg_hijauan += pk_kg_hijauan_temp;

            //Toast.makeText(getBaseContext(), "Pembagi hijauan = " + String.valueOf(pembagi_hijauan[i] + "\n" + "Pk prs hijauan = " + pk_prs_hijauan[i]) + "\nBk Hijauan = " + bk_hijauan + "\nPk kg hijauan = " + pk_kg_hijauan, Toast.LENGTH_LONG).show();

        }

        double pk_konsentrat = pk_kg - pk_kg_hijauan;

        //Toast.makeText(getBaseContext(), "Pk dari hijauan" + " = " + String.valueOf(pk_kg_hijauan), Toast.LENGTH_SHORT).show();

        double pk_konsentrat_prs = (pk_konsentrat/bk_konsentrat)*100; //Tanpa %

        //Bahan energi2;
        double pk_energi[] = new double[10];
        //double total_pk_energi = 0;
        double harga_energi[] = new double[10];
        double perbandingan_energi_temp[] = new double[10];
        double total_perbandingan_energi = 0;

        //responseText2.append("\n\nEnergi...");

        for (int i = 0; i < k; i++) {


            pk_energi[i] = mBahanDao.getBahanById(energi[i]).getPk_prs();   //Tanpa %

            harga_energi[i] = mBahanDao.getBahanById(energi[i]).getHarga(); //Tanpa %

            perbandingan_energi_temp[i] = harga_energi[i]/pk_energi[i];

            total_perbandingan_energi += perbandingan_energi_temp[i];

        }

        double perbandingan_energi[] = new double[10];
        double prs_pk_energi[] = new double[10];
        double total_pk_prs_energi = 0;

        for (int i = 0; i < k; i++) {

            perbandingan_energi[i] = (perbandingan_energi_temp[i]*100)/total_perbandingan_energi;  //Tanpa %

            prs_pk_energi[i] = (perbandingan_energi[i] * pk_energi[i])/100; //Tanpa %

            total_pk_prs_energi += prs_pk_energi[i];    //Tanpa %

        }

        double pk_protein[] = new double[10];
        double harga_protein[] = new double[10];
        double perbandingan_protein_temp[] = new double[10];
        double total_perbandingan_protein = 0;

        for (int i = 0; i < l; i++) {

            pk_protein[i] = mBahanDao.getBahanById(protein[i]).getPk_prs();   //Tanpa %

            harga_protein[i] = mBahanDao.getBahanById(protein[i]).getHarga(); //Tanpa %

            perbandingan_protein_temp[i] = harga_protein[i]/pk_protein[i];

            total_perbandingan_protein += perbandingan_protein_temp[i];

        }

        double perbandingan_protein[] = new double[10];
        double prs_pk_protein[] = new double[10];
        double total_pk_prs_protein = 0;

        for (int i = 0; i < l; i++) {

            perbandingan_protein[i] = (perbandingan_protein_temp[i]/total_perbandingan_protein)*100;  //Tanpa %

            prs_pk_protein[i] = (perbandingan_protein[i] * pk_protein[i])/100; //Tanpa %

            total_pk_prs_protein += prs_pk_protein[i];  //Tanpa %

        }

        double sbr_energi_temp = (total_pk_prs_protein - pk_konsentrat_prs)/100; //Tanpa %
        double sbr_protein_temp = (total_pk_prs_energi - pk_konsentrat_prs)/100; //Tanpa %

        if (sbr_energi_temp < 0) {
            sbr_energi_temp = sbr_energi_temp * (-1);
        }

        if (sbr_protein_temp < 0) {
            sbr_protein_temp = sbr_protein_temp * (-1);
        }

        double total_prs_energi_protein_temp = sbr_protein_temp + sbr_energi_temp; //Tanpa %

        double prs_sbr_energi = (sbr_energi_temp/total_prs_energi_protein_temp)*100;    //Tanpa %
        double prs_sbr_protein = (sbr_protein_temp/total_prs_energi_protein_temp)*100;  //Tanpa %

        double sbr_energi_kg = (prs_sbr_energi * bk_konsentrat)/100;
        double sbr_protein_kg = (prs_sbr_protein * bk_konsentrat)/100;

        DecimalFormat df2 = new DecimalFormat("##.");

        double asfeed_hijauan[] = new double[10];
        int harga_total = 0;
        int harga_hijauan[] = new int[10];

        for (int i = 0; i < j; i++) {

            asfeed_hijauan[i] = (bk_hijauan * pembagi_hijauan[i] * 1000)/(mBahanDao.getBahanById(hijau[i]).getBk_prs());

            harga_hijauan[i] = (int)(asfeed_hijauan[i] * mBahanDao.getBahanById(hijau[i]).getHarga());

            harga_total += harga_hijauan[i];

        }

        double jml_energi[] = new double[10];
        double asfeed_energi[] = new double[10];
        int harga_energi_akhir[] = new int[10];

        for (int i = 0; i < k; i++) {

            jml_energi[i] = (perbandingan_energi[i] * sbr_energi_kg)/100;

            asfeed_energi[i] = (jml_energi[i] * 100 * 1000)/mBahanDao.getBahanById(energi[i]).getBk_prs();

            harga_energi_akhir[i] = (int)(asfeed_energi[i] * mBahanDao.getBahanById(energi[i]).getHarga());

            harga_total += harga_energi_akhir[i];

        }

        double jml_protein[] = new double[10];
        double asfeed_protein[] = new double[10];
        int harga_protein_akhir[] = new int[10];

        for (int i = 0; i < l; i++) {

            jml_protein[i] = (perbandingan_protein[i] * sbr_protein_kg)/100;

            asfeed_protein[i] = (jml_protein[i] * 100 * 1000)/mBahanDao.getBahanById(protein[i]).getBk_prs();

            harga_protein_akhir[i] = (int)(asfeed_protein[i] * mBahanDao.getBahanById(protein[i]).getHarga());

            harga_total += harga_protein_akhir[i];

        }

        int biaya_pakan = lama * harga_total * jumlah;

        Resep resep;

        List<Resep> newlistResepHijauan = new ArrayList<Resep>();
        List<Resep> newlistResepEnergi = new ArrayList<Resep>();
        List<Resep> newlistResepProtein = new ArrayList<Resep>();

        double asfeed_total = 0;

        for (int i = 0; i < j; i++) {

            resep = new Resep(i+1, mBahanDao.getBahanById(hijau[i]).getNamaBahan(), String.valueOf(mBahanDao.getBahanById(hijau[i]).getHarga()), df2.format(asfeed_hijauan[i]), String.valueOf(harga_hijauan[i]), "hijauan");

            asfeed_total += asfeed_hijauan[i];

            newlistResepHijauan.add(resep);

        }

        for (int i = 0; i < k; i++) {

            resep = new Resep(i+1, mBahanDao.getBahanById(energi[i]).getNamaBahan(), String.valueOf(mBahanDao.getBahanById(energi[i]).getHarga()), df2.format(asfeed_energi[i]), String.valueOf(harga_energi_akhir[i]), "energi");

            asfeed_total += asfeed_energi[i];

            newlistResepEnergi.add(resep);

        }


        for (int i = 0; i < l; i++) {

            resep = new Resep(i+1, mBahanDao.getBahanById(protein[i]).getNamaBahan(), String.valueOf(mBahanDao.getBahanById(protein[i]).getHarga()),df2.format(asfeed_protein[i]), String.valueOf(harga_protein_akhir[i]), "protein");

            asfeed_total += asfeed_protein[i];

            newlistResepProtein.add(resep);

        }

        Intent newIntent = new Intent(getApplicationContext(), ResepRansum.class);

        Bundle var_resep = new Bundle();

        newIntent.putExtra("resephijauan", (Serializable) newlistResepHijauan);
        newIntent.putExtra("resepenergi", (Serializable) newlistResepEnergi);
        newIntent.putExtra("resepprotein", (Serializable) newlistResepProtein);
        var_resep.putInt("asfeedtotal", (int)asfeed_total);
        var_resep.putInt("hargahari", harga_total);
        var_resep.putInt("hargatotal", biaya_pakan);
        var_resep.putInt("jmlternak", jumlah);
        var_resep.putInt("lama", lama);
        newIntent.putExtras(var_resep);
        startActivity(newIntent);
    }

    public void CekUntung() {

        Hewan hewanxx = mHewanDao.getHewanByHewanTujuan(hewan, tujuan);

        double bk_hewan = hewanxx.getBk_hewan();        // Tanpa %
        double pk_hewan = hewanxx.getPk_hewan();        // Tanpa %
        double hijauan = hewanxx.getHijau();            // Tanpa %
        double konsentrat = hewanxx.getKonsentrat();    // Tanpa %

        double bk_kg = (bk_hewan * berat1)/100;
        double pk_kg = (bk_kg * pk_hewan)/100;
        double bk_hijauan = (hijauan * bk_kg)/100;
        double bk_konsentrat = (konsentrat * bk_kg)/100;
        //double pk_hijauan = ()

        //Toast.makeText(getBaseContext(), bk_kg + "\n" + pk_kg + "\n" + bk_hijauan + "\n" + bk_konsentrat, Toast.LENGTH_LONG).show();

        String bahan[] = bahanid.split("-");

        //Toast.makeText(getBaseContext(), String.valueOf(bahan.length), Toast.LENGTH_SHORT).show();

        int hijau[] = new int[10];
        int energi[] = new int[10];
        int protein[] = new int[10];

        int j = 0;
        int k = 0;
        int l = 0;

        for(int i = 1; i < bahan.length; i++) {

            Bahan bahanzz = mBahanDao.getBahanById(Integer.parseInt(bahan[i]));

            if(bahanzz.getKategori().equals("hijauan")) {

                hijau[j] = Integer.parseInt(bahan[i]);

                j++;

            }
            else if (bahanzz.getKategori().equals("energi")) {

                energi[k] = Integer.parseInt(bahan[i]);

                k++;

            }
            else if (bahanzz.getKategori().equals("protein")) {

                protein[l] = Integer.parseInt(bahan[i]);

                l++;

            }
            else {
                Toast.makeText(getBaseContext(), "Error klasifikasi", Toast.LENGTH_SHORT).show();
            }
        }

        double pk_prs_hijauan[] = new double[10];
        double total_prs_pk_hijauan = 0;
        double pembagi_hijauan[] = new double[10];

        for (int i = 0; i < j ; i++) {

            pk_prs_hijauan[i] = mBahanDao.getBahanById(hijau[i]).getPk_prs();   // Tanpa %

            total_prs_pk_hijauan += pk_prs_hijauan[i];      // Tanpa %

        }

        double pk_kg_hijauan_temp = 0;
        double pk_kg_hijauan = 0;

        for (int i = 0; i < j ; i++) {

            pembagi_hijauan[i] = (pk_prs_hijauan[i]*100)/total_prs_pk_hijauan;  //Tanpa %

            pk_kg_hijauan_temp = (pembagi_hijauan[i] * pk_prs_hijauan[i] * bk_hijauan)/10000;

            pk_kg_hijauan += pk_kg_hijauan_temp;

            //Toast.makeText(getBaseContext(), "Pembagi hijauan = " + String.valueOf(pembagi_hijauan[i] + "\n" + "Pk prs hijauan = " + pk_prs_hijauan[i]) + "\nBk Hijauan = " + bk_hijauan + "\nPk kg hijauan = " + pk_kg_hijauan, Toast.LENGTH_LONG).show();

        }

        double pk_konsentrat = pk_kg - pk_kg_hijauan;

        double pk_konsentrat_prs = (pk_konsentrat/bk_konsentrat)*100; //Tanpa %


        double pk_energi[] = new double[10];
        double harga_energi[] = new double[10];
        double perbandingan_energi_temp[] = new double[10];
        double total_perbandingan_energi = 0;

        for (int i = 0; i < k; i++) {


            pk_energi[i] = mBahanDao.getBahanById(energi[i]).getPk_prs();   //Tanpa %

            harga_energi[i] = mBahanDao.getBahanById(energi[i]).getHarga(); //Tanpa %

            perbandingan_energi_temp[i] = harga_energi[i]/pk_energi[i];

            total_perbandingan_energi += perbandingan_energi_temp[i];

        }

        double perbandingan_energi[] = new double[10];
        double prs_pk_energi[] = new double[10];
        double total_pk_prs_energi = 0;

        for (int i = 0; i < k; i++) {

            perbandingan_energi[i] = (perbandingan_energi_temp[i]*100)/total_perbandingan_energi;  //Tanpa %

            prs_pk_energi[i] = (perbandingan_energi[i] * pk_energi[i])/100; //Tanpa %

            total_pk_prs_energi += prs_pk_energi[i];    //Tanpa %

        }

        double pk_protein[] = new double[10];
        double harga_protein[] = new double[10];
        double perbandingan_protein_temp[] = new double[10];
        double total_perbandingan_protein = 0;

        for (int i = 0; i < l; i++) {

            pk_protein[i] = mBahanDao.getBahanById(protein[i]).getPk_prs();   //Tanpa %

            harga_protein[i] = mBahanDao.getBahanById(protein[i]).getHarga(); //Tanpa %

            perbandingan_protein_temp[i] = harga_protein[i]/pk_protein[i];

            total_perbandingan_protein += perbandingan_protein_temp[i];

        }

        double perbandingan_protein[] = new double[10];
        double prs_pk_protein[] = new double[10];
        double total_pk_prs_protein = 0;

        for (int i = 0; i < l; i++) {

            perbandingan_protein[i] = (perbandingan_protein_temp[i]/total_perbandingan_protein)*100;  //Tanpa %

            prs_pk_protein[i] = (perbandingan_protein[i] * pk_protein[i])/100; //Tanpa %

            total_pk_prs_protein += prs_pk_protein[i];  //Tanpa %

        }

        double sbr_energi_temp = (total_pk_prs_protein - pk_konsentrat_prs)/100; //Tanpa %
        double sbr_protein_temp = (total_pk_prs_energi - pk_konsentrat_prs)/100; //Tanpa %

        if (sbr_energi_temp < 0) {
            sbr_energi_temp = sbr_energi_temp * (-1);
        }

        if (sbr_protein_temp < 0) {
            sbr_protein_temp = sbr_protein_temp * (-1);
        }

        double total_prs_energi_protein_temp = sbr_protein_temp + sbr_energi_temp; //Tanpa %

        double prs_sbr_energi = (sbr_energi_temp/total_prs_energi_protein_temp)*100;    //Tanpa %
        double prs_sbr_protein = (sbr_protein_temp/total_prs_energi_protein_temp)*100;  //Tanpa %

        double sbr_energi_kg = (prs_sbr_energi * bk_konsentrat)/100;
        double sbr_protein_kg = (prs_sbr_protein * bk_konsentrat)/100;

        DecimalFormat df2 = new DecimalFormat("##.");

        double asfeed_hijauan[] = new double[10];
        int harga_total = 0;
        int harga_hijauan[] = new int[10];

        for (int i = 0; i < j; i++) {

            asfeed_hijauan[i] = (bk_hijauan * pembagi_hijauan[i] * 1000)/(mBahanDao.getBahanById(hijau[i]).getBk_prs());

            harga_hijauan[i] = (int)(asfeed_hijauan[i] * mBahanDao.getBahanById(hijau[i]).getHarga());

            harga_total += harga_hijauan[i];

        }

        double jml_energi[] = new double[10];
        double asfeed_energi[] = new double[10];
        int harga_energi_akhir[] = new int[10];

        for (int i = 0; i < k; i++) {

            jml_energi[i] = (perbandingan_energi[i] * sbr_energi_kg)/100;

            asfeed_energi[i] = (jml_energi[i] * 100 * 1000)/mBahanDao.getBahanById(energi[i]).getBk_prs();

            harga_energi_akhir[i] = (int)(asfeed_energi[i] * mBahanDao.getBahanById(energi[i]).getHarga());

            harga_total += harga_energi_akhir[i];

        }

        double jml_protein[] = new double[10];
        double asfeed_protein[] = new double[10];
        int harga_protein_akhir[] = new int[10];

        for (int i = 0; i < l; i++) {

            jml_protein[i] = (perbandingan_protein[i] * sbr_protein_kg)/100;

            asfeed_protein[i] = (jml_protein[i] * 100 * 1000)/mBahanDao.getBahanById(protein[i]).getBk_prs();

            harga_protein_akhir[i] = (int)(asfeed_protein[i] * mBahanDao.getBahanById(protein[i]).getHarga());

            harga_total += harga_protein_akhir[i];

        }

        int biaya_pakan = lama * harga_total * jumlah;

        String textProduk = new String();
        int keuntungan = 0;
        int penjualan_produk = 0;

        if(tujuan.equals("Potong")) {

            double kenaikan_bobot = lama * produk;
            penjualan_produk = (int)(mHewanDao.getHewanByHewanTujuan(hewan, tujuan).getHargajual() * kenaikan_bobot * jumlah);
            keuntungan = penjualan_produk - biaya_pakan;
            textProduk = "kg";

        }
        else if (tujuan.equals("Perah")) {

            penjualan_produk = (int)(mHewanDao.getHewanByHewanTujuan(hewan, tujuan).getHargajual() * produk * jumlah);
            keuntungan = penjualan_produk - biaya_pakan;
            textProduk = "lt";

        }

        Resep resep;

        List<Resep> newlistResepHijauan = new ArrayList<Resep>();
        List<Resep> newlistResepEnergi = new ArrayList<Resep>();
        List<Resep> newlistResepProtein = new ArrayList<Resep>();

        double asfeed_total = 0;

        for (int i = 0; i < j; i++) {

            resep = new Resep(i+1, mBahanDao.getBahanById(hijau[i]).getNamaBahan(), String.valueOf(mBahanDao.getBahanById(hijau[i]).getHarga()),df2.format(asfeed_hijauan[i]), String.valueOf(harga_hijauan[i]), "hijauan");

            asfeed_total += asfeed_hijauan[i];

            newlistResepHijauan.add(resep);

        }

        for (int i = 0; i < k; i++) {

            resep = new Resep(i+1, mBahanDao.getBahanById(energi[i]).getNamaBahan(), String.valueOf(mBahanDao.getBahanById(hijau[i]).getHarga()),df2.format(asfeed_energi[i]), String.valueOf(harga_energi_akhir[i]), "energi");

            asfeed_total += asfeed_energi[i];

            newlistResepEnergi.add(resep);

        }


        for (int i = 0; i < l; i++) {

            resep = new Resep(i+1, mBahanDao.getBahanById(protein[i]).getNamaBahan(), String.valueOf(mBahanDao.getBahanById(hijau[i]).getHarga()),df2.format(asfeed_protein[i]), String.valueOf(harga_protein_akhir[i]), "protein");

            asfeed_total += asfeed_protein[i];

            newlistResepProtein.add(resep);

        }

        Intent newIntent = new Intent(getApplicationContext(), ResepRansum.class);

        Bundle var_resep = new Bundle();

        newIntent.putExtra("resephijauan", (Serializable) newlistResepHijauan);
        newIntent.putExtra("resepenergi", (Serializable) newlistResepEnergi);
        newIntent.putExtra("resepprotein", (Serializable) newlistResepProtein);
        var_resep.putInt("asfeedtotal", (int)asfeed_total);
        var_resep.putInt("hargahari", harga_total);
        var_resep.putInt("hargatotal", biaya_pakan);
        var_resep.putInt("hargaproduk", mHewanDao.getHewanByHewanTujuan(hewan, tujuan).getHargajual());
        var_resep.putDouble("produk", produk);
        var_resep.putInt("jmlternak", jumlah);
        var_resep.putString("textproduk", textProduk);
        var_resep.putInt("lama", lama);
        var_resep.putInt("totalpemasukan", penjualan_produk);
        var_resep.putInt("keuntungan", keuntungan);
        newIntent.putExtras(var_resep);
        startActivity(newIntent);
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
