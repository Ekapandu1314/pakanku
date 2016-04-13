package com.android.mirzaadr.pakanku;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListTipsAdapter;
import com.android.mirzaadr.pakanku.Internet.JSONfunctions;
import com.android.mirzaadr.pakanku.Internet.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mirzaadr on 4/1/2016.
 */
public class Info_tips extends Fragment {

    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListTipsAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    public static String JUDUL = "judul";
    public static String DESKRIPSI = "deskripsi";
    public static String LINK = "link";
    public static String GAMBAR = "gambar";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tips_layout, container, false);

        listview = (ListView) view.findViewById(R.id.tipslist);



        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            NetworkUtils utils = new NetworkUtils(getActivity());
            if(utils.isConnectingToInternet()) {

                new DownloadJSON().execute();

            }
            else {

                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();

            }
        }else{

            // fragment is no longer visible
        }
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Please wait");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL("http://pakanku.patpatstudio.com/android/artikel.php");

            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("artikel");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put(JUDUL, jsonobject.getString(JUDUL));
                    map.put(DESKRIPSI, jsonobject.getString(DESKRIPSI));
                    map.put(LINK, jsonobject.getString(LINK));
                    map.put(GAMBAR, jsonobject.getString(GAMBAR));
                    // Set the JSON Objects into the array
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Pass the results into ListViewAdapter.java
            adapter = new ListTipsAdapter(getActivity(), arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

}
