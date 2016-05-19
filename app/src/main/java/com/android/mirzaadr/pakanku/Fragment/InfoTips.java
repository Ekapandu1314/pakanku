package com.android.mirzaadr.pakanku.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListTipsAdapter;
import com.android.mirzaadr.pakanku.Internet.NetworkUtils;
import com.android.mirzaadr.pakanku.R;

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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;


public class InfoTips extends Fragment {

    ListView listview;
    ListTipsAdapter adapter = null;
    ArrayList<HashMap<String, String>> arraylist;
    public static String JUDUL = "judul";
    public static String DESKRIPSI = "deskripsi";
    public static String LINK = "link";
    public static String GAMBAR = "gambar";
    Boolean internet_error = false;

    ProgressBar haha;

    Boolean prog = false;
    Boolean conn = false;

    ImageButton reload;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        setHasOptionsMenu(true);

        listview = (ListView) view.findViewById(R.id.tipslist);
        haha = (ProgressBar) view.findViewById(R.id.progress_tips);
        reload = (ImageButton) getActivity().findViewById(R.id.reload);

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetworkUtils utils = new NetworkUtils(getActivity());
                if(utils.isConnectingToInternet()) {
                    haha.setVisibility(View.VISIBLE);
                    listview.setVisibility(View.INVISIBLE);
                    new DownloadJSON().execute();
                }
                else {
                    Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(!conn) {
            haha.setVisibility(View.VISIBLE);
        }
        else {
            haha.setVisibility(View.GONE);
            prog = true;
        }


        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            NetworkUtils utils = new NetworkUtils(getActivity());
            if(utils.isConnectingToInternet()) {

                if(adapter == null){
                    if(prog)
                    {
                        haha.setVisibility(View.VISIBLE);
                    }
                    new DownloadJSON().execute();
                }


            }
            else {

                conn = true;
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();

            }
        }else{

            // fragment is no longer visible
        }
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            //haha.setVisibility(View.VISIBLE);
            super.onPreExecute();
            prog = true;
            //pDialog = ProgressDialog.show(getActivity(), "Please wait...", "Silahkan tunggu");
            //progressDialog = createProgressDialog(getActivity());
            //progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            arraylist = new ArrayList<HashMap<String, String>>();

            String serverData = null;

            if(!internet_error) {

                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://pakanku.patpatstudio.com/android/artikel.php");
                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    serverData = EntityUtils.toString(httpEntity);
                    Log.d("response", serverData);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    internet_error = true;
                    this.cancel(true);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    internet_error = true;
                    this.cancel(true);
                } catch (IOException e) {
                    e.printStackTrace();
                    internet_error = true;
                    this.cancel(true);
                }
            }
            else {
                internet_error = true;
                this.cancel(true);
            }

            if(!internet_error){

                try {
                    // Locate the array name in JSON
                    JSONObject jsonObject = new JSONObject(serverData);
                    JSONArray jsonarray = jsonObject.getJSONArray("artikel");

                    for (int i = 0; i < jsonarray.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        JSONObject jsonObjectBahan = jsonarray.getJSONObject(i);
                        // Retrive JSON Objects
                        map.put(JUDUL, jsonObjectBahan.getString(JUDUL));
                        map.put(DESKRIPSI, jsonObjectBahan.getString(DESKRIPSI));
                        map.put(LINK, jsonObjectBahan.getString(LINK));
                        map.put(GAMBAR, jsonObjectBahan.getString(GAMBAR));
                        // Set the JSON Objects into the array
                        arraylist.add(map);
                    }
                } catch (JSONException e) {
                    this.cancel(true);
                    internet_error = true;
                    e.printStackTrace();
                }


            }
            else {

                this.cancel(true);
            }

            return null;
        }

        @Override
        protected void onCancelled() {
            internet_error = true;
            super.onCancelled();
            //progressDialog.dismiss();
            haha.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Internet connection error", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void args) {
            listview.setVisibility(View.VISIBLE);
            // Pass the results into ListViewAdapter.java
            if(internet_error) {

                //progressDialog.dismiss();
                haha.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Internet connection error", Toast.LENGTH_SHORT).show();

            }
            else {

                adapter = new ListTipsAdapter(getActivity(), arraylist);
                // Set the adapter to the ListView
                listview.setAdapter(adapter);
                // Close the progress dialog
                //progressDialog.dismiss();
                haha.setVisibility(View.GONE);

            }

        }
    }

//    public static ProgressDialog createProgressDialog(Context mContext) {
//        ProgressDialog dialog = new ProgressDialog(mContext);
//        try {
//            dialog.show();
//        } catch (WindowManager.BadTokenException e) {
//
//        }
//        dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dialog.setContentView(R.layout.progress_dialog_loading);
//        return dialog;
//    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_info_tips, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

}
