package com.android.mirzaadr.pakanku;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mirzaadr on 4/1/2016.
 */
public class Info_tips extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tips_layout, container, false);

        final ListView tipsListView =(ListView)view.findViewById(R.id.tipslist);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
       //         android.R.layout.simple_list_item_1, monthsArray);
        CustomListAdapter adapter = new CustomListAdapter(getActivity(),getListData());

        tipsListView.setAdapter(adapter);
        tipsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = tipsListView.getItemAtPosition(position);
                NewsItem newsData = (NewsItem) o;
                Toast.makeText(getActivity(), "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    private ArrayList getListData() {
        ArrayList<NewsItem> results = new ArrayList<NewsItem>();
        NewsItem newsData = new NewsItem();
        newsData.setHeadline("Dance of Democracy");
        newsData.setReporterName("Pankaj Gupta");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);

        NewsItem newsData2 = new NewsItem();
        newsData2.setHeadline("Lalalala");
        newsData2.setReporterName("nonoo");
        newsData2.setDate("May 26, 2013, 13:35");
        results.add(newsData2);

        NewsItem newsData3 = new NewsItem();
        newsData3.setHeadline("Dance of Democracy");
        newsData3.setReporterName("Pankaj Gupta");
        newsData3.setDate("May 26, 2013, 13:35");
        results.add(newsData3);


        // Add some more dummy data for testing
        return results;
    }
}
