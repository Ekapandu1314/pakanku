package com.android.mirzaadr.pakanku;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListBahanAdapter;
import com.android.mirzaadr.pakanku.Dao.BahanDAO;
import com.android.mirzaadr.pakanku.Dao.DBHelper;
import com.android.mirzaadr.pakanku.Dao.VersionDAO;
import com.android.mirzaadr.pakanku.Internet.NetworkUtils;
import com.android.mirzaadr.pakanku.Manager.AlertDialogManager;
import com.android.mirzaadr.pakanku.Model.Bahan;
import com.android.mirzaadr.pakanku.Model.Version;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Bahan> mListBahan;
    private BahanDAO mBahanDao;
    private VersionDAO mVersionDao;

    AlertDialogManager alert = new AlertDialogManager();

    String version;
    String tanggal;

    private Dialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBahanDao = new BahanDAO(this);
        mVersionDao = new VersionDAO(this);
        // fill the listView
        mListBahan = mBahanDao.getAllBahan();

        NetworkUtils utils = new NetworkUtils(MainActivity.this);
        if(utils.isConnectingToInternet())
        {

            if(mListBahan != null && !mListBahan.isEmpty() && mVersionDao.getTopVersion() !=null) {

                Version versi = mVersionDao.getTopVersion();
                version = versi.getVersiBahan();
                tanggal = versi.getTanggal();

                cekUpdate(version, tanggal);

            }
            else {

                new DataFetcherTask().execute();

            }

        }
        else
        {

            if(mListBahan != null && !mListBahan.isEmpty()) {

                Toast.makeText(getBaseContext(), "No Internet Connection, no update data", Toast.LENGTH_SHORT).show();

            } else {

                alert.showAlertDialog(MainActivity.this, "No update data..", "Require to connect internet for first use", false);

            }

        }


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


 /*       Button buat = (Button) findViewById(R.id.BuatPakan);
        buat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuatPakan.class);
                startActivity(intent);
            }
        });

        Button info = (Button) findViewById(R.id.Informasi);
        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });*/
    }

    class DataFetcherTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Silahkan tunggu");
        }

        @Override
        protected Void doInBackground(Void... params) {
            String serverData = null;// String object to store fetched data from server
            // Http Request Code start

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://pakanku.patpatstudio.com/android/cekall.php");
            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                serverData = EntityUtils.toString(httpEntity);
                Log.d("response", serverData);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
// Http Request Code end
// Json Parsing Code Start
            //success = jsonObj.getInt(TAG_SUCCESS);

            try {
                //bahanArrayList = new ArrayList<Bahan>();
                JSONObject jsonObject = new JSONObject(serverData);
                JSONArray jsonArray = jsonObject.getJSONArray("bahan");

                Version version = new Version();

                String versi = jsonObject.getString("versi");
                String tanggal = jsonObject.getString("tanggal");

                version.setVersiBahan(versi);
                version.setTanggal(tanggal);

                mVersionDao.addVersionJson(version);

                //mVersionDao.createVersion(versi, tanggal);

                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObjectBahan = jsonArray.getJSONObject(i);
                    int id_bahan = jsonObjectBahan.getInt(DBHelper.BAHAN_ID);
                    String nama_bahan = jsonObjectBahan.getString(DBHelper.NAMA_BAHAN);
                    double bk_prs = jsonObjectBahan.getDouble(DBHelper.BK_PRS);
                    double pk_prs = jsonObjectBahan.getDouble(DBHelper.PK_PRS);
                    String kategori = jsonObjectBahan.getString(DBHelper.KATEGORI);
                    int harga = jsonObjectBahan.getInt(DBHelper.HARGA);

                    Bahan bahan = new Bahan();
                    bahan.setIdBahan(id_bahan);
                    bahan.setNamaBahan(nama_bahan);
                    bahan.setBk_prs(bk_prs);
                    bahan.setPk_prs(pk_prs);
                    bahan.setKategori(kategori);
                    bahan.setHarga(harga);

                    int id = id_bahan;

                    //if (mBahanDao.getBahanById(id).getNama_bahan() == null)
                    //{
                    mBahanDao.addBahanJson(bahan);// Inserting into DB
                    //}
                    //else
                    //{
                    //mBahanDao.updateBahanJSON(bahan);
                    //}

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
//Json Parsing code end
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pDialog.dismiss();

        }
    }


    private void cekUpdate(final String version, final String tanggal) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Silahkan tunggu...");
            }

            @Override
            protected String doInBackground(String... params) {

                String versi = params[0];
                String tgl = params[1];

                InputStream is = null;
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://patpatstudio.com/pakanku/update.php?versi=" + versi + "&tanggal=" + tgl);

                    HttpResponse response = httpClient.execute(httpGet);

                    //response = httpClient.execute("patpatstudio.com", "/pakanku/update.php?versi=" + versi + "&tanggal=" + tgl);
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

                String hasil = result.trim();

                Toast.makeText(getBaseContext(), hasil, Toast.LENGTH_SHORT).show();

                if(result!=null)
                {
                    if (hasil.equals("update")){

                        showUpdateDialogConfirmation();

                    }
                    else if (hasil.equals("tidak"))
                    {
                        alert.showAlertDialog(MainActivity.this, "Checking complete..", "No data update", false);
                    }

                }
                else
                {
                    alert.showAlertDialog(MainActivity.this, "Checking Failed..", "Check your internet connection", false);
                }


            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(version, tanggal);

    }

    private void showUpdateDialogConfirmation() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Update");
        alertDialogBuilder.setMessage("Are you sure you want to update data?");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the company and refresh the list
                if(mBahanDao != null) {

                    new DataFetcherTask().execute();

                    //refresh the listView

                    Version versixx = new Version();

                    versixx.setVersiBahan(version);
                    versixx.setTanggal(tanggal);

                    mVersionDao.deleteVersion(versixx);

                }

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Update succesfull", Toast.LENGTH_SHORT).show();
            }
        });

        // set neutral button OK
        alertDialogBuilder.setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

    public void BuatClick(View v) {
        Intent intent = new Intent(MainActivity.this, BuatPakan.class);
        startActivity(intent);
    }

    public void InfoClick(View v) {
        Intent intent = new Intent(MainActivity.this, InformationActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
