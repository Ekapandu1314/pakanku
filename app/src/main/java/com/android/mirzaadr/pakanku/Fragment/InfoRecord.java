package com.android.mirzaadr.pakanku.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListRecordAdapter;
import com.android.mirzaadr.pakanku.Dao.RecordDAO;
import com.android.mirzaadr.pakanku.Model.Record;
import com.android.mirzaadr.pakanku.R;
import com.android.mirzaadr.pakanku.Activity.ResepRansumRecord;

import java.util.List;

/**
 * Created by Mirzaadr on 4/1/2016.
 */
public class InfoRecord extends Fragment {

    private ListRecordAdapter mAdapter;
    private List<Record> mListRecord;
    private RecordDAO mRecordDao;
    private ListView mListviewRecord;
    Boolean list = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vieww = inflater.inflate(R.layout.fragment_record, container, false);

        mRecordDao = new RecordDAO(getActivity());
        mListRecord = mRecordDao.getAllRecord();

        final Spinner spinner = (Spinner) vieww.findViewById(R.id.spinnerHewan);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.hewan, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        this.mListviewRecord = (ListView) vieww.findViewById(R.id.listRecord);

        mAdapter = new ListRecordAdapter(getActivity(), mListRecord);
        mListviewRecord.setAdapter(mAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hewan;
                hewan = spinner.getSelectedItem().toString();
                mListviewRecord = (ListView) vieww.findViewById(R.id.listRecord);
                mListRecord = mRecordDao.getAllRecordByHewan(hewan);
                mAdapter = new ListRecordAdapter(getActivity(), mListRecord);
                mListviewRecord.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

        mListviewRecord.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Record clickedRecord = mAdapter.getItem(position);

                showDeleteDialogConfirmation(clickedRecord);

                return false;
            }
        });




        return vieww;
    }

    private void showDeleteDialogConfirmation(final Record record) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle("Hapus");
        alertDialogBuilder
                .setMessage("Apakah kamu yakin untuk menghapus data record \""
                        + record.getNama_record() + " ?");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the employee and refresh the list
                if(mRecordDao != null) {
                    mRecordDao.deleteRecord(record);

                    //refresh the listView
                    mListRecord.remove(record);
                    if(mListRecord.isEmpty()) {
                        mListviewRecord.setVisibility(View.INVISIBLE);
                        //mTxtEmptyListEmployees.setVisibility(View.VISIBLE);
                    }

                    mAdapter.setItems(mListRecord);
                    mAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
                Toast.makeText(getActivity(), "Data record telah berhasil dihapus", Toast.LENGTH_SHORT).show();

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
}
