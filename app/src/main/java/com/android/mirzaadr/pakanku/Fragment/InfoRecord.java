package com.android.mirzaadr.pakanku.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mirzaadr.pakanku.Adapter.ListRecordAdapter;
import com.android.mirzaadr.pakanku.Dao.RecordDAO;
import com.android.mirzaadr.pakanku.Decoration.DividerItemDecoration;
import com.android.mirzaadr.pakanku.Model.Record;
import com.android.mirzaadr.pakanku.MyScrollListener;
import com.android.mirzaadr.pakanku.R;
import com.android.mirzaadr.pakanku.ResepRansumRecord;

import org.w3c.dom.Text;

import java.util.List;


public class InfoRecord extends Fragment {

    private ListRecordAdapter mAdapter;
    private List<Record> mListRecord;
    private RecordDAO mRecordDao;
    RecyclerView mListviewRecord;

    private int mToolbarHeight;

    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_record, container, false);

        mRecordDao = new RecordDAO(getActivity());
        mListRecord = mRecordDao.getAllRecord();

        setHasOptionsMenu(true);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_informasi);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinnerHewan);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.hewan, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.mListviewRecord = (RecyclerView) view.findViewById(R.id.listRecord);

        mAdapter = new ListRecordAdapter(mListRecord);
        mListviewRecord.setAdapter(mAdapter);

        mToolbarHeight = toolbar.getHeight();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View views, int position, long id) {
                String hewan = "Semua";
                hewan = spinner.getSelectedItem().toString();
                mListviewRecord = (RecyclerView) view.findViewById(R.id.listRecord);
                mListRecord = mRecordDao.getAllRecordByHewan(hewan);
                mAdapter = new ListRecordAdapter(mListRecord);
                mListviewRecord.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mListviewRecord.setLayoutManager(mLayoutManager);
        mListviewRecord.setItemAnimator(new DefaultItemAnimator());
        mListviewRecord.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        mListviewRecord.setItemAnimator(new DefaultItemAnimator());

        mListviewRecord.addOnScrollListener(new MyScrollListener(getActivity()) {

            @Override
            public void onMoved(int distance) {
//                toolbar.setTranslationY(-distance);
//                fab.hide();
            }

            @Override
            public void onShow() {
                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void onHide() {
                toolbar.animate().translationY(-mToolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
            }

        });

        mListviewRecord.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mListviewRecord, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Record clickedRecord = mListRecord.get(position);

                Intent intent = new Intent(getActivity(), ResepRansumRecord.class);
                Bundle var_resep = new Bundle();
                var_resep.putInt("idrecord", clickedRecord.getIdrecord());
                intent.putExtras(var_resep);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, final int position) {

                final Record clickedRecord = mListRecord.get(position);

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_box_yes_no);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                final Button yesHapus = (Button) dialog.findViewById(R.id.buttonYES);
                final Button noHapus = (Button) dialog.findViewById(R.id.buttonNO);
                final TextView hapus = (TextView) dialog.findViewById(R.id.hapus_record);

                hapus.setText("Apakah kamu yakin untuk menghapus data record "
                        + clickedRecord.getNama_record() + " ?");

                yesHapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(mRecordDao != null) {
                            mRecordDao.deleteRecord(clickedRecord);

                            //refresh the listView
                            //mListRecord.remove(record);
                            mListRecord.remove(position);
                            mAdapter.setmItems(mListRecord);
                            mAdapter.notifyItemRemoved(position);
                            if(mListRecord.isEmpty()) {
                                mListviewRecord.setVisibility(View.INVISIBLE);
                            }

                        }

                        dialog.dismiss();

                    }
                });

                noHapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        }));

        return view;
    }

//    private void showDeleteDialogConfirmation(final Record record, final int position) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//
//        alertDialogBuilder.setTitle("Hapus");
//        alertDialogBuilder
//                .setMessage("Apakah kamu yakin untuk menghapus data record "
//                        + record.getNama_record() + " ?");
//
//        // set positive button YES message
//        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // delete the employee and refresh the list
//                if(mRecordDao != null) {
//                    mRecordDao.deleteRecord(record);
//
//                    //refresh the listView
//                    //mListRecord.remove(record);
//                    mListRecord.remove(position);
//                    mAdapter.setmItems(mListRecord);
//                    mAdapter.notifyItemRemoved(position);
//                    if(mListRecord.isEmpty()) {
//                        mListviewRecord.setVisibility(View.INVISIBLE);
//                        //mTxtEmptyListEmployees.setVisibility(View.VISIBLE);
//                    }
//
//                    //mAdapter.setmItems(mListRecord);
//
//                    //mAdapter.notifyDataSetChanged();
//                }
//
//                dialog.dismiss();
//                Toast.makeText(getActivity(), "Data record telah berhasil dihapus", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        // set neutral button OK
//        alertDialogBuilder.setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Dismiss the dialog
//                dialog.dismiss();
//            }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        // show alert
//        alertDialog.show();
//    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private InfoRecord.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final InfoRecord.ClickListener clickListener) {
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


}
