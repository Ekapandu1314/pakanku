package com.android.mirzaadr.pakanku;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.mirzaadr.pakanku.Adapter.ListRecordAdapter;
import com.android.mirzaadr.pakanku.Dao.RecordDAO;
import com.android.mirzaadr.pakanku.Model.Record;

import java.util.List;

/**
 * Created by Mirzaadr on 4/1/2016.
 */
public class Info_history extends Fragment {

    private ListRecordAdapter mAdapter;
    private List<Record> mListRecord;
    private RecordDAO mRecordDao;
    private ListView mListviewRecord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.history_layout, container, false);

        mRecordDao = new RecordDAO(getActivity());
        mListRecord = mRecordDao.getAllRecord();

        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerHewan);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.hewan, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        this.mListviewRecord = (ListView) view.findViewById(R.id.listRecord);

        mAdapter = new ListRecordAdapter(getActivity(), mListRecord);
        mListviewRecord.setAdapter(mAdapter);

        mListviewRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Record clickedRecord = mAdapter.getItem(position);

                Intent intent = new Intent(getActivity(), ResepRansumRecord.class);
                Bundle var_resep = new Bundle();
                var_resep.putInt("idrecord", clickedRecord.getIdrecord());
                intent.putExtras(var_resep);
                startActivity(intent);

            }
        });

        return view;
    }
}
