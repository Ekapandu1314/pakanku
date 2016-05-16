package com.android.mirzaadr.pakanku;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.mirzaadr.pakanku.Adapter.ListEdittextAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Model.Bahan;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;

public class MasukkanHarga extends Activity {

    private ListView mListviewBahan;
    private ListEdittextAdapter mAdapter;
    private List<Bahan> mListBahan;
    private BahanDAO mBahanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_harga);

        mBahanDao = new BahanDAO(this);
        // fill the listView
        mListBahan = mBahanDao.getAllBahan();

        initViews();

        mAdapter = new ListEdittextAdapter(this, mListBahan);
        mListviewBahan.setAdapter(mAdapter);

        //RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) mListviewBahan.getLayoutParams();
        //lp1.height = 70*mListBahan.size();

        Utility.setListViewHeightBasedOnChildren(mListviewBahan);

    }

    private void initViews() {
        this.mListviewBahan = (ListView) findViewById(R.id.listEdit);
        //this.mTxtEmptyListBahan = (TextView) findViewById(R.id.txt_empty_list_companies);
        //this.mListviewBahan.setOnItemClickListener(this);

    }

    public void checkHargaBaru(View view){

        Calendar c = Calendar.getInstance();

        int hours_calendar_temp = c.get(Calendar.HOUR_OF_DAY);
        int minutes_calendar_temp = c.get(Calendar.MINUTE);
        int month_calendar_temp = c.get(Calendar.MONTH) + 1;
        int date_calendar_temp = c.get(Calendar.DAY_OF_MONTH);
        int year_calendar_temp = c.get(Calendar.YEAR);

        String hours_calendar = String.valueOf(hours_calendar_temp);
        String minutes_calendar = String.valueOf(minutes_calendar_temp);
        String month_calendar = String.valueOf(month_calendar_temp);
        String date_calendar = String.valueOf(date_calendar_temp);
        String year_calendar = String.valueOf(year_calendar_temp);

        if(hours_calendar.trim().length() != 2) {

            hours_calendar = "0" + hours_calendar;

        }

        if(minutes_calendar.trim().length() != 2) {

            minutes_calendar = "0" + minutes_calendar;

        }

        if(month_calendar.trim().length() != 2) {

            month_calendar = "0" + month_calendar;

        }

        if(date_calendar.trim().length() != 2) {

            date_calendar = "0" + date_calendar;

        }


        String tanggal = date_calendar + month_calendar + year_calendar + "-" + hours_calendar + minutes_calendar;

        int ulang = 1;

        StringBuffer responseText = new StringBuffer();

        List<Bahan> bahanxxxx = mAdapter.getItems();
        for (int i = 0; i < bahanxxxx.size(); i++) {
            Bahan bahanxv = bahanxxxx.get(i);
            if (bahanxv.getHarga_baru() == "" || bahanxv.getHarga_baru() == "-"){
                responseText.append("&tgl" + ulang + "=" + tanggal + "&idbahan" + ulang + "=" + String.valueOf(bahanxv.getIdbahan()) + "&harga" + ulang + "=" + bahanxv.getHarga());
                mBahanDao.updateBahanFromId(bahanxv.getIdbahan(), bahanxv.getHarga());
            }
            else {
                responseText.append("&tgl" + ulang + "=" + tanggal + "&idbahan" + ulang + "=" + String.valueOf(bahanxv.getIdbahan()) + "&harga" + ulang + "=" + bahanxv.getHarga_baru());
                mBahanDao.updateBahanFromId(bahanxv.getIdbahan(), Integer.parseInt(bahanxv.getHarga_baru()));
            }

            ulang++;

        }

        //Toast.makeText(getBaseContext(), "ulang=" + ulang + "&" + responseText.toString(), Toast.LENGTH_SHORT).show();

        String kirim = "ulang=" + ulang + "&" + responseText.toString();

        sendHarga(kirim);

    }

    private void sendHarga(final String link) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MasukkanHarga.this, "Please wait", "Silahkan tunggu...");
            }

            @Override
            protected String doInBackground(String... params) {

                InputStream is = null;
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://pakanku.patpatstudio.com/android/input_harga.php?" + link);

                    HttpResponse response = httpClient.execute(httpGet);
                    HttpEntity entity = response.getEntity();
                    String htmlResponse = EntityUtils.toString(entity);
                    result = htmlResponse;
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;


            }

            @Override
            protected void onPostExecute(String result){

                loadingDialog.dismiss();

            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(link);

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
