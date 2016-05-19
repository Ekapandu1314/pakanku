package com.android.mirzaadr.pakanku;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
//import android.widget.RelativeLayout;
//import android.widget.ScrollView;
////import android.view.ViewGroup;
////import android.widget.ListAdapter;
////import android.widget.ListView;
////import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListEdittextAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Decoration.DividerItemDecoration;
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

public class MasukkanHarga extends AppCompatActivity {

    private RecyclerView mListviewBahan;
    private ListEdittextAdapter mAdapter;
    public List<Bahan> mListBahan;
    private BahanDAO mBahanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_harga);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mBahanDao = new BahanDAO(this);
        // fill the listView
        mListBahan = mBahanDao.getAllBahan();

        initViews();

        mAdapter = new ListEdittextAdapter(mListBahan);
        mListviewBahan.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.rellyt);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) v.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                linearLayout.requestFocus();
            }
        });

    }

    private void initViews() {
        this.mListviewBahan = (RecyclerView) findViewById(R.id.listEdit);

        mListviewBahan.setNestedScrollingEnabled(false);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        mListviewBahan.setLayoutManager(mLayoutManager);
        mListviewBahan.setItemAnimator(new DefaultItemAnimator());
        mListviewBahan.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

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

        StringBuilder responseText = new StringBuilder();

        List<Bahan> bahanxxxx = mAdapter.getmItems();
        for (int i = 0; i < bahanxxxx.size(); i++) {
            Bahan bahanxv = bahanxxxx.get(i);
            if (bahanxv.getHarga_baru().equals("")  || bahanxv.getHarga_baru().equals("-")){
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

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MasukkanHarga.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MasukkanHarga.ClickListener clickListener) {
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
                        clickListener.onLongClick(child, recyclerView.getChildLayoutPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildLayoutPosition(child));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            finish();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
