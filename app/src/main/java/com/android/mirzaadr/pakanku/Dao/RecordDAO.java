package com.android.mirzaadr.pakanku.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.mirzaadr.pakanku.Model.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eka Pandu Winata on 4/4/2016.
 */
public class RecordDAO {

    public static final String TAG = "RecordDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.RECORD_ID,
            DBHelper.RTANGGAL,
            DBHelper.RHEWAN,
            DBHelper.RTUJUAN,
            DBHelper.RBERAT,
            DBHelper.RJTERNAK,
            DBHelper.PBAHAN,
            DBHelper.JBAHAN,
            DBHelper.RTUANG,
            DBHelper.RTUNTUNG
    };

    public RecordDAO(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Record createRecord(String rtanggal,
                               String rhewan,
                               double rberat,
                               int rjternak,
                               String pbahan,
                               String jbahan,
                               int rtuang,
                               int rtuntung) {
        ContentValues values = new ContentValues();
        //values.put(DBHelper.BAHAN_ID, idbahan);
        values.put(DBHelper.RTANGGAL, rtanggal);
        values.put(DBHelper.RHEWAN, rhewan);
        values.put(DBHelper.RBERAT, rberat);
        values.put(DBHelper.RJTERNAK, rjternak);
        values.put(DBHelper.PBAHAN, pbahan);
        values.put(DBHelper.JBAHAN, jbahan);
        values.put(DBHelper.RTUANG, rtuang);
        values.put(DBHelper.RTUNTUNG, rtuntung);

        int insertId = (int) mDatabase
                .insert(DBHelper.TABLE_RECORD, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_RECORD, mAllColumns,
                DBHelper.RECORD_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Record newRecord = cursorToRecord(cursor);
        cursor.close();
        return newRecord;
    }

    public void deleteRecord(Record record) {
        int id = record.getIdrecord();
        System.out.println("the deleted ingredient has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_RECORD, DBHelper.RECORD_ID
                + " = " + id, null);
    }

    public void addRecordJson(Record record) {
        //SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(DBHelper.RECORD_ID, record.getIdrecord());
            values.put(DBHelper.RTANGGAL, record.getRtanggal());
            values.put(DBHelper.RHEWAN, record.getRhewan());
            values.put(DBHelper.RBERAT, record.getRberat());
            values.put(DBHelper.RJTERNAK, record.getRjternak());
            values.put(DBHelper.PBAHAN, record.getPbahan());
            values.put(DBHelper.JBAHAN, record.getJbahan());
            values.put(DBHelper.RTUANG, record.getRtuang());
            values.put(DBHelper.RTUNTUNG, record.getRtuntung());

            mDatabase.insert(DBHelper.TABLE_RECORD, null, values);

            mDatabase.update(DBHelper.TABLE_RECORD, values, DBHelper.RECORD_ID + " = " + record.getIdrecord(), null);


            //mDatabase.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    public List<Record> getAllRecord() {
        List<Record> listRecord = new ArrayList<Record>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_RECORD, mAllColumns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Record record = cursorToRecord(cursor);
                listRecord.add(record);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }
        return listRecord;
    }

    public Record getRecordById(int id) {
        Cursor cursorxx = mDatabase.query(DBHelper.TABLE_RECORD, mAllColumns, DBHelper.RECORD_ID + " = ",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursorxx != null)
            cursorxx.moveToFirst();

        Record record = cursorToRecord(cursorxx);
        // return contact
        return record;

    }

    private Record cursorToRecord(Cursor cursor) {
        Record record = new Record();

        record.setIdrecord(cursor.getInt(0));
        record.setRtanggal(cursor.getString(1));
        record.setRhewan(cursor.getString(2));
        record.setRtujuan(cursor.getString(3));
        record.setRberat(cursor.getDouble(4));
        record.setRjternak(cursor.getInt(5));
        record.setPbahan(cursor.getString(6));
        record.setJbahan(cursor.getString(7));
        record.setRtuang(cursor.getInt(8));
        record.setRtuntung(cursor.getInt(9));

        return record;
    }

}
